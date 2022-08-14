package dev.com.kafkasendmessage.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class Consumer {


    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message)  {
        log.debug("#### -> Consumed message -> {}", message);
    }
}