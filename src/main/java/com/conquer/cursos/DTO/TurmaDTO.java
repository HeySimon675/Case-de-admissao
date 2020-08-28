package com.conquer.cursos.DTO;

import com.conquer.cursos.model.entity.Turma;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

public class TurmaDTO extends DTO {

    @NotEmpty
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;
    @Length(max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String descricao;

    @DecimalMin(value = "0.0")
    @Digits(integer = 6, fraction = 2)
    private Double preco;

    public TurmaDTO() {
        setDescricao("");
        setPreco(0D);
    }

    public TurmaDTO(Turma obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
        descricao = obj.getDescricao();
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
