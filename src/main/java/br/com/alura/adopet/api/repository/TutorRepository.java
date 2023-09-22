package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query("SELECT t FROM Tutor t WHERE t.nome = :nome")
    boolean existsByTelefone(String telefone);

    @Query("SELECT t FROM Tutor t WHERE t.email = :email")
    boolean existsByEmail(String email);
}
