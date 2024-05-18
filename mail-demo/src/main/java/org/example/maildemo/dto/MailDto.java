package org.example.maildemo.dto;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.maildemo.entity.Mail;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
