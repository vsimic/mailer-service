package rs.etf.vladan.simic.domain;

import lombok.Getter;

@Getter
public class RegistrationMailNotification {
    private String recipientFirstName;
    private String recipientLastName;
    private String recipientEmailAddress;

    public RegistrationMailNotification(
	    RegistrationMailNotificationBuilder registrationMailNotificationBuilder) {
	this.recipientEmailAddress = registrationMailNotificationBuilder.recipientEmailAddress;
	this.recipientFirstName = registrationMailNotificationBuilder.recipientFirstName;
	this.recipientLastName = registrationMailNotificationBuilder.recipientLastName;
    }

    public static class RegistrationMailNotificationBuilder {

	private String recipientFirstName;
	private String recipientLastName;
	private String recipientEmailAddress;

	public RegistrationMailNotificationBuilder(String firstName, String lastName, String emailAddress) {
	    this.recipientEmailAddress = emailAddress;
	    this.recipientFirstName = firstName;
	    this.recipientLastName = lastName;
	}

	public RegistrationMailNotification create() {
	    return new RegistrationMailNotification(this);
	}
    }

}
