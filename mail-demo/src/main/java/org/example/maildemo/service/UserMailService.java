package org.example.maildemo.service;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.constants.CustomResponseMessage;
import org.example.maildemo.dto.UserMailDto;
import org.example.maildemo.entity.UserMail;
import org.example.maildemo.exception.NotFoundException;
import org.example.maildemo.model.ResponseMessage;
import org.example.maildemo.repository.UserMailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMailService {
    private final UserMailRepository userMailRepository;

    public void saveAll(List<UserMail> userMail) {
        userMailRepository.saveAll(userMail);
    }


    public UserMail updateUserEmail(UserMailDto userMailDto) throws NotFoundException {
        UserMail userMail = userMailRepository.findByUserEmailAndMailId(userMailDto.getUser().getEmail(), userMailDto.getMail().getId()).orElseThrow(() -> new NotFoundException(CustomResponseMessage.USER_MAIL_NOT_FOUND));
        userMail.setMailBoxTypes(userMailDto.getMailBoxTypes() != null ? userMailDto.getMailBoxTypes() : userMail.getMailBoxTypes());
        userMail.setRead(userMailDto.isRead());
        userMailRepository.save(userMail);
        return userMailRepository.save(userMail);
    }

    public ResponseEntity<ResponseMessage> deleteUserEmail(UserMailDto userMailDto) throws NotFoundException {
        UserMail userMail = userMailRepository.findByUserEmailAndMailId(userMailDto.getUser().getEmail(), userMailDto.getMail().getId()).orElseThrow(() -> new NotFoundException(CustomResponseMessage.USER_MAIL_NOT_FOUND));
        userMailRepository.delete(userMail);
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,CustomResponseMessage.SUCCESS));
    }
}
