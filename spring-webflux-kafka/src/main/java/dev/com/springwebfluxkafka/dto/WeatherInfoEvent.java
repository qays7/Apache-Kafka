package dev.com.springwebfluxkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherInfoEvent {

    private long stationId;
    private int temperature;

}