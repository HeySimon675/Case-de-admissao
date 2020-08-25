package com.conquer.cursos.service.validation;

import javax.validation.Constraint;

@Constraint(validatedBy = AlunoInsertValidator.class)

public @interface AlunoInsert {
}
