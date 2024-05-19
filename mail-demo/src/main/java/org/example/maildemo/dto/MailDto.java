package org.example.maildemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private Long id;
    private String content;
    private Date creationTime;
    private List<TagDto> tags;
    private String title;
    private UserDto sender;
    private List<MailDto> parent;
    private List<MailDto> child;
}
