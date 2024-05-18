package org.example.maildemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    private String content;
    private List<MailUserDto> to;
    private List<String> tags;
    private String title;
    private MailDto repliedMail;
}