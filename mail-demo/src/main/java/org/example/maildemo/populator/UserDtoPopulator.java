package org.example.maildemo.populator;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.UserDto;
import org.example.maildemo.entity.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoPopulator extends GenericPopulator<User, UserDto> {
    @Override
    public UserDto populate(User user, UserDto userDto) {
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

    @Override
    public UserDto getTarget() {
        return new UserDto();
    }
}
