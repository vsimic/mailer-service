package rs.etf.vladan.simic.requests.integrations.events.subscribe_request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.etf.vladan.simic.requests.integrations.events.EventType;

@Getter
@Setter
@NoArgsConstructor
public class EventSubscribeRequest {

    @NotNull
    @Size(min = 1)
    private String subscriberName;
    @NotNull
    @Size(min = 1)
    private String subscriberHook;
    @Size(min = 1)
    @NotNull
    private EventType eventToSubscriberFor;

}
