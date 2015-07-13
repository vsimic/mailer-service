package rs.etf.vladan.simic.services;

import rs.etf.vladan.simic.domain.RegistrationMailNotification;
import rs.etf.vladan.simic.requests.integrations.events.Event;
import rs.etf.vladan.simic.requests.notifications.request.VerificationEmailRequest;
import rs.etf.vladan.simic.requests.notifications.response.MailerResponse;

public interface MailService {

	MailerResponse sendEmail(VerificationEmailRequest newEmail) ;

	RegistrationMailNotification generateMailRequestForCreateduser(Event userCreatedEvent);

	MailerResponse sendEmailForNewlyCreatedUser(RegistrationMailNotification verificationEmail);
}
