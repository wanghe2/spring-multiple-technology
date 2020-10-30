package com.wang.dao;

import com.wang.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    @Query(value="select * from employee where dept = ?1 limit 0,10", nativeQuery = true)
    List<Employee> getEmployeeByDept(Integer dept);
}
