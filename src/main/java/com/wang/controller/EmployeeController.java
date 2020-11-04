package com.wang.controller;

import com.alibaba.fastjson.JSON;
import com.wang.bean.Employee;
import com.wang.excption.MyException;
import com.wang.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/data")
@Api(value="雇工",tags={"雇工信息接口的操作"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RestTemplate restTemplate;

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
        if(StringUtils.isEmpty(employee) || StringUtils.isEmpty(employee.getUsername())){
            return "empty entity";
        }
        employeeService.insert(employee);
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

    @RequestMapping(value = "/exception",method = RequestMethod.GET)
    @ApiOperation(value = "全局异常" ,notes = "测试使用，这里抛出异常，会被统一处理")
    public String getException() throws MyException {
        int i = 5;
        throw new MyException("全局异常");
    }

//    @ResponseBody
    @RequestMapping(value = "/remoteAccess",method = RequestMethod.GET)
    @ApiOperation(value = "远程访问",notes = "restTemplate方式")
    public String remoteAccess(){
        ResponseEntity<String> data= restTemplate.getForEntity("https://api.yuedu.pro/book/_search?keyword=java&pn=1&ps=10",String.class);
        return "获取的内容"+data.getBody();
    }
}
