package com.conquer.cursos.service.validation;

import com.conquer.cursos.DTO.AlunoNewDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlunoInsertValidator implements ConstraintValidator<AlunoInsert, AlunoNewDTO> {
   public void initialize(AlunoInsert constraint) {
   }

   public boolean isValid(AlunoNewDTO objDTO, ConstraintValidatorContext context) {
      return false;
   }
}
