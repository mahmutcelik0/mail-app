package org.example.maildemo.service;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.RegisterRequest;
import org.example.maildemo.model.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserFacade userFacade;

    public ResponseEntity<ResponseMessage> register(RegisterRequest registerInformations) {
        return userFacade.register(registerInformations);

    }
}
