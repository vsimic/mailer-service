package rs.etf.vladan.simic.requests.integrations.events;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Getter
@Setter
@NoArgsConstructor
public class Event {


    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "contentType")
    @JsonSubTypes({ @JsonSubTypes.Type(value = UserCreatedEventData.class, name = "USER_CREATED") })
    private EventData eventData;
    
    @JsonTypeId
    private EventType contentType;
    
}
