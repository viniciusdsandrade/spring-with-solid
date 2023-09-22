package br.com.alura.adopet.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity(name = "abrigos")
@Table(
        name = "tb_abrigos",
        schema = "db_adoPet"
)
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(columnDefinition = "VARCHAR(64)")
    private String nome;

    @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
    @Column(columnDefinition = "VARCHAR(16)")
    private String telefone;

    @Email
    @Column(columnDefinition = "VARCHAR(96)")
    private String email;

    @OneToMany(mappedBy = "abrigo",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Pet> pets;
}
