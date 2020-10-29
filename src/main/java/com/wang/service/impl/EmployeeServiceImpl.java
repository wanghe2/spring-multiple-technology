package com.wang.service.impl;

import com.wang.bean.Employee;
import com.wang.dao.EmployeeDao;
import com.wang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee getEmployeeByName(String name) {
        Employee employee = new Employee();
        employee.setUsername(name);
        Example<Employee>example=Example.of(employee);
        try {
            return employeeDao.findOne(example).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeeListByDept(Integer dept) {
        return employeeDao.getEmployeeByDept(dept);
    }
}
