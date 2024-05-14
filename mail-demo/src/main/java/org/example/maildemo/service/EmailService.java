package org.example.maildemo.service;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.constants.CustomResponseMessage;
import org.example.maildemo.dto.MailRequest;
import org.example.maildemo.dto.UserMailDto;
import org.example.maildemo.entity.Mail;
import org.example.maildemo.entity.Tag;
import org.example.maildemo.entity.User;
import org.example.maildemo.entity.UserMail;
import org.example.maildemo.exception.NotFoundException;
import org.example.maildemo.model.MailBoxTypes;
import org.example.maildemo.model.ResponseMessage;
import org.example.maildemo.populator.UserMailDtoPopulator;
import org.example.maildemo.repository.UserMailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<String> sendEmail(MailRequest email) {
        Set<User> toUsers = userService.findUsers(email.getTo());
        Set<Tag> tags = tagService.findTags(email.getTags());
        Mail mail = Mail.builder().content(email.getContent() == null ? "" : email.getContent()).creationTime(new Date()).tags(tags).title(email.getTitle()).build();

        List<UserMail> userMails = toUsers.stream().map(e -> UserMail.builder().user(e).mail(mail).mailBoxTypes(MailBoxTypes.INBOX).isRead(false).build()).toList();
        userMailService.saveAll(userMails);
        return ResponseEntity.ok(CustomResponseMessage.EMAIL_SENT_SUCCESSFULLY);
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
