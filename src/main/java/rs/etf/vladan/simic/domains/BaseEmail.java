package rs.etf.vladan.simic.domains;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "email_notification")
@Getter
@Setter
@NoArgsConstructor
public class BaseEmail implements Serializable {

    private static final long serialVersionUID = -787804141106616684L;

    @Id
    private String id;

    private String subject;

    private Date date = Calendar.getInstance().getTime();

    private boolean sent = false;

    private String recipient;

    private String userId;

    public BaseEmail(String subject, String recipient, String userId) {
	super();
	this.subject = subject;
	this.recipient = recipient;
	this.userId = userId;
    }

}
