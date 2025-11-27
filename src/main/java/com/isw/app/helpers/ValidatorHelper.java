package com.isw.app.helpers;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

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
