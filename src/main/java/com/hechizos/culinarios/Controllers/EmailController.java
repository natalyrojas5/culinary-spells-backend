package com.hechizos.culinarios.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.hechizos.culinarios.Dto.EmailDto.ChangePasswordDTO;
import com.hechizos.culinarios.Dto.EmailDto.EmailValuesDTO;
import com.hechizos.culinarios.Exception.GenericResponseRecord;
import com.hechizos.culinarios.Models.User;
import com.hechizos.culinarios.Services.UserService;
import com.hechizos.culinarios.Services.EmailService.EmailService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
@CrossOrigin
public class EmailController {
    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private static final String subject = "Cambio de Contraseña";

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) throws Exception {
        User usuarioOpt = userService.findOneByEmail(dto.getMailTo());
        if (usuarioOpt == null) {
            return ResponseEntity.ok(new GenericResponseRecord<>(403, "Forbidden", new ArrayList<>()));
        }
        dto.setMailFrom(mailFrom);
        dto.setMailTo(usuarioOpt.getEmail());
        dto.setSubject(subject);
        dto.setUserName(usuarioOpt.getName());
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        usuarioOpt.setTokenPassword(tokenPassword);
        userService.save(usuarioOpt);
        emailService.sendEmail(dto);
        return ResponseEntity
                .ok(new GenericResponseRecord<>(HttpStatus.OK.value(), "Email enviado", new ArrayList<>()));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult)
            throws Exception {
        if (bindingResult.hasErrors())
            return ResponseEntity.ok(new GenericResponseRecord<>(403, "Forbidden", new ArrayList<>()));
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            return ResponseEntity.ok(new GenericResponseRecord<>(403, "Forbidden", new ArrayList<>()));
        User usuarioOpt = userService.findByTokenPassword(dto.getTokenPassword());
        if (usuarioOpt == null)
            return ResponseEntity.ok(new GenericResponseRecord<>(403, "Forbidden", new ArrayList<>()));
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuarioOpt.setPassword(newPassword);
        usuarioOpt.setTokenPassword(null);
        userService.save(usuarioOpt);
        return ResponseEntity
                .ok(new GenericResponseRecord<>(HttpStatus.OK.value(), "Contraseña Actualizada", new ArrayList<>()));
    }

}
