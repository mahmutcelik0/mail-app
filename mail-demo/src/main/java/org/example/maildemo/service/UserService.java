package org.example.maildemo.service;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.constants.CustomResponseMessage;
import org.example.maildemo.dto.MailUserDto;
import org.example.maildemo.dto.RegisterRequest;
import org.example.maildemo.entity.User;
import org.example.maildemo.exception.NotFoundException;
import org.example.maildemo.model.ResponseMessage;
import org.example.maildemo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByMail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(CustomResponseMessage.USER_NOT_FOUND));
    }

    public void example(){

    }

    public ResponseEntity<ResponseMessage> register(RegisterRequest registerInformations) {
        if(userRepository.existsByEmail(registerInformations.getEmail())){
            return ResponseEntity.badRequest().body(ResponseMessage.builder().message(CustomResponseMessage.USER_ALREADY_EXISTS).status(HttpStatus.BAD_REQUEST).build());
        }
        User user = User.builder()
                .email(registerInformations.getEmail())
                .password(registerInformations.getPassword())
                .firstName(registerInformations.getFirstName())
                .lastName(registerInformations.getLastName())
                .build();
        userRepository.save(user);
        return ResponseEntity.ok(ResponseMessage.builder().message(CustomResponseMessage.USER_CREATED).status(HttpStatus.OK).build());
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Set<User> findUsers(Set<MailUserDto> to) {
        return to.stream().map(e-> {
            try {
                return findUserByMail(e.getEmail());
            } catch (NotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(Collectors.toSet());
    }
}
