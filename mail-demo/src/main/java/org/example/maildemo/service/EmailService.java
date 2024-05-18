package org.example.maildemo.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.example.maildemo.constants.CustomResponseMessage;
import org.example.maildemo.dto.MailRequest;
import org.example.maildemo.dto.MailUserDto;
import org.example.maildemo.dto.UserMailDto;
import org.example.maildemo.entity.Mail;
import org.example.maildemo.entity.Tag;
import org.example.maildemo.entity.User;
import org.example.maildemo.entity.UserMail;
import org.example.maildemo.exception.NotFoundException;
import org.example.maildemo.model.MailBoxTypes;
import org.example.maildemo.model.ResponseMessage;
import org.example.maildemo.populator.UserMailDtoPopulator;
import org.example.maildemo.repository.EmailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final UserService userService;
    private final TagService tagService;
    private final UserMailService userMailService;
    private final UserMailDtoPopulator userMailDtoPopulator;
    private final EmailRepository emailRepository;

    public ResponseEntity<ResponseMessage> sendEmail(MailRequest mailRequest) throws NotFoundException {
        if (doesUserSendEmailSelf(mailRequest.getTo()))
            return ResponseEntity.internalServerError().body(ResponseMessage.builder().message(CustomResponseMessage.USER_CAN_NOT_SEND_EMAIL_SELF).status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        Set<User> toUsers = userService.findUsers(mailRequest.getTo());
        Set<Tag> tags = tagService.findTags(mailRequest.getTags());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String senderMail = authentication.getPrincipal().toString();

        Mail parent = null;
        if(ObjectUtils.isNotEmpty(mailRequest.getRepliedMail())){
            parent =emailRepository.findById(mailRequest.getRepliedMail().getId()).orElseThrow(()->new NotFoundException(CustomResponseMessage.EMAIL_DOES_NOT_EXIST));
        }

            Mail mail = Mail.builder()
            .content(mailRequest.getContent() == null ? "" : mailRequest.getContent())
            .creationTime(new Date())
            .tags(tags)
            .title(mailRequest.getTitle())
            .sender(userService.findUserByMail(senderMail))
            .parent(parent == null ? null : Set.of(parent))
            .build();

            mail.getParent().forEach(e-> {
                if(CollectionUtils.isEmpty(e.getChild())) e.setChild(Set.of(e));
                else e.getChild().add(e);
            });

        List<UserMail> userMails = toUsers.stream().map(e -> UserMail.builder().user(e).mail(mail).mailBoxTypes(MailBoxTypes.INBOX).isRead(false).build()).toList();
        userMailService.saveAll(userMails);
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK, CustomResponseMessage.EMAIL_SENT_SUCCESSFULLY));
    }

    private boolean doesUserSendEmailSelf(List<MailUserDto> mailUserDtoList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String senderMail = authentication.getPrincipal().toString();
        return mailUserDtoList.stream().anyMatch(e -> e.getEmail().equalsIgnoreCase(senderMail));
    }

    public ResponseEntity<List<UserMailDto>> getUserEmails() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findUserByMail(authentication.getPrincipal().toString());

        return ResponseEntity.ok(user.getUserMails().stream().map(userMailDtoPopulator::populate).toList());
    }

    public ResponseEntity<ResponseMessage> updateUserEmail(UserMailDto userMailDto) throws NotFoundException {
        return userMailService.updateUserEmail(userMailDto);
    }

    public ResponseEntity<ResponseMessage> deleteUserEmail(UserMailDto userMailDto) throws NotFoundException {
        return userMailService.deleteUserEmail(userMailDto);
    }
}
