package dev.com.springwebfluxkafka.helper;

import dev.com.springwebfluxkafka.dto.WeatherInfoEvent;

public interface WeatherInfoEventListener {
    void onData(WeatherInfoEvent event);
    void processComplete();
}
