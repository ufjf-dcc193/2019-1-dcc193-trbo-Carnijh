package br.ufjf.dcc193.trbo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trbo.models.Atendente;

/**
 * AtendenteRepository
 */
public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    List<Atendente> findAll();
    List<Atendente> findByDeletado(Boolean deletado);
    void deleteById(Long id);
    Atendente findByEmail(String email);
    Atendente findByEmailAndCodigoAcesso(String email, String codigoAcesso);
}