package br.ufjf.dcc193.trbo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trbo.models.Categoria;

/**
 * CategoriaRepository
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAll();
    void deleteById(Long id);
}