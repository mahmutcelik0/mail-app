package org.example.maildemo.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MailBoxTypes {
    INBOX("INBOX"),
    DELETE("DELETE");

    private String type;
}