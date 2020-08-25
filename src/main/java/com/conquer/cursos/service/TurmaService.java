package com.conquer.cursos.service;

import com.conquer.cursos.model.entity.Turma;
import com.conquer.cursos.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;

    public Turma insert(Turma obj){
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }
}
