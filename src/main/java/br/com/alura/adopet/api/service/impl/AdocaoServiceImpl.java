package br.com.alura.adopet.api.service.impl;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.EmailUnsedException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.AdocaoService;
import br.com.alura.adopet.api.service.EmailService;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdocaoServiceImpl implements AdocaoService {

    private final AdocaoRepository repository;
    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;
    private final EmailService emailService;
    private final List<ValidacaoSolicitacaoAdocao> validacoes;

    @Contract(pure = true)
    public AdocaoServiceImpl(AdocaoRepository repository,
                             PetRepository petRepository,
                             TutorRepository tutorRepository,
                             EmailService emailService,
                             List<ValidacaoSolicitacaoAdocao> validacoes) {

        this.repository = repository;
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
        this.emailService = emailService;
        this.validacoes = validacoes;
    }

    @Override
    public void solicitar(@NotNull SolicitacaoAdocaoDto dto) throws EmailUnsedException {

        Pet pet = petRepository.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());
        validacoes.forEach(validacao -> validacao.validar(dto));
        Adocao adocao = new Adocao(tutor, pet, dto.motivo());
        repository.saveAndFlush(adocao);

        emailService.sendEmail(
                adocao.getTutor().getEmail(),
                "Adoção solicitada",
                "Sua solicitação de adoção foi enviada com sucesso!" +
                        adocao.getPet().getNome() + " Sucesso na solicitação de adoção!");

    }

    @Override
    public void aprovar(@NotNull AprovacaoAdocaoDto dto) throws EmailUnsedException {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.marcarComoAprovado();

        emailService.sendEmail(
                adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Sua solicitação de adoção foi aprovada com sucesso!" +
                        adocao.getPet().getNome() + " Sucesso na solicitação de adoção!");

    }

    @Override
    public void reprovar(@NotNull ReprovacaoAdocaoDto dto) throws EmailUnsedException {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.marcarComoReprovado(dto.justificativa());

        emailService.sendEmail(
                adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Sua solicitação de adoção foi reprovada com sucesso!" +
                        adocao.getPet().getNome() + " Sucesso na solicitação de adoção!");
    }
}