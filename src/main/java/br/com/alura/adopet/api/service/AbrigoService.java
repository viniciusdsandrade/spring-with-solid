package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.CadastroAbrigoDto;
import br.com.alura.adopet.api.model.Adocao;

public interface AbrigoService {
    
    void cadastrar(CadastroAbrigoDto dto);

    void listarPets(Adocao adocao);

    void cadastrarPet(Adocao adocao);
}
