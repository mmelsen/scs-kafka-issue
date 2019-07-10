package nl.mmelsen.triggerservice.trigger.boundary;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import static nl.mmelsen.triggerservice.trigger.boundary.EventsBindingConsts.EVENTS_OUT;

public interface EventsBinding {

    @Output(EVENTS_OUT)
    MessageChannel eventsOut();
}