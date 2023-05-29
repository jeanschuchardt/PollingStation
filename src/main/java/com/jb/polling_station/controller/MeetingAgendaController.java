package com.jb.polling_station.controller;

import com.jb.polling_station.record.MeetingAgendaRequestDTO;
import com.jb.polling_station.service.MeetingAgendaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingAgendaController {
    
    public final MeetingAgendaService meetingAgendaService;
    
    
    public MeetingAgendaController(MeetingAgendaService meetingAgendaService) {
        this.meetingAgendaService = meetingAgendaService;
    }
    
  
    public void createMeetingAgenda(MeetingAgendaRequestDTO meetingAgendaRequestDTO){
        meetingAgendaService.createAgenda(meetingAgendaRequestDTO);
    }
}
