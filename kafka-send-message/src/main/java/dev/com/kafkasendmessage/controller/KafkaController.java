package dev.com.kafkasendmessage.controller;

import dev.com.kafkasendmessage.engine.Producer;
import dev.com.kafkasendmessage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "kafka")
public class KafkaController {

    @Autowired
    private  Producer producer;


    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody User user) {
        this.producer.sendMessage(user);
    }
}
