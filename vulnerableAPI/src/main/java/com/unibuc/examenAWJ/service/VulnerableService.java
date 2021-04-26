package com.unibuc.examenAWJ.service;

import com.unibuc.examenAWJ.dto.AuthRequest;
import com.unibuc.examenAWJ.dto.AuthResponse;
import com.unibuc.examenAWJ.model.User;
import com.unibuc.examenAWJ.repository.VulnerableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VulnerableService {
    @Autowired
    VulnerableRepository vulnerableRepository;

    public User createUser(User user){

        return vulnerableRepository.createUser(user);
    }

    public AuthResponse authorize(String username, String password){
        User user;
        try {
            user = vulnerableRepository.getUser(username, password);
        } catch (Exception e){
            String executedQuery = "Authorization failed. Executed query against the db: SELECT * FROM users WHERE user == '" + username + "' AND pass ='" + password + "' LIMIT 1";
            return new AuthResponse(executedQuery, false);
        }
        String executedQuery = "Authorization worked. Executed query against the db: SELECT * FROM users WHERE user == '" + username + "' AND pass ='" + password + "' LIMIT 1";
        return new AuthResponse(executedQuery, true);
    }
}
