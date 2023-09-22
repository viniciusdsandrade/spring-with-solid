package br.com.alura.adopet.api.validacoes.pet;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;
    
    @Contract(pure = true)
    public ValidacaoPetComAdocaoEmAndamento(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    public void validar(@NotNull SolicitacaoAdocaoDto dto) {
        
        boolean petTemAdocaoEmAndamento = adocaoRepository.existsByPetIdAndStatus(
                dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO.name()
        );
        
        if(petTemAdocaoEmAndamento) {
            throw new ValidacaoException("O pet já possui uma adoção em andamento");
        }
    }
}