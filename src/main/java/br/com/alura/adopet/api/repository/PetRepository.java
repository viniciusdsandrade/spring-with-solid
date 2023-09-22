package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
