package com.jb.polling_station.controller;

import com.jb.polling_station.entity.MeetingAgenda;
import com.jb.polling_station.record.MeetingAgendaRequestDTO;
import com.jb.polling_station.service.MeetingAgendaService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetingAgendaController {
    
    public final MeetingAgendaService meetingAgendaService;
    
    
    public MeetingAgendaController(MeetingAgendaService meetingAgendaService) {
        this.meetingAgendaService = meetingAgendaService;
    }
    
    
    @PostMapping("/api/v1/meeting-agenda")
    public MeetingAgenda createMeetingAgenda(MeetingAgendaRequestDTO meetingAgendaRequestDTO) {
        return meetingAgendaService.createAgenda(meetingAgendaRequestDTO);
    }
    
    @DeleteMapping("/api/v1/meeting-agenda/{id}")
    public void deleteAgenda(int id) {
        meetingAgendaService.delete(id);
    }
    
    @GetMapping("/api/v1/meeting-agenda")
    public List<MeetingAgenda> getAll( ) {
        return meetingAgendaService.getAll();
    }
    
    @GetMapping("/api/v1/meeting-agenda/{id}")
    public MeetingAgenda getById(int id ) {
        return meetingAgendaService.getAgendaById(id);
    }
    
}
