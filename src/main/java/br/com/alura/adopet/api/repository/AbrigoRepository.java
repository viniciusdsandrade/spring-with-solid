package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    @Query("SELECT a FROM Abrigo a WHERE a.nome = :nome")
    boolean existsByNome(String nome);

    @Query("SELECT a FROM Abrigo a WHERE a.telefone = :telefone")
    boolean existsByTelefone(String telefone);

    @Query("SELECT a FROM Abrigo a WHERE a.email = :email")
    boolean existsByEmail(String email);

    @Query("SELECT a FROM Abrigo a WHERE a.nome = :nome")
    Abrigo findByNome(String nome);
}
