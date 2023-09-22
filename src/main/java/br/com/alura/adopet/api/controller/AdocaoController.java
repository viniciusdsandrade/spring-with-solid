package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.AdocaoException;
import br.com.alura.adopet.api.service.AdocaoService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.Contract;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    private final AdocaoService adocaoService;

    @Contract(pure = true)
    public AdocaoController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoAdocaoDto dto) {
        try {
            this.adocaoService.solicitar(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid AprovacaoAdocaoDto dto) throws AdocaoException {

        this.adocaoService.aprovar(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovacaoAdocaoDto dto) throws AdocaoException {

        this.adocaoService.reprovar(dto);
        return ResponseEntity.ok().build();
    }
}
