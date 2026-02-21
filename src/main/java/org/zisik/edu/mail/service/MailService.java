package org.zisik.edu.mail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.exception.InvalidRequest;
import org.zisik.edu.mail.domain.Feedback;
import org.zisik.edu.mail.domain.Mail;
import org.zisik.edu.mail.domain.MailType;
import org.zisik.edu.mail.repository.FeedbackRepository;
import org.zisik.edu.mail.repository.MailRepository;
import org.zisik.edu.mail.request.SendFeedbackRequest;
import org.zisik.edu.mail.response.FeedbackResponse;
import org.zisik.edu.mail.response.MailResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {

    private final MailRepository mailRepository;
    private final FeedbackRepository feedbackRepository;

    public Page<MailResponse> getNotices(Pageable pageable) {
        return mailRepository.findByRecipientIdIsNullAndType(MailType.NOTICE, pageable)
                .map(MailResponse::from);
    }

    public Page<MailResponse> getPersonalMails(Long accountId, Pageable pageable) {
        return mailRepository.findByRecipientIdAndTypeNot(accountId, MailType.NOTICE, pageable)
                .map(MailResponse::from);
    }

    @Transactional
    public void markAsRead(Long accountId, Long mailId) {
        Mail mail = mailRepository.findById(mailId)
                .orElseThrow(() -> new InvalidRequest("메일을 찾을 수 없습니다."));

        // 공지가 아닌 경우 본인 메일만 읽음 처리 가능
        if (mail.getRecipientId() != null && !mail.getRecipientId().equals(accountId)) {
            throw new InvalidRequest("본인의 메일만 읽음 처리할 수 있습니다.");
        }

        mail.markAsRead();
    }

    @Transactional
    public FeedbackResponse sendFeedback(Long accountId, SendFeedbackRequest request) {
        Feedback feedback = Feedback.builder()
                .senderId(accountId)
                .subject(request.getSubject())
                .content(request.getContent())
                .build();

        feedbackRepository.save(feedback);
        return FeedbackResponse.from(feedback);
    }

    public long getUnreadCount(Long accountId) {
        return mailRepository.countByRecipientIdAndIsReadFalse(accountId);
    }
}
