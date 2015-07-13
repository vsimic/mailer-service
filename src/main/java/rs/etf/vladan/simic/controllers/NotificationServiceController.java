package rs.etf.vladan.simic.controllers;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rs.etf.vladan.simic.domain.RegistrationMailNotification;
import rs.etf.vladan.simic.requests.integrations.events.Event;
import rs.etf.vladan.simic.requests.notifications.request.VerificationEmailRequest;
import rs.etf.vladan.simic.requests.notifications.response.MailerResponse;
import rs.etf.vladan.simic.services.MailService;

@RestController
@RequestMapping("/api/notification")
@Slf4j
public class NotificationServiceController {

    @Autowired
    private MailService mailService;

    private HttpHeaders getNoCacheHeaders() {
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	return responseHeaders;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MailerResponse> processNotificationRequest(
	    @RequestBody @Valid VerificationEmailRequest newEmailSubmition) {

	log.debug("Received new mail request {}", newEmailSubmition.toString());

	MailerResponse sendEmailResponse = mailService.sendEmail(newEmailSubmition);

	log.debug("sending status of email {}", sendEmailResponse.getMailStatus());

	return new ResponseEntity<MailerResponse>(sendEmailResponse, getNoCacheHeaders(), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MailerResponse> receiveEventUpdate(@RequestBody @Valid Event userCreatedEvent) {
	
	log.debug("New user created, send werification email");

	RegistrationMailNotification verificationEmail =mailService.generateMailRequestForCreateduser(userCreatedEvent);
	
	MailerResponse sendEmailResponse =mailService.sendEmailForNewlyCreatedUser(verificationEmail);
	
	return new ResponseEntity<MailerResponse>(sendEmailResponse,getNoCacheHeaders(), HttpStatus.CREATED);
    }

}
