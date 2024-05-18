package org.example.maildemo.populator;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.MailDto;
import org.example.maildemo.entity.Mail;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MailDtoPopulator extends GenericPopulator<Mail, MailDto> {
    private final TagDtoPopulator tagDtoPopulator;
    private final UserDtoPopulator userDtoPopulator;

    @Override
    public MailDto populate(Mail mail, MailDto mailDto) {
        mailDto.setId(mail.getId());
        mailDto.setContent(mail.getContent());
        mailDto.setCreationTime(mail.getCreationTime());
        mailDto.setTags(tagDtoPopulator.populateAlltoList(mail.getTags()));
        mailDto.setTitle(mail.getTitle());
        mailDto.setSender(userDtoPopulator.populate(mail.getSender()));
        mailDto.setParent(this.getSubMail(mail.getParent(),mail.getId(),true));
        mailDto.setChild(this.getSubMail(mail.getChild(),mail.getId(),false));
        return mailDto;
    }

    public MailDto populate(Mail mail, MailDto mailDto,boolean isParent) {
        mailDto.setId(mail.getId());
        mailDto.setContent(mail.getContent());
        mailDto.setCreationTime(mail.getCreationTime());
        mailDto.setTags(tagDtoPopulator.populateAlltoList(mail.getTags()));
        mailDto.setTitle(mail.getTitle());
        mailDto.setSender(userDtoPopulator.populate(mail.getSender()));
        if(isParent)
            mailDto.setParent(this.getSubMail(mail.getParent(),mail.getId(), true));
        else
            mailDto.setChild(this.getSubMail(mail.getChild(),mail.getId(), false));
        return mailDto;
    }


    private List<MailDto> getSubMail(Set<Mail> subMail, Long mailId, boolean b) {
        List<MailDto> mailDtoList = new ArrayList<>();
        if(CollectionUtils.isEmpty(subMail)) return mailDtoList;
        subMail.forEach(e->{
            if(!Objects.equals(e.getId(), mailId))
                mailDtoList.add(populate(e,getTarget(),b));
        });
        return mailDtoList;
    }


    @Override
    public MailDto getTarget() {
        return new MailDto();
    }
}
