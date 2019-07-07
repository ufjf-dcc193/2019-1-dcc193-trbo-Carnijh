package br.ufjf.dcc193.trbo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trbo.models.Evento;

/**
 * EventoRepository
 */
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findAll();
    void deleteById(Long id);
}