package br.com.alura.adopet.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor
@Entity(name = "adocoes")
@Table(
        name = "tb_adocao",
        schema = "db_adoPet"
)
public class Adocao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "adocao_sequence"
    )
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(columnDefinition = "DATETIME")
    @EqualsAndHashCode.Include
    private LocalDateTime data;

    @JoinColumn(columnDefinition = "BIGINT UNSIGNED")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @JoinColumn(columnDefinition = "BIGINT UNSIGNED")
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition =
            "ENUM('AGUARDANDO_AVALIACAO', " +
                    "'APROVADO', " +
                    "'REPROVADO')")
    private StatusAdocao status;

    @Column(columnDefinition = "TEXT")
    private String justificativaStatus;

    public Adocao(Tutor tutor, Pet pet, String motivo) {
        this.tutor = tutor;
        this.pet = pet;
        this.motivo = motivo;
        this.data = LocalDateTime.now();
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
    }

    public void marcarComoAprovado() {
        this.status = StatusAdocao.APROVADO;
    }

    public void marcarComoReprovado(String justificativa) {
        this.status = StatusAdocao.REPROVADO;
        this.justificativaStatus = justificativa;
    }
}