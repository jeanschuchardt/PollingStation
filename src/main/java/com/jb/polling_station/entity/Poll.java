package com.jb.polling_station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Poll extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "userId", updatable = false, insertable = false)
    private User user;
    
    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private Boolean inFavor;
    
    @ManyToOne
    @JoinColumn(name = "sessionPollId", updatable = false, insertable = false)
    private SessionPoll sessionPoll;
    
    @Column
    private Long sessionPollId;
}
