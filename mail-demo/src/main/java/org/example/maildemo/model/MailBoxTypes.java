package org.example.maildemo.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MailBoxTypes {
    INBOX("INBOX"),
    ARCHIVE("ARCHIVE"),
    SENT("SENT"),
    JUNK("JUNK");

    private String type;
}