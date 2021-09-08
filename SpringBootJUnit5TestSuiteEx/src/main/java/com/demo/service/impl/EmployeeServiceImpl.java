package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.Util.EmailValidator;
import com.demo.exception.EmployeeException;
import com.demo.exception.EmployeeNotFoundException;
import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;
import com.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  public static final long serialVersionUID = 1L;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public List<Employee> findAll() {
    List<Employee> employees = new ArrayList<>();
    employeeRepository.findAll().forEach(employees::add);
    return employees;
  }

  @Override
  public Employee findOne(int employeeId) {
    return employeeRepository.findById(employeeId).orElse(null);
  }

  @Override
  public Employee save(Employee employee) throws EmployeeException {
    if (EmailValidator.isValidEmail(employee.getEmail())) {
      return employeeRepository.save(employee);
    }
    else { throw new EmployeeException("Email is not valid for the employee - " + employee.toString());
    }
  }
  @Override
  public Employee update(Employee employee) throws EmployeeException {

    if (employee.getId() <= 0) {
      throw new EmployeeException("Error: No such employee with email = " + employee.getEmail() + " found");
    }
    Employee oldEmployee = employeeRepository.findByEmail(employee.getEmail());
    if (oldEmployee == null) {
      throw new EmployeeException("Error: Invalid employee, could not update");
    } else if (!oldEmployee.getId().equals(employee.getId())) {
      throw new EmployeeException("Error: Can't reuse existing email");
    } else {
      return employeeRepository.save(employee);
    }
  }

  @Override
  public void delete(int employeeId) throws EmployeeNotFoundException {
    Employee employee = findOne(employeeId);
    if (employee == null) {
      throw new EmployeeNotFoundException("Not such employee with id - " + employeeId);
    } else {
      employeeRepository.deleteById(employeeId);
    }
  }
}
