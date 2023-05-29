package com.jb.polling_station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
public class MeetingAgenda extends BaseEntity {
    @Column
    private String name;
    
    @Column
    private String description;
    
    @Column
    private Long userId;
   
    @OneToMany(mappedBy = "meetingAgenda")
    private Set<SessionPoll> sessionPolls = new HashSet<>();
   
   

}
