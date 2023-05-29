package com.jb.polling_station.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

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
