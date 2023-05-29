package com.jb.polling_station.repository;

import com.jb.polling_station.entity.Poll;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PollRepository extends BaseRepository<Poll> {
    
    
    Optional<Poll> findBySessionPollIdAndUserId(Long sessionId, Long userId);
    List<Poll> findAllByIsArchivedAndSessionPollId(boolean isArchived, Long sessionPollId);
}
