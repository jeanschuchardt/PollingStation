package com.jb.polling_station.controller;

import com.jb.polling_station.record.TextMessageDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CommonsLog
public class WebSocketTextController {
    
    @Autowired
    SimpMessagingTemplate template;
    
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody TextMessageDTO textMessageDTO) {
        template.convertAndSend("/topic/message", textMessageDTO);
        log.warn("test");
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessageDTO textMessageDTO) {
        // receive message from client
    }
    
    
    @SendTo("/topic/message")
    public TextMessageDTO broadcastMessage(@Payload TextMessageDTO string) {
        log.warn(string);
        return string;
    }
}