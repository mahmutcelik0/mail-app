package org.example.maildemo.repository;

import org.example.maildemo.entity.UserMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserMailRepository extends JpaRepository<UserMail, Long> {
    @Query("select um from UserMail as um where um.user.email = ?1 and um.mail.id = ?2")
    Optional<UserMail> findByUserEmailAndMailId(String userEmail, Long mailId);
}
