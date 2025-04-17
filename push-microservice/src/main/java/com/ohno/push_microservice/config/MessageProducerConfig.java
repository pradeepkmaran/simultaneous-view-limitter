package com.ohno.push_microservice.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ohno.push_microservice.dto.WatchInfo.WatchInfoDto;
import lombok.Value;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
public class MessageProducerConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;

    @Bean
    public Map<String, Object> dtoProducerConfigs () {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, WatchInfoDto.Request> watchInfoProducerFactory () {
        return new DefaultKafkaProducerFactory<>(dtoProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, WatchInfoDto.Request> watchInfoKafkaTemplate () {
        return new KafkaTemplate<>(watchInfoProducerFactory());
    }
}
