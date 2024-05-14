package org.example.maildemo.populator;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.MailDto;
import org.example.maildemo.entity.Mail;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailDtoPopulator extends GenericPopulator<Mail, MailDto> {
    private final TagDtoPopulator tagDtoPopulator;

    @Override
    public MailDto populate(Mail mail, MailDto mailDto) {
        mailDto.setId(mail.getId());
        mailDto.setContent(mail.getContent());
        mailDto.setCreationTime(mail.getCreationTime());
        mailDto.setTags(tagDtoPopulator.populateAll(mail.getTags()));
        mailDto.setTitle(mail.getTitle());
        return mailDto;
    }

    @Override
    public MailDto getTarget() {
        return new MailDto();
    }
}
