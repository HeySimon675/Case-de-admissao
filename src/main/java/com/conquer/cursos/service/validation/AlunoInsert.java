package com.conquer.cursos.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = AlunoInsertValidator.class)

public @interface AlunoInsert {
    String message() default "Erro de validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
