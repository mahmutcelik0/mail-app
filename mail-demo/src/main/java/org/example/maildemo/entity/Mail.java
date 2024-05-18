package org.example.maildemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "MAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date creationTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "MAIL_TAGS",
            joinColumns = {@JoinColumn(name = "MAIL_ID",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID",referencedColumnName = "id")}
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "mail")
    private List<UserMail> userMails;

    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private User sender;

    @ManyToMany
    private Set<Mail> parent = new HashSet<>();

    @ManyToMany(mappedBy = "parent")
    private Set<Mail> child = new HashSet<>();
}
