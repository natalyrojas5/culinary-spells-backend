package com.hechizos.culinarios.Services;

import java.util.List;

import com.hechizos.culinarios.Models.User;

public interface UserService extends ICrud<User, Long> {
    User findOneByEmail(String email);

    List<User> findByEmailLike(String email);

    User findByIdUser(Long idUser);
}
