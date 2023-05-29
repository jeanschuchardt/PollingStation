package com.jb.polling_station.service;

import com.jb.polling_station.entity.Poll;
import com.jb.polling_station.entity.SessionPoll;
import com.jb.polling_station.entity.User;
import com.jb.polling_station.exception.ApiRequestException;
import com.jb.polling_station.record.PollSessionRequestDTO;
import com.jb.polling_station.record.VoteRequestDTO;
import com.jb.polling_station.repository.PollRepository;
import com.jb.polling_station.repository.PollSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PollService {
    
    private final PollRepository pollRepository;
    
    private final PollSessionRepository pollSessionRepository;
    
    private final UserService userService;
    
    public PollService(PollRepository pollRepository,
                       PollSessionRepository pollSessionRepository,
                       UserService userService) {
        this.pollRepository = pollRepository;
        this.pollSessionRepository = pollSessionRepository;
        this.userService = userService;
    }
    
    public SessionPoll createNewPollSession(PollSessionRequestDTO pollSessionRequestDTO) {
        
        SessionPoll sessionPoll = new SessionPoll()
                .setMeetingAgendaId(pollSessionRequestDTO.agendaId())
                .setStartTime(pollSessionRequestDTO.starDate());
        
        if (Objects.isNull(pollSessionRequestDTO.endDate())) {
            LocalDateTime localDateTime = pollSessionRequestDTO.starDate();
            localDateTime.plusMinutes(5);
            
        } else {
            sessionPoll.setEndTime(pollSessionRequestDTO.endDate());
        }
        
        return pollSessionRepository.save(sessionPoll);
    }
    
    public Poll vote(Long agendaId, Long sessionId, VoteRequestDTO voteRequestDTO) {
        SessionPoll sessionPoll = pollSessionRepository.findByIdAndIsArchived(sessionId.intValue(), false)
                                                       .orElseThrow(() -> new ApiRequestException("", HttpStatus.NOT_FOUND));
        
        if(!sessionPoll.getMeetingAgendaId().equals(agendaId)){
            throw new ApiRequestException("", HttpStatus.BAD_REQUEST);
        }
    
        User user = userService.getUser(Math.toIntExact(voteRequestDTO.userId()));
    
        if (Objects.isNull(sessionPoll.getIsActive()) && sessionPoll.getIsActive()) {
            Poll poll = new Poll()
                    .setSessionPollId(sessionId)
                    .setUserId(voteRequestDTO.userId())
                    .setInFavor(voteRequestDTO.inFavor());
            
            return pollRepository.save(poll);
            
        }
        throw new ApiRequestException("", HttpStatus.BAD_REQUEST);
    }
    
}

