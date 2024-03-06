package com.mandacarubroker.custimnotation;

import com.mandacarubroker.customvalidator.AgeLimitValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Notação para verificação de data do usuario.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeLimitValidator.class)
public @interface AgeLimit {
  /**
   * Idade minima necessaria.
   */
  int minimumAge() default 18;
  /**
   * Mensagem de erro.
   */
  String message() default "User must be at least 18 years old";
  /**
   * Grupos.
   */
  Class<?>[] groups() default {};
  /**
   * Payload.
   */
  Class<? extends Payload>[] payload() default {};
}