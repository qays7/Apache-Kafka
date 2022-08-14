package dev.com.springwebfluxkafka.service;

import dev.com.springwebfluxkafka.dto.WeatherInfoEvent;
import dev.com.springwebfluxkafka.helper.WeatherInfoEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class WeatherInfoEventProcessor {

    private WeatherInfoEventListener listener;

    public void register(WeatherInfoEventListener listener) {
        this.listener = listener;
    }

    public void onEvent(WeatherInfoEvent event) {
        if (listener != null) {
            listener.onData(event);
        }
    }

    public void onComplete() {
        if (listener != null) {
            listener.processComplete();
        }
    }

    @KafkaListener(topics = "weather", groupId = "group_id")
    public void consume(WeatherInfoEvent message) throws IOException {
        log.debug("#### -> Consumed message -> {}" , message);
        onEvent(message);
    }
}