package com.jb.polling_station.controller;

import com.jb.polling_station.entity.MeetingAgenda;
import com.jb.polling_station.record.MeetingAgendaRequestDTO;
import com.jb.polling_station.service.MeetingAgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListController {
    
    private final MeetingAgendaService meetingAgendaService;
    
    @Operation(summary = "Get all lists", description = "It will return all lists that belongs to the authenticated " +
            "user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @GetMapping("/api/v1/lists")
    public List<MeetingAgenda> getListById() {
        List<MeetingAgenda> all = meetingAgendaService.getAll();
        return all;
    }
    
    @Operation(summary = "Get list by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @GetMapping("/api/v1/lists/{id}")
    public MeetingAgenda getListById(@PathVariable int id) {
        MeetingAgenda listById = meetingAgendaService.getListById(id);
        return listById;
    }
    
    @Operation(summary = "Create new list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @PostMapping("/api/v1/lists")
    public MeetingAgenda post(@RequestBody MeetingAgendaRequestDTO requestDTO) {
        MeetingAgenda list = meetingAgendaService.createList(requestDTO);
        return list;
    }
    
    @Operation(summary = "Update list", description = "Only the owner can update the list, any other user will fail " +
            "the execution.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @DeleteMapping("/api/v1/lists/{id}")
    public void delete(@PathVariable int id) {
        meetingAgendaService.delete(id);
        
    }
}
