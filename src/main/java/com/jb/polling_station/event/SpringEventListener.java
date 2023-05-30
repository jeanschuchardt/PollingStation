package com.jb.polling_station.event;



import com.jb.polling_station.entity.events.Event;
import com.jb.polling_station.entity.events.PollEvent;
import com.jb.polling_station.entity.handler.PollEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//FIXME: Clean up all the listener/publisher structure so it won't rely on spring in case we want to user an real async pub/sub

@Component
public class SpringEventListener implements com.jb.polling_station.event.EventListener{

    @Autowired
    PollEventHandler pollEventHandler;

    @EventListener(condition = "#event.success")
    public void handleEvent(Event event) {

        if (event.getClass() == PollEvent.class) {
            pollEventHandler.handle(event);
        }
    }
    
}
