/*
 * This class demonstrates nested testcase
 */
package com.demo.service;

import com.demo.exception.EmployeeException;
import com.demo.model.Employee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Invalid employee test")
@Tag("integration")
class InvalidEmployeeCreateTest {

    private Employee employee;

    @Autowired
    EmployeeService employeeService;

    @Nested
    @DisplayName("For invalid email")
    class ForInvalidEmail {

        int totalEmployee = 0;

        @BeforeEach
        void createNewEmployee() {
            totalEmployee = employeeService.findAll().size();
            employee = new Employee("TestFirstName", "TestLastName", "not-an-email");
        }

        @Test
        @DisplayName("throws invalid email exception")
        void shouldThrowException() {
            assertThrows(EmployeeException.class, () -> employeeService.save(employee));
        }

        @Nested
        @DisplayName("when all employee fetched")
        class WhenAllEmployeeFetched {

            @Test
            @DisplayName("there should not be any new employee added")
            void noEmployeeShouldBePresent() {
                assertTrue(employeeService.findAll().size() == totalEmployee, "No new employee present");
            }
        }
    }
}
