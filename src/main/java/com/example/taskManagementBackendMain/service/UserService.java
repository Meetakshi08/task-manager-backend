package com.example.taskManagementBackendMain.service;


import com.example.taskManagementBackendMain.entity.User;
import com.example.taskManagementBackendMain.repository.UserRepository;
import com.example.taskManagementBackendMain.service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Signup
    public String signUp(User user){

        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new RuntimeException("Email is empty");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User registered successfully";
    }

    // Login with JWT
    public String logIn(User user){

        System.out.println("Entered Email: " + user.getEmail());
        System.out.println("Entered Password: " + user.getPassword());

        User existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser == null){
            System.out.println("User not found");
            throw new RuntimeException("User not found");
        }

        System.out.println("Database Password: " + existingUser.getPassword());

        boolean isMatch = passwordEncoder.matches(
                user.getPassword(),
                existingUser.getPassword()
        );

        System.out.println("Password Match: " + isMatch);

        if(!isMatch){
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(existingUser.getEmail());

        return token;
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}