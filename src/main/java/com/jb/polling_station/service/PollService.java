package com.jb.polling_station.service;

import com.jb.polling_station.entity.Poll;
import com.jb.polling_station.entity.SessionPoll;
import com.jb.polling_station.entity.User;
import com.jb.polling_station.exception.ApiRequestException;
import com.jb.polling_station.record.PollSessionRequestDTO;
import com.jb.polling_station.record.SessionResult;
import com.jb.polling_station.record.VoteRequestDTO;
import com.jb.polling_station.repository.PollRepository;
import com.jb.polling_station.repository.PollSessionRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@CommonsLog
@EnableScheduling
public class PollService {
    private final long SECOND = 1000;
    private final long MINUTE = SECOND * 60;
    private final long HOUR = MINUTE * 60;
    private final PollRepository pollRepository;
    
    private final PollSessionRepository pollSessionRepository;
    
    private final UserService userService;
    
    
    public PollService(PollRepository pollRepository,
                       PollSessionRepository pollSessionRepository,
                       UserService userService
    ) {
        this.pollRepository = pollRepository;
        this.pollSessionRepository = pollSessionRepository;
        this.userService = userService;
        
        
    }
    
    public SessionPoll createNewPollSession(PollSessionRequestDTO pollSessionRequestDTO) {
        
        SessionPoll sessionPoll = new SessionPoll()
                .setMeetingAgendaId(pollSessionRequestDTO.getAgendaId())
                .setStartTime(pollSessionRequestDTO.getStarDate());
        
        if (Objects.isNull(pollSessionRequestDTO.getEndDate())) {
            LocalDateTime localDateTime = pollSessionRequestDTO.getStarDate();
            localDateTime.plusMinutes(5);
            
        } else {
            sessionPoll.setEndTime(pollSessionRequestDTO.getEndDate());
        }
        
        return pollSessionRepository.save(sessionPoll);
    }
    
    public Poll vote(Long agendaId, Long sessionId, VoteRequestDTO voteRequestDTO) {
        SessionPoll sessionPoll = pollSessionRepository.findByIdAndIsArchived(sessionId.intValue(), false)
                                                       .orElseThrow(() -> new ApiRequestException("", HttpStatus.NOT_FOUND));
        
        if (!sessionPoll.getMeetingAgendaId().equals(agendaId)) {
            throw new ApiRequestException("", HttpStatus.BAD_REQUEST);
        }
        
        User user = userService.getUser(Math.toIntExact(voteRequestDTO.userId()));
        if(pollRepository.findBySessionPollIdAndUserId(sessionId,voteRequestDTO.userId()).isPresent()){
            throw new ApiRequestException("User are voted", HttpStatus.BAD_REQUEST);
        }
        
        if (Objects.nonNull(sessionPoll.getIsActive()) && sessionPoll.getIsActive()) {
            Poll poll = new Poll()
                    .setSessionPollId(sessionId)
                    .setUserId(voteRequestDTO.userId())
                    .setInFavor(voteRequestDTO.inFavor());
            
            return pollRepository.save(poll);
            
        }
        
        throw new ApiRequestException("Session is closed", HttpStatus.BAD_REQUEST);
    }
    
    @Scheduled(fixedDelay = SECOND)
    public void tryToCloseAllSession() {
        log.warn("try to close vote session");
        List<SessionPoll> sessionPolls = pollSessionRepository.findAllByIsArchivedAndIsActive(false, true);
        
        if (!sessionPolls.isEmpty()) {
            for (SessionPoll sessionPoll : sessionPolls) {
                tryToCloseSession(sessionPoll);
            }
        }
        
    }
    
    private void tryToCloseSession(SessionPoll sessionPoll) {
        LocalDateTime endTime = sessionPoll.getEndTime();
        LocalDateTime now = LocalDateTime.now();
        
        if (now.isAfter(endTime)) {
            sessionPoll.setIsActive(false);
            log.info("vote session id" + sessionPoll.getId() + " is closed.");
            pollSessionRepository.save(sessionPoll);
        }
        
    }
    
    public SessionResult getResults(Long agendaId, Long sessionId) {
        SessionPoll sessionPoll = pollSessionRepository.findByIdAndIsArchived(sessionId.intValue(), false)
                                                       .orElseThrow(() -> new ApiRequestException("", HttpStatus.NOT_FOUND));
        
        if (!sessionPoll.getMeetingAgendaId().equals(agendaId)) {
            throw new ApiRequestException("", HttpStatus.BAD_REQUEST);
        }
        
        List<Poll> polls = pollRepository.findAllByIsArchivedAndSessionPollId(false, sessionPoll.getId());
    
        Long inFavor = polls.stream().filter(i -> i.getInFavor().equals(true)).count();
        Long against  = polls.stream().filter(i -> i.getInFavor().equals(false)).count();
    
    
        return new SessionResult(agendaId.intValue(),sessionId.intValue(),inFavor.intValue(),against.intValue());
    
    }
}

