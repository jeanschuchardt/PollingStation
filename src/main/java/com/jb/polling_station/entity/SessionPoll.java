package com.jb.polling_station.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
public class SessionPoll extends BaseEntity {
    @Column
    private Boolean isActive = true;
    
    @Column
    private LocalDateTime startTime;
    
    @Column
    private LocalDateTime endTime;
    
    
    @ManyToOne
    @JoinColumn(name = "meetingAgendaId", updatable = false, insertable = false)
    private MeetingAgenda meetingAgenda;
    
    @Column
    private Long meetingAgendaId;
    
    @OneToMany(mappedBy = "sessionPoll")
    private Set<Poll> poll = new HashSet<>();
    
    
}
