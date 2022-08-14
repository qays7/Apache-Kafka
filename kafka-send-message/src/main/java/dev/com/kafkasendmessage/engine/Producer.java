package dev.com.kafkasendmessage.engine;

import dev.com.kafkasendmessage.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer {

    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.debug("#### -> Producing message -> {}", message);
        this.kafkaTemplate.send(TOPIC, message);
    }

    public void sendMessage(User user) {
        log.debug("#### -> Producing message -> {}", user);
        this.kafkaTemplate.send(TOPIC, user.toString());
    }

}
