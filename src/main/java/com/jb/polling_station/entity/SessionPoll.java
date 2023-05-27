package com.jb.polling_station.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class SessionPoll extends BaseEntity {
    @Column
    private Boolean isActive;
    
    @Column
    private Date startTime;
    
    @Column
    private Date endTime;
    
    
    @ManyToOne
    @JoinColumn(name = "meetingAgendaId", updatable = false, insertable = false)
    private MeetingAgenda meetingAgenda;
    
    @Column
    private Long meetingAgendaId;
    
    @OneToMany(mappedBy = "sessionPoll")
    private Set<Poll> poll = new HashSet<>();
    
    
}
