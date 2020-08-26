package com.conquer.cursos.model.entity;

import com.conquer.cursos.model.entity.Aluno;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    @Transient
    public static final int LIMITE_ALUNOS = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TURMA_ALUNO",
    joinColumns = @JoinColumn(name = "turma_id"),
    inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Set<Aluno> alunos = new HashSet<>();

    public Turma() {
    }

    public Turma(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    @Transient
    public boolean isCheia(){
        return (alunos.size() >= LIMITE_ALUNOS);
    }
}
