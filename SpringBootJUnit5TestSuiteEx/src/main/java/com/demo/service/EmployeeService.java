package com.demo.service;

import java.util.List;
import com.demo.exception.EmployeeException;
import com.demo.exception.EmployeeNotFoundException;
import com.demo.model.Employee;

public interface EmployeeService {

  /**
   * Find all employees
   *
   * @return all employees
   */
  List<Employee> findAll();

  /**
   * Find a specific employee
   *
   * @param employeeId concerned id of employee
   * @return concerned employee
   */
  Employee findOne(int employeeId);

  /**
   * Creates a new employee
   *
   * @param employee employee object with required details
   * @return newly created employee
   */
  Employee save(Employee employee) throws EmployeeException;

  /**
   * Updates a new employee
   *
   * @param employee employee object with required details
   * @return newly created employee
   */
  Employee update(Employee employee) throws EmployeeException;

  /**
   * Deletes an employee
   *
   * @param employeeId concerned employee id
   */
  void delete(int employeeId) throws EmployeeNotFoundException;
}
