package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByTelefoneAndEmail(String telefone, String email);

    boolean existsByEmail(String email);

    boolean existsByTelefone(String telefone);
}
