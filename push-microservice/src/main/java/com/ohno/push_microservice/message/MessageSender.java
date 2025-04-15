package com.ohno.push_microservice.message;

import com.ohno.push_microservice.dto.WatchInfo.WatchInfoDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
    private final KafkaTemplate<String, WatchInfoDto.Request> watchInfoKafkaTemplate;

    public void sendMessage (String topic, WatchInfoDto.Request dto) {
        watchInfoKafkaTemplate.send(topic, dto);
        logger.info(topic + " produced.");
    }
}
