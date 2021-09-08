package com.demo.service;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({EmployeeServiceTest.class, EmployeeUpdateTest.class})
public class AllTests {

}
