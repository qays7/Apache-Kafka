package dev.com.springwebfluxkafka.service;

import dev.com.springwebfluxkafka.dto.WeatherInfoEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;

@Slf4j
@Service
public class WeatherInfoService {

    @Autowired
    private KafkaTemplate<String, WeatherInfoEvent> kafkaTemplate;

    public ListenableFuture<SendResult<String, WeatherInfoEvent>> sendMessage(String topic, WeatherInfoEvent message) {
        log.debug("#### -> Producing message -> {}" , message);
        return this.kafkaTemplate.send(topic, message);
    }

    @Scheduled(fixedDelay = 5000)
    public void getWeatherInfoJob() throws IOException {
        log.debug("generate fake weather event");
        WeatherInfoEvent event = new WeatherInfoEvent(RandomUtils.nextLong(0, 100), RandomUtils.nextInt(16, 30));
        sendMessage("weather", event);
    }
}
