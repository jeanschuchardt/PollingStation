package com.jb.polling_station.controller;


import com.jb.polling_station.entity.SessionPoll;
import com.jb.polling_station.record.PollSessionRequestDTO;
import com.jb.polling_station.record.SessionResult;
import com.jb.polling_station.record.VoteRequestDTO;
import com.jb.polling_station.service.PollService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollController {
    
    
    private final PollService pollService;
    
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }
    
    @PostMapping("/api/v1/poll")
    public SessionPoll createPollSession(PollSessionRequestDTO pollSessionRequestDTO) {
       return  pollService.createNewPollSession(pollSessionRequestDTO);
    }
    
    
    @PostMapping("/api/v1/agenda/{agendaId}/poll/{sessionId}")
    public void vote(Long agendaId, Long sessionId, VoteRequestDTO voteRequestDTO) {
        pollService.vote(agendaId,sessionId,voteRequestDTO);
    }
    
    @GetMapping("/api/v1/agenda/{agendaId}/poll/{sessionId}")
    public SessionResult getResult(Long agendaId, Long sessionId) {
        return pollService.getResults(agendaId, sessionId);
    }
    
    
    
}
