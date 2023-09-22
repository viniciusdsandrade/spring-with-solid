package br.com.alura.adopet.api.validacoes.tutor;


import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;
    private final TutorRepository tutorRepository;

    public ValidacaoTutorComLimiteDeAdocoes(AdocaoRepository adocaoRepository,
                                            TutorRepository tutorRepository) {
        this.adocaoRepository = adocaoRepository;
        this.tutorRepository = tutorRepository;
    }

    public void validar(@NotNull SolicitacaoAdocaoDto dto) {

        List<Adocao> adocoes = adocaoRepository.findAll();
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

        for (Adocao a : adocoes) {
            int contador = 0;
            if (a.getTutor().getId().equals(tutor.getId()) && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                contador++;
            }
            if (contador == 5) {
                throw new ValidacaoException("Tutor já possui 3 adoções aguardando avaliação!");
            }
        }
    }
}
    
    

