package rs.etf.vladan.simic.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationEmail extends BaseEmail {

    private static final long serialVersionUID = 8642925290427082743L;

    public VerificationEmail(String subject, String recipient, String userId) {
	super(subject, recipient, userId);
    }

}
