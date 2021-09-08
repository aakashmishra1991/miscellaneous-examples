/*
 * This demonstrates lambda assertions
 */
package com.demo.service;

import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.demo.exception.EmployeeException;
import com.demo.model.Employee;

@Transactional
//@SpringBootTest()
//@ExtendWith(SpringExtension.class)
@DisplayName("Valid employee test")
@Tag("integration")
public class ValidEmployeeTest {

  private Employee employee;

  @Autowired
  private EmployeeService employeeService;

  @BeforeEach
  void setup() {
    employee = new Employee("test-firstname", "test-lastname", "test-email@domain.com");
  }

  @DisplayName("Valid employee can be stored")
  @Test
  public void validEmployeeCanBeStoredTest() throws EmployeeException {
    Employee savedEmployee = employeeService.save(employee);
    assertAll("All user attribute should be persisted",
        () -> assertEquals(employee.getFirstName(), savedEmployee.getFirstName(), "Firstname matched"),
        () -> assertEquals(employee.getLastName(), savedEmployee.getLastName(),"Lastname matched"),
        () -> assertEquals(employee.getEmail(), savedEmployee.getEmail(), "Email matched"),
        () -> assertTrue(savedEmployee.getId() > 0,"Employee ID generated"));
  }

}
