/*
 * This unit test demonstrates parameterized test
 * with value source and method source
 */
package com.demo.service;

import com.demo.Util.EmailValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
class InvalidEmailTest {

  @DisplayName("Test with valid")
  @ParameterizedTest(name = "email = {0}")
  @ValueSource(strings = { "this@email.com", "email.example@example.com", "demo@local.co.in" })
  void validEmails(String email) {
    assertTrue(EmailValidator.isValidEmail(email));
  }

  @DisplayName("Test with invalid")
  @ParameterizedTest(name = "email = {0}")
  @MethodSource("invalidEmailsProvider")
  void invalidEmails(String email) {
    assertFalse(EmailValidator.isValidEmail(email));
  }

  private static Stream<String> invalidEmailsProvider() {
    return Stream.of("this@email", "email@example.", "local.co.in");
  }

}
