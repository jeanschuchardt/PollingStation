package com.jb.polling_station.service;


import com.jb.polling_station.entity.Role;
import com.jb.polling_station.entity.User;
import com.jb.polling_station.exception.ApiRequestException;
import com.jb.polling_station.record.SimpleUserResponseDTO;
import com.jb.polling_station.record.UserRequestDTO;
import com.jb.polling_station.record.UserResponseDTO;
import com.jb.polling_station.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest servletRequest;

    
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(Role.USER);
        
        User save = saveUser(user);
        
        
        
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
        
    }
    
    public User updateUser(int userid, UserRequestDTO userRequest) {
        
        User user = getUser(userid);
    
        isUserAllowed(user);
    
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        if (Objects.nonNull(userRequest.password()) && userRequest.password().equals("")) {
            user.setPassword(passwordEncoder.encode(userRequest.password()));
        }
        
        return saveUser(user);
    }
    
    private void isUserAllowed(User user) {
        if(!userIsAuthority(user)){
            throw new ApiRequestException("You can not execute this action.",HttpStatus.FORBIDDEN);
        }
    }
    
    private boolean userIsAuthority(User user) {
        User userPrincipal = getAuthenticatedUser();
        return user.getId().equals(userPrincipal.getId());
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
    
    public User getAuthenticatedUser() {
        User userPrincipal =
                (User) ((UsernamePasswordAuthenticationToken)servletRequest.getUserPrincipal()).getPrincipal();
        return userPrincipal;
    }
}
