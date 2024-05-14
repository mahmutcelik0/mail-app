package org.example.maildemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private Long id;
    private String content;
    private Date creationTime;
    private Set<TagDto> tags = new HashSet<>();
    private String title;
}
