package rs.etf.vladan.simic.requests.notifications.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VerificationEmailRequest {

    @NotNull
    @Size(min=1)
    private String emailSubject;
    @NotNull
    @Size(min=1)
    private String emailSender;
    @NotNull
    @Size(min=1)
    private String recipientEmail;
    @NotNull
    @Size(min=1)
    private String userId;
    @Size(min=1)
    @NotNull
    private String activationCode;

    public VerificationEmailRequest(String emailSubject, String emailSender, String recipientEmail,
	    String userId, String activationCode) {
	super();
	this.emailSubject = emailSubject;
	this.emailSender = emailSender;
	this.recipientEmail = recipientEmail;
	this.userId = userId;
	this.activationCode = activationCode;
    }

}
