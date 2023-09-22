package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    
    @Query(value = 
            "SELECT EXISTS(" +
                    "SELECT * FROM tb_adocao " +
                    "WHERE pet_id = ?1 " +
                    "AND status = ?2)", 
            nativeQuery = true)
    boolean existsByPetIdAndStatus(Long petId, String status);

}
