package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.abrigo.CadastroPetAbrigoDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    private String nome;

    private String raca;

    private Integer idade;

    private String cor;

    private Float peso;

    private Boolean adotado;

    public Pet() {
    }

    public Pet(CadastroPetAbrigoDto cadastroPetAbrigoDto) {
        this.tipo = cadastroPetAbrigoDto.tipoPet();
        this.nome = cadastroPetAbrigoDto.nome();
        this.raca = cadastroPetAbrigoDto.raca();
        this.idade = cadastroPetAbrigoDto.idade();
        this.cor = cadastroPetAbrigoDto.cor();
        this.peso = cadastroPetAbrigoDto.peso();
        this.adotado = false;
    }

    @ManyToOne
    @JsonBackReference("abrigo_pets")
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    @OneToOne(mappedBy = "pet")
    @JsonBackReference("adocao_pets")
    private Adocao adocao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public void setTipo(TipoPet tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Boolean getAdotado() {
        return adotado;
    }

    public void setAdotado(Boolean adotado) {
        this.adotado = adotado;
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }

    public Adocao getAdocao() {
        return adocao;
    }

    public void setAdocao(Adocao adocao) {
        this.adocao = adocao;
    }
}