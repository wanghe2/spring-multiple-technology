package com.wang.controller;

import com.alibaba.fastjson.JSON;
import com.wang.bean.Employee;
import com.wang.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
@Api("针对雇工信息进行增删改查")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    @ApiOperation(value = "获取员工信息",notes = "根据用户名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名" ,dataType = "String",defaultValue = "" ,paramType = "query")
    })
    public String getEmployeeByName(String username){
        Employee employee=employeeService.getEmployeeByName(username);
        if(employee != null) {
            return JSON.toJSONString(employee);
        }
        return "sorry,no user_info for "+username;
    }

    @RequestMapping(value = "/employee", method =  RequestMethod.POST)
    @ApiOperation(value = "新增员工信息",notes = "传递一个对象")
    public String addEmployee(Employee employee){
        return "add success";
    }

    @RequestMapping(value = "/employeeByDept",method = RequestMethod.GET)
    @ApiOperation(value = "获取员工列表",notes = "根据部门id获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dept",value = "部门id" ,dataType = "Integer",defaultValue = "" ,paramType = "query")
    })
    public String  getEmployeeListByDept(Integer dept){
        List<Employee>employeeList = employeeService.getEmployeeListByDept(dept);
        if(employeeList !=null&&employeeList.size()>0){
            return JSON.toJSONString(employeeList);
        }
        return "";
    }
}