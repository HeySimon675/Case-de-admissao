package com.conquer.cursos.DTO;

import java.io.Serializable;

public class DTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    public DTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
