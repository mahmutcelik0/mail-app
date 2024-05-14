package org.example.maildemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.maildemo.model.MailBoxTypes;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMailDto {
    private UserDto user;
    private MailDto mail;
    private MailBoxTypes mailBoxTypes;
    private boolean isRead;
}
