package rs.etf.vladan.simic.requests.integrations.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreatedEventData implements EventData {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public UserCreatedEventData(String id, String firstName, String lastName, String username, String email) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.email = email;
    }

}
