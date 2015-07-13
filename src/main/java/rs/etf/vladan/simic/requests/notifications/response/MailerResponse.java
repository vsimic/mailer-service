package rs.etf.vladan.simic.requests.notifications.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MailerResponse {
	@Setter
	private MailSendingStatus mailStatus;

	public MailerResponse(MailSendingStatus mailStatus) {
		super();
		this.mailStatus = mailStatus;
	}

}
