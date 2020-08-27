package com.conquer.cursos.controller;


import com.conquer.cursos.DTO.DTO;
import com.conquer.cursos.DTO.MatriculaDTO;
import com.conquer.cursos.DTO.TurmaDTO;
import com.conquer.cursos.model.entity.Aluno;
import com.conquer.cursos.model.entity.Turma;
import com.conquer.cursos.service.AlunoService;
import com.conquer.cursos.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String pageTurmas(Model model){
        model.addAttribute("turmas", service.getAll());
        return "turmas";
    }

    @PostMapping
    public String insertTurma(@Valid @ModelAttribute TurmaDTO dto){
        Turma turma = service.fromDTO(dto);
        service.insert(turma);
        return "redirect:/turmas";
    }

    @GetMapping("/matricula")
    public String mostrarTurmasParaMatricula(@ModelAttribute DTO dto, Model model){
        Aluno aluno = alunoService.find(dto.getId());
        List<Turma> turmas = service.getAllDisponivel(aluno);
        model.addAttribute("turmas", turmas);
        model.addAttribute("aluno", aluno);
        model.addAttribute("matriculaDTO", new MatriculaDTO());
        return "matricula";
    }

    @PostMapping("/matricula")
    public String matricularAluno(@ModelAttribute MatriculaDTO dto){
        Aluno aluno = alunoService.find(dto.getAlunoId());
        service.matricularAluno(dto.getTurmaId(),aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/novaTurma")
    public String novaTurma(Model model){
        model.addAttribute("turma", new TurmaDTO());
        return "novaTurma";
    }

    /* METODOS DA API
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody TurmaDTO dto, @PathVariable Integer id){
        Turma obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    */
}
