package com.conquer.cursos.controller;

import com.conquer.cursos.DTO.AlunoDTO;
import com.conquer.cursos.DTO.AlunoNewDTO;
import com.conquer.cursos.DTO.DTO;
import com.conquer.cursos.model.entity.Aluno;
import com.conquer.cursos.model.entity.Turma;
import com.conquer.cursos.service.AlunoService;
import com.conquer.cursos.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public String listarAlunos(Model model){
        model.addAttribute("alunos", service.getAll());
        model.addAttribute("dto", new DTO());
        return "alunos";
    }

    @GetMapping("/novoAluno")
    public String novoAluno(Model model) {
        model.addAttribute("aluno", new AlunoNewDTO());
        return "novoAluno";
    }

    @PostMapping
    public String insertAluno(@Valid @ModelAttribute AlunoNewDTO dto){
        Aluno aluno = service.fromDTO(dto);
        aluno = service.insert(aluno);
        return "redirect:/alunos";
    }
//    @PostMapping
//    public String insertAluno(@Valid @ModelAttribute AlunoNewDTO dto, RedirectAttributes redirectAttributes){
//        Aluno aluno = service.fromDTO(dto);
//        aluno = service.insert(aluno);
//        redirectAttributes.addFlashAttribute("aluno", aluno);
//        return "redirect:/matricular";
//    }

//
//    @PostMapping
//    public ResponseEntity<Void> insert(@Valid @RequestBody AlunoNewDTO dto) {
//        Aluno obj = service.fromDTO(dto);
//        obj = service.insert(obj);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }

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
