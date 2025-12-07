package com.isw.app.application.helpers;

import java.util.Set;
import java.util.stream.Collectors;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

public class ValidatorHelper {
  private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private static final Validator validator = factory.getValidator();

  public static <T> Set<ConstraintViolation<T>> validate(T object) {
    return validator.validate(object);
  }

  public static <T> String getMessages(Set<ConstraintViolation<T>> violations) {
    return violations.stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining("\n"));
  }
}
