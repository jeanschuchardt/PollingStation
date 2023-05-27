package com.jb.polling_station.repository;

import com.jb.ItemService.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends BaseRepository<User> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByEmailAndIsArchived(String email, boolean isArchived);
    
    
}
