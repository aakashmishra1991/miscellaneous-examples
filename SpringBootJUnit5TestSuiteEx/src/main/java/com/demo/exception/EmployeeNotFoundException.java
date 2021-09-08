package com.demo.exception;

public class EmployeeNotFoundException extends Exception {

  public EmployeeNotFoundException(String error) {
    super(error);
  }
}
