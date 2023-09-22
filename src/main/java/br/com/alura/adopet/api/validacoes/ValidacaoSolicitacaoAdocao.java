package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ValidacaoSolicitacaoAdocao {

    void validar(@NotNull SolicitacaoAdocaoDto dto) ;
}
