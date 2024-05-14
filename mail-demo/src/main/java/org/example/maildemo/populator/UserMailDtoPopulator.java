package org.example.maildemo.populator;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.UserMailDto;
import org.example.maildemo.entity.UserMail;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMailDtoPopulator extends GenericPopulator<UserMail, UserMailDto> {
    private final UserDtoPopulator userDtoPopulator;
    private final MailDtoPopulator mailDtoPopulator;

    @Override
    public UserMailDto populate(UserMail userMail, UserMailDto userMailDto) {
        userMailDto.setUser(userDtoPopulator.populate(userMail.getUser()));
        userMailDto.setMail(mailDtoPopulator.populate(userMail.getMail()));
        userMailDto.setRead(userMail.isRead());
        userMailDto.setMailBoxTypes(userMail.getMailBoxTypes());
        return userMailDto;
    }

    @Override
    public UserMailDto getTarget() {
        return new UserMailDto();
    }
}
