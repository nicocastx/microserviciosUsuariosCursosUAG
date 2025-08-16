package com.udemyjavamicro.springcloud.usuarios.msvcusuarios.services;

import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.models.entity.Usuario;
import com.udemyjavamicro.springcloud.usuarios.msvcusuarios.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> list() {
        return StreamSupport.stream(
                userRepo.findAll().spliterator(), false)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> porId(Long id) {
        return userRepo.findById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> guardar(Usuario usuario) {
        return Optional.of(userRepo.save(usuario));
    }

    @Override
    public void eliminar(Long id) {
        userRepo.deleteById(id);
    }

}
