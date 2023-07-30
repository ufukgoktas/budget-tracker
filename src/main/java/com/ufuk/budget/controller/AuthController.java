package com.ufuk.budget.controller;

import com.ufuk.budget.entity.Role;
import com.ufuk.budget.entity.User;
import com.ufuk.budget.model.dto.AuthResponseDto;
import com.ufuk.budget.model.dto.LoginDto;
import com.ufuk.budget.model.dto.RegisterDto;
import com.ufuk.budget.repository.RoleRepository;
import com.ufuk.budget.repository.UserRepository;
import com.ufuk.budget.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

@Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("login")
    public  ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
    Authentication authentication =authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
    );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token),HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
    if (userRepository.existsByEmail(registerDto.getUsername())){
        return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
    }
    User user = new User();
    user.setName(registerDto.getName());
    user.setEmail(registerDto.getUsername());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
        return  new ResponseEntity<>("user register success",HttpStatus.OK);

    }
}
