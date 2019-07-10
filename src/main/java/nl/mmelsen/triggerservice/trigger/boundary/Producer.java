package nl.mmelsen.triggerservice.trigger.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@EnableBinding(EventsBinding.class)
public class Producer {

//    @Autowired
    private EventsBinding eventsBinding;
    private final long timeoutInMs;

    public Producer(
                    @Value("${producer.timeoutinms}") long timeoutInMs) {
        this.timeoutInMs = timeoutInMs;
    }

    public void send(String content) {
        Message<String> message = MessageBuilder
                .withPayload(content)
                .setHeader(KafkaHeaders.MESSAGE_KEY, "test-key")
                .build();

        failSafeMessageSend(message);
    }

    private void failSafeMessageSend(Message<String> message) {
        boolean sendSucceeded = false;
        do {
            try {
                this.eventsBinding.eventsOut().send(message, this.timeoutInMs);
                sendSucceeded = true;
            }
            catch (Exception ex) {
                log.error("Exception when sending message: {}", ex.getMessage());
            }
        }
        while (!sendSucceeded);
    }
}
