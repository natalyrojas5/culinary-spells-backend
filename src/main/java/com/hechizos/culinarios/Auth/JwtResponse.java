package com.hechizos.culinarios.Auth;

import java.util.Date;

import com.hechizos.culinarios.Dto.UserDto;

public record JwtResponse(String jwtToken, Date expiresAt, UserDto user) {
}
