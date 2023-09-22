package br.com.alura.adopet.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity(name = "pets")
@Table(
        name = "pets",
        schema = "db_adoPet"
)
public class Pet {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "pet_sequence")
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('GATO', 'CACHORRO')")
    private TipoPet tipo;

    @Column(columnDefinition = "VARCHAR(64)")
    private String nome;

    @Column(columnDefinition = "VARCHAR(64)")
    private String raca;

    @Column(columnDefinition = "TINYINT UNSIGNED")
    @Positive
    private Integer idade;

    @Column(columnDefinition = "VARCHAR(64)")
    private String cor;

    @Column(columnDefinition = "DECIMAL(7,2)")
    @Positive
    private Float peso;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean adotado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abrigo_id",
            columnDefinition = "BIGINT UNSIGNED")
    private Abrigo abrigo;

    @OneToOne(mappedBy = "pet")
    private Adocao adocao;
}
