package com.conquer.cursos.DTO;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AlunoDTO extends DTO{

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;

    public AlunoDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}


