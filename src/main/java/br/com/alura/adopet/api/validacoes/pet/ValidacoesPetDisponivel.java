package br.com.alura.adopet.api.validacoes.pet;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ValidacoesPetDisponivel implements ValidacaoSolicitacaoAdocao {

    private final PetRepository petRepository;

    @Contract(pure = true)
    public ValidacoesPetDisponivel(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void validar(@NotNull SolicitacaoAdocaoDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());

        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já adotado");
        }
    }
}
