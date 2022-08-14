package dev.com.springwebfluxkafka.controller;

import dev.com.springwebfluxkafka.dto.WeatherInfoEvent;
import dev.com.springwebfluxkafka.helper.WeatherInfoEventListener;
import dev.com.springwebfluxkafka.service.WeatherInfoEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api")
public class WeatherInfoController {

    @Autowired
    private WeatherInfoEventProcessor processor;

    private Flux<WeatherInfoEvent> bridge;

    public WeatherInfoController() {
        // (3) Broadcast to several subscribers
        this.bridge = createBridge().publish().autoConnect().cache(10).log();
    }

    // (1) Spring MVC annotation
    @GetMapping(value = "/weather", produces = "text/event-stream;charset=UTF-8")
    public Flux<WeatherInfoEvent> getWeatherInfo() {

        return bridge;
    }

    private Flux<WeatherInfoEvent> createBridge() {
        Flux<WeatherInfoEvent> bridge = Flux.create(sink -> { // (2)
            processor.register(new WeatherInfoEventListener() {

                @Override
                public void processComplete() {
                    sink.complete();
                }

                @Override
                public void onData(WeatherInfoEvent data) {
                    sink.next(data);
                }
            });
        });
        return bridge;
    }
}
