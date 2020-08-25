package com.conquer.cursos.repositories;

import com.conquer.cursos.model.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Transactional(readOnly=true)
    Aluno findByEmail(String email);
}
