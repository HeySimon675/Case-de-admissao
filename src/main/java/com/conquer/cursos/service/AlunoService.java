package com.conquer.cursos.service;

import com.conquer.cursos.DTO.AlunoDTO;
import com.conquer.cursos.DTO.AlunoNewDTO;
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

    public Aluno fromDTO(AlunoDTO dto) {
        return new Aluno(dto.getNome(), null, dto.getEmail());
    }
    public Aluno fromDTO(AlunoNewDTO dto) {
        return new Aluno(dto.getNome(), dto.getCpf(), dto.getEmail());
    }


    public Aluno update(Aluno obj){
        Aluno aux = find(obj.getId());
        aux.setNome(obj.getNome());
        aux.setEmail(obj.getEmail());
        return repository.save(aux);
    }
}
