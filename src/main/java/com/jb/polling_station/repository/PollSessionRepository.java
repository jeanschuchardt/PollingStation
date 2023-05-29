package com.jb.polling_station.repository;

import com.jb.polling_station.entity.SessionPoll;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  PollSessionRepository  extends BaseRepository<SessionPoll>{
    
    
    List<SessionPoll> findAllByIsArchivedAndIsActive(Boolean isArchived, Boolean isActive);
}
