package rs.etf.vladan.simic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import rs.etf.vladan.simic.domain.RegistrationMailNotification;
import rs.etf.vladan.simic.domains.VerificationEmail;
import rs.etf.vladan.simic.repository.NotificationRepository;
import rs.etf.vladan.simic.requests.integrations.events.Event;
import rs.etf.vladan.simic.requests.integrations.events.UserCreatedEventData;
import rs.etf.vladan.simic.requests.notifications.request.VerificationEmailRequest;
import rs.etf.vladan.simic.requests.notifications.response.MailSendingStatus;
import rs.etf.vladan.simic.requests.notifications.response.MailerResponse;
import rs.etf.vladan.simic.services.MandrilService;
import rs.etf.vladan.simic.services.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Value("${mailer.recepient.default:myemail@mail.com.com}")
    private String defaultEmailSender;

    @Value("${mailer.verification.subject:default subject}")
    private String defaultEmailSubject;

    @Autowired
    private MandrilService mailSenderService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public MailerResponse sendEmail(VerificationEmailRequest newEmailRequest) {
	// generate appropriate email object

	VerificationEmail newMail = new VerificationEmail(newEmailRequest.getEmailSubject(),
		newEmailRequest.getRecipientEmail(), newEmailRequest.getUserId());

	boolean isSuccesuful = mailSenderService.sendVerificationMail(newMail);

	MailerResponse response = new MailerResponse(MailSendingStatus.SENDING_IN_PROGRESS);

	if (isSuccesuful) {
	    response.setMailStatus(MailSendingStatus.SENT);
	    newMail.setSent(true);
	}
	notificationRepository.save(newMail);

	return response;

    }

    @Override
    public RegistrationMailNotification generateMailRequestForCreateduser(Event userCreatedEvent) {

	UserCreatedEventData userCreatedData = (UserCreatedEventData) userCreatedEvent.getEventData();

	RegistrationMailNotification newMailNotification = new RegistrationMailNotification.RegistrationMailNotificationBuilder(
		userCreatedData.getFirstName(), userCreatedData.getLastName(), userCreatedData.getEmail())
		.create();

	return newMailNotification;
    }

    @Override
    public MailerResponse sendEmailForNewlyCreatedUser(RegistrationMailNotification verificationEmail) {

	VerificationEmail newMail = new VerificationEmail("verification email",
		verificationEmail.getRecipientEmailAddress(), verificationEmail.getRecipientEmailAddress());

	boolean isSuccesuful = mailSenderService.sendVerificationMail(newMail);

	MailerResponse response = new MailerResponse(MailSendingStatus.SENDING_IN_PROGRESS);

	if (isSuccesuful) {
	    response.setMailStatus(MailSendingStatus.SENT);
	    newMail.setSent(true);
	}
	notificationRepository.save(newMail);

	return response;

    }
}
