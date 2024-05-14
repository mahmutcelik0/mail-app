package org.example.maildemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.MailRequest;
import org.example.maildemo.dto.UserMailDto;
import org.example.maildemo.exception.NotFoundException;
import org.example.maildemo.model.ResponseMessage;
import org.example.maildemo.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emails")
@RequiredArgsConstructor
public class EmailApi {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody MailRequest mailRequest) {
        return emailService.sendEmail(mailRequest);
    }

    @GetMapping
    public ResponseEntity<List<UserMailDto>> getUserEmails() throws NotFoundException {
        return emailService.getUserEmails();
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseMessage> updateUserEmail(@RequestBody UserMailDto userMailDto) throws NotFoundException {
        return emailService.updateUserEmail(userMailDto);
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage> deleteUserEmail(@RequestBody UserMailDto userMailDto) throws NotFoundException {
        return emailService.deleteUserEmail(userMailDto);
    }
}
