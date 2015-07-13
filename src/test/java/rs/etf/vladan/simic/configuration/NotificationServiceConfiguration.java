package rs.etf.vladan.simic.configuration;

import rs.etf.vladan.simic.requests.notifications.request.VerificationEmailRequest;
import rs.etf.vladan.simic.requests.notifications.response.MailSendingStatus;
import rs.etf.vladan.simic.requests.notifications.response.MailerResponse;

public class NotificationServiceConfiguration {

    public static VerificationEmailRequest submitEmailRequest() {

	VerificationEmailRequest newRequest = new VerificationEmailRequest();
	newRequest.setActivationCode("testActivationCode");
	newRequest.setEmailSender("test@test.com");
	newRequest.setEmailSubject("testEmail");
	newRequest.setRecipientEmail("omaha88@gmail.com");
	newRequest.setUserId("123");

	return newRequest;
    }
    
    public static VerificationEmailRequest submitInvalidEmailRequest() {

	VerificationEmailRequest newRequest = new VerificationEmailRequest();
	newRequest.setActivationCode("testActivationCode");
	newRequest.setEmailSender("test@test.com");
	newRequest.setEmailSubject("testEmail");
	newRequest.setUserId("123");

	return newRequest;
    }
        

    public static MailerResponse mailSentResponseStatus() {

	MailerResponse response = new MailerResponse(MailSendingStatus.SENT);

	return response;
    }

}
