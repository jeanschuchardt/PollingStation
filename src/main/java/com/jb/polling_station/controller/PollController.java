package com.jb.polling_station.controller;


import com.jb.polling_station.entity.SessionPoll;
import com.jb.polling_station.record.PollSessionRequestDTO;
import com.jb.polling_station.record.SessionResult;
import com.jb.polling_station.record.VoteRequestDTO;
import com.jb.polling_station.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollController {
    
    
    private final PollService pollService;
    
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }
    
    
    @Operation(summary = "Create a new poll session" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PostMapping("/api/v1/poll")
    public SessionPoll createPollSession(PollSessionRequestDTO pollSessionRequestDTO) {
       return  pollService.createNewPollSession(pollSessionRequestDTO);
    }
    
    
    @Operation(summary = "add a vote" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PostMapping("/api/v1/agenda/{agendaId}/poll/{sessionId}")
    public void vote(Long agendaId, Long sessionId, VoteRequestDTO voteRequestDTO) {
        pollService.vote(agendaId,sessionId,voteRequestDTO);
    }
    
    
    @Operation(summary = "get result" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @GetMapping("/api/v1/agenda/{agendaId}/poll/{sessionId}")
    public SessionResult getResult(Long agendaId, Long sessionId) {
        return pollService.getResults(agendaId, sessionId);
    }
    
    
    
}
