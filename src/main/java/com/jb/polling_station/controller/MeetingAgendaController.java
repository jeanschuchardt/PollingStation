package com.jb.polling_station.controller;

import com.jb.polling_station.entity.MeetingAgenda;
import com.jb.polling_station.record.MeetingAgendaRequestDTO;
import com.jb.polling_station.service.MeetingAgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    
    @Operation(summary = "Create new meeting agenda")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Meeting agenda created", content = @Content),
                           @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
    
    @PostMapping("/api/v1/meeting-agenda")
    public MeetingAgenda createMeetingAgenda(MeetingAgendaRequestDTO meetingAgendaRequestDTO) {
        return meetingAgendaService.createAgenda(meetingAgendaRequestDTO);
    }
    
    @Operation(summary = "Delete agenda")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                           @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
    @DeleteMapping("/api/v1/meeting-agenda/{id}")
    public void deleteAgenda(int id) {
        meetingAgendaService.delete(id);
    }
    
    @Operation(summary = "Get All agenda")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                           @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                           @ApiResponse(responseCode = "204", description = "No content", content = @Content)})
    @GetMapping("/api/v1/meeting-agenda")
    public List<MeetingAgenda> getAll() {
        return meetingAgendaService.getAll();
    }
    
    @Operation(summary = "Get agenda by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                           @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                           @ApiResponse(responseCode = "204", description = "No content", content = @Content),
                           @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    @GetMapping("/api/v1/meeting-agenda/{id}")
    public MeetingAgenda getById(int id) {
        return meetingAgendaService.getAgendaById(id);
    }
    
}
