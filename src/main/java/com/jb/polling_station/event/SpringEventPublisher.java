package com.jb.polling_station.event;


import com.jb.polling_station.entity.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventPublisher implements EventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
