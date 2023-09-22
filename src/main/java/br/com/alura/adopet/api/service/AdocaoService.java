package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.AdocaoException;
import br.com.alura.adopet.api.exception.EmailUnsedException;
import org.jetbrains.annotations.NotNull;

public interface AdocaoService {
    
    void solicitar(@NotNull SolicitacaoAdocaoDto dto) throws EmailUnsedException, AdocaoException;

    void aprovar(@NotNull AprovacaoAdocaoDto dto) throws EmailUnsedException;

    void reprovar(@NotNull ReprovacaoAdocaoDto dto) throws EmailUnsedException;
}
