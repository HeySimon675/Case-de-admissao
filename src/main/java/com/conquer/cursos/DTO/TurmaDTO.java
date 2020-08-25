package com.conquer.cursos.DTO;

import com.conquer.cursos.model.entity.Turma;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class TurmaDTO extends DTO {

    @NotEmpty
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    public TurmaDTO() {
    }

    public TurmaDTO(Turma obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
