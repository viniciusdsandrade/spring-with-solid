package br.com.alura.adopet.api.validacoes.abrigo;

import br.com.alura.adopet.api.dto.abrigo.CadastroAbrigoDto;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoCadastroAbrigo;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDeValidacaoCadastroTutor implements ValidacaoCadastroAbrigo {
    
    private final AbrigoRepository abrigoRepository;
    
    public ValidacaoDeValidacaoCadastroTutor(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }

    public void validar(@NotNull CadastroAbrigoDto dto) {
        
        boolean nomeJaCadastrado = abrigoRepository.existsByNome(dto.nome());
        boolean telefoneJaCadastrado = abrigoRepository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = abrigoRepository.existsByEmail(dto.email());
        
        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro abrigo!");
        } else {
            repository.save(abrigo);
            return ResponseEntity.ok().build();
        }
    }
    
}
