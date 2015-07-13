package rs.etf.vladan.simic.services.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import rs.etf.vladan.simic.domains.VerificationEmail;
import rs.etf.vladan.simic.repository.NotificationRepository;
import rs.etf.vladan.simic.services.MandrilService;

@Component
@Slf4j
public class ScheduleService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MandrilService mandrilService;

    @Scheduled(fixedRate = 10000)
    public void resendUnsentEmails() {
	List<VerificationEmail> findAll = notificationRepository.findBySent(false);

	log.info("Retry to send total of: {} mails", findAll.size());
	if (findAll.size() > 0) {
	    for (VerificationEmail unsentVerificationMail : findAll) {

		boolean isSent = mandrilService.sendVerificationMail(unsentVerificationMail);
		log.info("Mail with id {} is sent ? {}", unsentVerificationMail.getId(), isSent);
		if (isSent) {
		    unsentVerificationMail.setSent(true);
		    notificationRepository.save(unsentVerificationMail);
		}

	    }

	}

    }

}
