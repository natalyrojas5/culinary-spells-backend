package com.hechizos.culinarios.Repositories;

import java.util.List;
import com.hechizos.culinarios.Models.User;

public interface UserRepository extends GenericRepository<User, Long> {
    User findOneByEmail(String email);

    List<User> findByEmailLike(String email);

    User findByIdUser(Long idUser);

    User findByTokenPassword(String token);
}
