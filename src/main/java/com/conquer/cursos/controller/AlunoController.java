package com.conquer.cursos.controller;

import com.conquer.cursos.DTO.AlunoDTO;
import com.conquer.cursos.DTO.AlunoNewDTO;
import com.conquer.cursos.model.entity.Aluno;
import com.conquer.cursos.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody AlunoNewDTO dto) {
        Aluno obj = service.fromDTO(dto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> find(@PathVariable Integer id){
        Aluno obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody AlunoDTO dto, @PathVariable Integer id){
        Aluno obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/matricular/{turma}/{aluno}")
    public ResponseEntity<Void> matricular(@PathVariable Integer turma, @PathVariable Integer aluno){
        service.matricular(turma, aluno);
        return ResponseEntity.noContent().build();
    }

}
