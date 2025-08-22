package com.udemyjavamicro.springcloud.msvccursos.services;

import com.udemyjavamicro.springcloud.msvccursos.clients.UsuarioFeignClients;
import com.udemyjavamicro.springcloud.msvccursos.models.Usuario;
import com.udemyjavamicro.springcloud.msvccursos.models.entity.Curso;
import com.udemyjavamicro.springcloud.msvccursos.models.entity.CursoUsuario;
import com.udemyjavamicro.springcloud.msvccursos.repositories.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {

    private CursoRepository cursoRepository;
    private UsuarioFeignClients clientUsuario;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (cursoRepository.findById(id).isPresent()) {
            cursoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> curso = cursoRepository.findById(idCurso);
        if (curso.isPresent()) {
            Usuario usuarioFound = clientUsuario.buscar(usuario.getId());
            Curso cso = curso.get();
            CursoUsuario cu = new CursoUsuario();
            cu.setUsuarioId(usuarioFound.getId());
            cso.addCursoUsuario(cu);

            cursoRepository.save(curso.get());
            return Optional.of(usuarioFound);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> curso = cursoRepository.findById(idCurso);
        if (curso.isPresent()) {
            Usuario usuarioCreated = clientUsuario.crear(usuario);
            Curso cso = curso.get();
            CursoUsuario cu = new CursoUsuario();
            cu.setUsuarioId(usuarioCreated.getId());
            cso.addCursoUsuario(cu);

            cursoRepository.save(curso.get());
            return Optional.of(usuarioCreated);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> curso = cursoRepository.findById(idCurso);
        if (curso.isPresent()) {
            Usuario usuarioDel = clientUsuario.buscar(usuario.getId());
            Curso cso = curso.get();
            CursoUsuario cu = new CursoUsuario();
            cu.setUsuarioId(usuarioDel.getId());
            cso.removeCursoUsuario(cu);

            cursoRepository.save(curso.get());
            return Optional.of(usuarioDel);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Curso> porIdConUsuarios(Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);
        if (o.isPresent()){
            Curso curso = o.get();
            if (!curso.getCursoUsuario().isEmpty()){
                List<Long> ids = curso.getCursoUsuario().stream().map(CursoUsuario::getUsuarioId).toList();
                List<Usuario> usuarios = clientUsuario.buscarPorIds(ids);
                curso.setUsuarios(usuarios);
            }
            return Optional.of(curso);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void eliminarCursoUsuarioPorId(Long idUsuario) {
        cursoRepository.eliminarCursoUsuarioPorId(idUsuario);
    }
}
