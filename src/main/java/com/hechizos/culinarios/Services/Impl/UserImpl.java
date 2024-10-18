package com.hechizos.culinarios.Services.Impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.hechizos.culinarios.Models.User;
import com.hechizos.culinarios.Repositories.GenericRepository;
import com.hechizos.culinarios.Repositories.UserRepository;
import com.hechizos.culinarios.Services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserImpl extends CrudImpl<User, Long> implements UserService {

    private final UserRepository userRepository;

    @Override
    protected GenericRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> findByEmailLike(String email) {
        return userRepository.findByEmailLike("%" + email + "%");
    }

    @Override
    public User findByIdUser(Long idUser) {
        return userRepository.findByIdUser(idUser);
    }

    @Override
    public User findByTokenPassword(String token) {
        return userRepository.findByTokenPassword(token);
    }
}
