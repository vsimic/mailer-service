package rs.etf.vladan.simic.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import rs.etf.vladan.simic.domains.VerificationEmail;
import rs.etf.vladan.simic.services.MandrilService;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import com.cribbstechnologies.clients.mandrill.model.MandrillTemplatedMessageRequest;
import com.cribbstechnologies.clients.mandrill.model.MergeVar;
import com.cribbstechnologies.clients.mandrill.request.MandrillMessagesRequest;
import com.cribbstechnologies.clients.mandrill.request.MandrillRESTRequest;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MandrilSenderImpl implements MandrilService {

	private static final Logger log = LoggerFactory
			.getLogger(MandrilSenderImpl.class);

	@Value("${mailer.sender.default:MyAwsomeApp}")
	private String defaultMailSenderName;

	@Value("${mailer.recepient.default:myemail@mail.com.com}")
	private String defaultEmailSender;

	@Value("${notification.accessLink.pattern:https://loalhost:8080/registaration/}")
	private String defaultAccessLinkPattern;

	HttpClient client = HttpClientBuilder.create().build();
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public boolean sendVerificationMail(VerificationEmail verificationEmail) {

		MandrillRESTRequest restRequest = initMandril();

		MandrillMessagesRequest messagesRequest = new MandrillMessagesRequest();
		messagesRequest.setRequest(restRequest);

		MandrillTemplatedMessageRequest request = new MandrillTemplatedMessageRequest();

		MandrillMessage mandrillMessage = new MandrillMessage();
		mandrillMessage.setFrom_name(defaultMailSenderName);
		mandrillMessage.setFrom_email(defaultEmailSender);
		mandrillMessage.setSubject(verificationEmail.getSubject());

		MandrillRecipient[] recipients = new MandrillRecipient[1];
		recipients[0] = new MandrillRecipient("****", verificationEmail.getRecipient());

		mandrillMessage.setTo(recipients);
		mandrillMessage.setTrack_clicks(true);
		mandrillMessage.setTrack_opens(true);

		// FIXME: SOO WRONG
//		String accessLink = defaultAccessLinkPattern
//				+ ((VerificationEmail) verificationEmail).getActivationCode();

		List<MergeVar> globalMergeVars = new ArrayList<MergeVar>();
		mandrillMessage.setGlobal_merge_vars(globalMergeVars);

		request.setMessage(mandrillMessage);
		request.setTemplate_name("verification-new");
		try {
			messagesRequest.sendTemplatedMessage(request);
		} catch (RequestFailedException re) {
			log.error("Sending email of type {} to {} failed.");
			return false;
		} catch (IllegalStateException ie) {
			restRequest.setHttpClient(HttpClientBuilder.create().build());
			sendVerificationMail(verificationEmail);
		} catch (Exception e) {
			log.error("Sending email failed. Cause {}.", e.getMessage());
			return false;
		}

		return true;
	}

	private MandrillRESTRequest initMandril() {
		MandrillConfiguration config = new MandrillConfiguration();
		config.setApiKey("MK-Rq283hJV6rmj9DPJxVQ");
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");

		MandrillRESTRequest restRequest = new MandrillRESTRequest();
		restRequest.setConfig(config);
		restRequest.setObjectMapper(mapper);
		restRequest.setHttpClient(client);
		return restRequest;
	}

}
