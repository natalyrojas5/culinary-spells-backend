package com.hechizos.culinarios.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.hechizos.culinarios.Auth.JwtRequest;
import com.hechizos.culinarios.Auth.JwtResponse;
import com.hechizos.culinarios.Auth.JwtTokenUtil;
import com.hechizos.culinarios.Dto.UserDto;
import com.hechizos.culinarios.Models.User;
import com.hechizos.culinarios.Services.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto) throws Exception {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userService.save(convertToEntity(userDto));
        return new ResponseEntity<>(convertToDto(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest req) throws Exception {
        authenticate(req.getEmail(), req.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final Date expiresAt = jwtTokenUtil.getExpirationDateFromToken(token);
        UserDto user = convertToDto(userService.findOneByEmail(req.getEmail()));
        return new ResponseEntity<>(new JwtResponse(token, expiresAt, user), HttpStatus.OK);
    }

    private ResponseEntity<String> authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        return ResponseEntity.ok().body("Authentication successful");
    }

    private UserDto convertToDto(User obj) {
        return modelMapper.map(obj, UserDto.class);
    }

    private User convertToEntity(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }
}
