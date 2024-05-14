package org.example.maildemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.maildemo.model.MailBoxTypes;

@Entity
@Table(name = "USER_MAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade =  {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id",referencedColumnName ="USER_ID" )
    private User user;

    @JsonIgnore
    @ManyToOne(cascade =  {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Mail mail;

    private MailBoxTypes mailBoxTypes;
    private boolean isRead;
}
