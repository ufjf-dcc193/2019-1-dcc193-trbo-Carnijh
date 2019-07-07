package br.ufjf.dcc193.trbo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trbo.models.Usuario;

/**
 * UsuarioRepository
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAll();
    void deleteById(Long id);
}