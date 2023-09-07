package com.happiest.minds.sftpapplication.utility;

import com.happiest.minds.sftpapplication.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static com.happiest.minds.sftpapplication.enums.Constants.EXCEPTION;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaUtility {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishToKafka(Object message, String topic) {
        try {
            Message<Object> payments = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, topic).build();
            kafkaTemplate.send(payments);
        } catch (Exception e) {
            log.error(EXCEPTION.getValue(), e);
        }
    }

}
