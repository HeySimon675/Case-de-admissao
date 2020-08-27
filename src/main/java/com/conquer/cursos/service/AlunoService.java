package com.conquer.cursos.service;

import com.conquer.cursos.DTO.AlunoDTO;
import com.conquer.cursos.DTO.AlunoNewDTO;
import com.conquer.cursos.DTO.MatriculaDTO;
import com.conquer.cursos.model.entity.Aluno;
import com.conquer.cursos.model.entity.Turma;
import com.conquer.cursos.repositories.AlunoRepository;
import com.conquer.cursos.repositories.TurmaRepository;
import com.conquer.cursos.service.exceptions.DataIntegrityException;
import com.conquer.cursos.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    @Autowired
    private TurmaService turmaService;

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

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir por estar matriculado em Turmas");
        }
    }

    public void matricular(Integer turmaId, Integer alunoId){
        turmaService.matricularAluno(turmaId, find(alunoId));
    }

    public List<Aluno> getAll() {
        return repository.findAll();
    }

    public Set<Turma> getAllMatriculadas(Integer id) {
        return find(id).getTurmasMatriculadas();
    }

    public void cancelarMatricula(MatriculaDTO dto) {
        Turma turma = turmaService.find(dto.getTurmaId());
        Aluno aluno = find(dto.getAlunoId());
        try{
            aluno.getTurmasMatriculadas().remove(turma);
            turma.getAlunos().remove(aluno);
            repository.save(aluno);
        }catch (Exception e){
            throw new DataIntegrityException("Não foi possível cancelar a matricula");
        }
    }
}
