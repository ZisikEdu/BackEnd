package org.zisik.edu.mail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.mail.domain.Mail;
import org.zisik.edu.mail.domain.MailType;

public interface MailRepository extends JpaRepository<Mail, Long> {

    Page<Mail> findByRecipientIdIsNullAndType(MailType type, Pageable pageable);

    Page<Mail> findByRecipientIdAndTypeNot(Long recipientId, MailType type, Pageable pageable);

    Page<Mail> findByRecipientId(Long recipientId, Pageable pageable);

    long countByRecipientIdAndIsReadFalse(Long recipientId);
}
