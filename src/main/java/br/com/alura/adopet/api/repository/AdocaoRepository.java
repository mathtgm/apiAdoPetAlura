package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    boolean existsByPetIdAndStatus(Long id, StatusAdocao status);

    boolean existsByIdTutorAndStatus(Long id, StatusAdocao status);

    List<Adocao> findByIdTutorAndStatus(Long id, StatusAdocao statusAdocao);
}
