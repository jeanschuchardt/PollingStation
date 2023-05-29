package com.jb.polling_station.service;


import com.jb.polling_station.entity.User;
import com.jb.polling_station.exception.ApiRequestException;
import com.jb.polling_station.record.SimpleUserResponseDTO;
import com.jb.polling_station.record.UserRequestDTO;
import com.jb.polling_station.record.UserResponseDTO;
import com.jb.polling_station.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setCpf(userRequest.cpf());
        
        User save = saveUser(user);
        
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
        
    }
    
    public User updateUser(int userid, UserRequestDTO userRequest) {
        
        User user = getUser(userid);
        
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
      
        
        return saveUser(user);
    }
    

    private User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ApiRequestException("Error to update user  " + user.getEmail() + ".", HttpStatus.BAD_REQUEST);
        }
    }
    
    public User getUser(int userid) {
        User user = userRepository.findByIdAndIsArchived(userid, false)
                                  .orElseThrow(() -> new ApiRequestException("", HttpStatus.NOT_FOUND));
        return user;
    }
    
    
    public SimpleUserResponseDTO mapResponse(User user) {
        SimpleUserResponseDTO simpleUserResponseDTO = new SimpleUserResponseDTO(user.getId(), user.getName(), user.getEmail());
        return simpleUserResponseDTO;
    }
    

}
