package com.conquer.cursos.DTO;

import java.io.Serializable;

public class MatriculaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer alunoId;
    private Integer turmaId;

    public MatriculaDTO() {
    }

    public Integer getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Integer alunoId) {
        this.alunoId = alunoId;
    }

    public Integer getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Integer turmaId) {
        this.turmaId = turmaId;
    }
}
