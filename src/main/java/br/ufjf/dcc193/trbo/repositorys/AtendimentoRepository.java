package br.ufjf.dcc193.trbo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trbo.models.Atendente;
import br.ufjf.dcc193.trbo.models.Atendimento;
import br.ufjf.dcc193.trbo.models.Categoria;
import br.ufjf.dcc193.trbo.models.Usuario;

/**
 * AtendimentoRepository
 */
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findAll();
    void deleteById(Long id);
    List<Atendimento> findByUsuario(Usuario usuario);
    List<Atendimento> findByAtendenteAndStatus(Atendente atendente, String status);
    List<Atendimento> findByCategoriaAndStatus(Categoria categoria, String status);
    List<Atendimento> findByStatus(String status);
    
}