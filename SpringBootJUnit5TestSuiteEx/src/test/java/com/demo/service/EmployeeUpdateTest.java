package com.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.demo.exception.EmployeeException;
import com.demo.model.Employee;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Tag("integration")
class EmployeeUpdateTest {

  private List<Employee> employees = Arrays.asList(
      new Employee("valid", "name", "email1@exmaple.com", 1),
      new Employee("invalid", "id", "email2@exmaple.com", 0), // Invalid employee
      new Employee("uses", "other email", "email1@exmaple.com", 3), // Invalid as it uses 1st employee email
      new Employee("email", "does not exist", "email4@exmaple.com", -1)); // Non-existent email can't be updated


  @Autowired
  EmployeeService employeeService;

  @TestFactory
  Stream<DynamicTest> dynamicEmployeeTest() {
    return employees.stream()
        .map(emp -> DynamicTest.dynamicTest(
            "Update Employee: " + emp.toString(),
            () -> {
              try {
                assertNotNull(employeeService.update(emp));
              } catch (EmployeeException e) {
                assertTrue(e.getMessage().toLowerCase().contains("error:"));
              }
            }
        ));
  }
}
