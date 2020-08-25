package com.conquer.cursos.controller;


import com.conquer.cursos.DTO.TurmaDTO;
import com.conquer.cursos.model.entity.Turma;
import com.conquer.cursos.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody TurmaDTO dto){
        Turma obj = service.fromDTO(dto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Turma> find(@PathVariable Integer id){
        Turma obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
