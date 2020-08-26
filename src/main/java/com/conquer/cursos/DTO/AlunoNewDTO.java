package com.conquer.cursos.DTO;

import com.conquer.cursos.service.validation.AlunoInsert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@AlunoInsert
public class AlunoNewDTO extends AlunoDTO{

    @NotEmpty(message="Preenchimento obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    public AlunoNewDTO() {
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
