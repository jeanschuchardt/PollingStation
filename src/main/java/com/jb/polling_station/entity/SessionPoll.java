package com.jb.polling_station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

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
    
    
}
