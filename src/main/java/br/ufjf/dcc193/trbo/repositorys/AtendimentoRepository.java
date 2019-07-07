package br.ufjf.dcc193.trbo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trbo.models.Atendimento;

/**
 * AtendimentoRepository
 */
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findAll();
    void deleteById(Long id);
}