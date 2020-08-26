package com.conquer.cursos.service;


import com.conquer.cursos.DTO.TurmaDTO;
import com.conquer.cursos.model.entity.Aluno;
import com.conquer.cursos.model.entity.Turma;
import com.conquer.cursos.repositories.TurmaRepository;
import com.conquer.cursos.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;

    public Turma insert(Turma obj){
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }

    public Turma find(Integer id){
        Optional<Turma> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Turma.class.getName()));
    }

    public Turma fromDTO(TurmaDTO dto) {
        return new Turma(dto.getNome());
    }

    public Turma update(Turma obj){
        Turma aux = find(obj.getId());
        aux.setNome(obj.getNome());
        return repository.save(aux);
    }
}
