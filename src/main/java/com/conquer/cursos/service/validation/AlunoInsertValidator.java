package com.conquer.cursos.service.validation;

import com.conquer.cursos.DTO.AlunoNewDTO;
import com.conquer.cursos.controller.exceptions.FieldMessage;
import com.conquer.cursos.model.entity.Aluno;
import com.conquer.cursos.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class AlunoInsertValidator implements ConstraintValidator<AlunoInsert, AlunoNewDTO> {

   @Autowired
   private AlunoRepository repository;


   public void initialize(AlunoInsert constraint) {
   }

   public boolean isValid(AlunoNewDTO objDTO, ConstraintValidatorContext context) {

      List<FieldMessage> list = new ArrayList<>();


      Aluno aux = repository.findByEmail(objDTO.getEmail());
      if (aux != null) {
         list.add(new FieldMessage("email", "Email já existente"));
      }

      for (FieldMessage e : list) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                 .addConstraintViolation();
      }
      return list.isEmpty();
   }
}
