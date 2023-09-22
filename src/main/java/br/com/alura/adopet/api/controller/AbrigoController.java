package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.abrigo.CadastroAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.jetbrains.annotations.Contract;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {
    
    private final AbrigoService abrigoService;
    
    @Contract(pure = true)
    public AbrigoController(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
    }

    @GetMapping
    public ResponseEntity<List<Abrigo>> listar() {
        return ResponseEntity.ok(abrigoService.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroAbrigoDto dto) {
        try{
            abrigoService.cadastrar(dto);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<Pet>> listarPets(@PathVariable String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            List<Pet> pets = repository.getReferenceById(id).getPets();
            return ResponseEntity.ok(pets);
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = repository.findByNome(idOuNome).getPets();
                return ResponseEntity.ok(pets);
            } catch (EntityNotFoundException enfe) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid Pet pet) {
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = repository.getReferenceById(id);
            pet.setAbrigo(abrigo);
            pet.setAdotado(false);
            abrigo.getPets().add(pet);
            repository.save(abrigo);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = repository.findByNome(idOuNome);
                pet.setAbrigo(abrigo);
                pet.setAdotado(false);
                abrigo.getPets().add(pet);
                repository.save(abrigo);
                return ResponseEntity.ok().build();
            } catch (EntityNotFoundException enfe) {
                return ResponseEntity.notFound().build();
            }
        }
    }
}