package com.wang.service;

import com.wang.bean.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee getEmployeeByName(String name);
    List<Employee> getEmployeeListByDept(Integer dept);
    void insert(Employee employee);
}
