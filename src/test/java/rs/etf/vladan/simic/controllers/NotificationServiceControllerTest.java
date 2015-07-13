package rs.etf.vladan.simic.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import rs.etf.vladan.simic.configuration.NotificationServiceConfiguration;
import rs.etf.vladan.simic.requests.notifications.request.VerificationEmailRequest;
import rs.etf.vladan.simic.services.impl.MailServiceImpl;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class NotificationServiceControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    NotificationServiceController notificationController;

    @Mock
    MailServiceImpl mailService;

    private ObjectMapper mapper = null;

    private String convertObjectToJson(Object objectToConvertToJson) throws JsonProcessingException {
	mapper.setSerializationInclusion(Include.NON_NULL);
	String writeValueAsString = mapper.writeValueAsString(objectToConvertToJson);
	log.info("Convertet object {} ", writeValueAsString);
	return writeValueAsString;
    }

    @Before
    public void setupTest() {
	mapper = new ObjectMapper();
	MockitoAnnotations.initMocks(this);
	this.mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void testNotficationControllerResponse() throws Exception {
	mockMvc.perform(post("/api/notification/update").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andDo(print());
    }

    @Test
    public void testMailNotificationRequest() throws Exception {

	when(mailService.sendEmail(Mockito.any(VerificationEmailRequest.class))).thenReturn(
		NotificationServiceConfiguration.mailSentResponseStatus());

	mockMvc.perform(
		post("/api/notification/verify").contentType(MediaType.APPLICATION_JSON).content(
			convertObjectToJson(NotificationServiceConfiguration.submitEmailRequest())))
		.andExpect(status().is2xxSuccessful())
		.andExpect(
			content().string(
				(convertObjectToJson(NotificationServiceConfiguration
					.mailSentResponseStatus()))))
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andDo(print());
    }
    
    @Test
    public void testBadSubmitedRequest() throws Exception {

	mockMvc.perform(
		post("/api/notification/verify").contentType(MediaType.APPLICATION_JSON).content(
			convertObjectToJson(NotificationServiceConfiguration.submitInvalidEmailRequest())))
		.andExpect(status().is4xxClientError())
		.andDo(print());
    }
}
