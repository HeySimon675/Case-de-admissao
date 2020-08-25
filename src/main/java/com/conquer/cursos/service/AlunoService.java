package com.conquer.cursos.service;

import com.conquer.cursos.model.entity.Aluno;
import com.conquer.cursos.repositories.AlunoRepository;
import com.conquer.cursos.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public Aluno insert(Aluno obj){
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }

    public Aluno find(Integer id){
        Optional<Aluno> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
    }
}
