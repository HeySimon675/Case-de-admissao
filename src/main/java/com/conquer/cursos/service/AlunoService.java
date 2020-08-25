package com.conquer.cursos.service;

import com.conquer.cursos.model.entity.Aluno;
import com.conquer.cursos.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public Aluno insert(Aluno obj){
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }
}
