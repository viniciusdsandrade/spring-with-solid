package br.com.alura.adopet.api.service.impl;

import br.com.alura.adopet.api.dto.abrigo.CadastroAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.validacoes.ValidacaoCadastroAbrigo;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoServiceImpl implements AbrigoService {

    private final AbrigoRepository abrigoRepository;
    private final TutorRepository tutorRepository;
    private final List<ValidacaoCadastroAbrigo> validacoes;

    @Contract(pure = true)
    public AbrigoServiceImpl(AbrigoRepository abrigoRepository,
                             TutorRepository tutorRepository,
                             List<ValidacaoCadastroAbrigo> validacoes) {
        
        this.abrigoRepository = abrigoRepository;
        this.tutorRepository = tutorRepository;
        this.validacoes = validacoes;
    }

    @Override
    public void cadastrar(@NotNull CadastroAbrigoDto dto) {
        Abrigo abrigo = abrigoRepository.getReferenceById(dto.idAbrigo());
        validacoes.forEach(validacao -> validacao.validar(dto));
        
        abrigoRepository.saveAndFlush(abrigo);
    }




    @Override
    public void listarPets(Adocao adocao) {

    }

    @Override
    public void cadastrarPet(Adocao adocao) {

    }
}
