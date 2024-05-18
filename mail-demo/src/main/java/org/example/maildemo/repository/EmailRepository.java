package org.example.maildemo.repository;

import org.example.maildemo.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Mail,Long> {

}
