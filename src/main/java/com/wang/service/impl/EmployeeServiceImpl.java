package com.wang.service.impl;

import com.wang.bean.Employee;
import com.wang.bean.MessageSender;
import com.wang.dao.EmployeeDao;
import com.wang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static String EMP_KEY_PREFIX = "emp_key";
    private final static String EMP_DEPT_PREFIX = "emp_dept";

    @Autowired
    private EmployeeDao employeeDao;

    @Resource(name="employeeRedisTemplate")
    private RedisTemplate<String,Employee> employeeRedisTemplate;

    @Autowired
    private MessageSender messageSender;

    @Override
    public Employee getEmployeeByName(String name) {
        Employee employeeCache = employeeRedisTemplate.boundValueOps(EMP_KEY_PREFIX+name).get();
        if(employeeCache != null){
            System.out.println("**********从缓存中获取"+employeeCache.getUsername()+"的信息**********");
            return employeeCache;
        }
        Employee employee = new Employee();
        employee.setUsername(name);
        Example<Employee>example=Example.of(employee);
        try {
            Employee employeeDb = employeeDao.findOne(example).get();
            employeeRedisTemplate.boundValueOps(EMP_KEY_PREFIX+name).set(employeeDb);
            return employeeDb;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeeListByDept(Integer dept) {
        BoundHashOperations<String, String, Employee>boundHashOperations= employeeRedisTemplate.<String,Employee>boundHashOps(EMP_DEPT_PREFIX+dept);
        Map<String,Employee>entrys = boundHashOperations.entries();
        if(entrys != null && entrys.size()>0){
            List<Employee>employeeList = new ArrayList<>();
            entrys.forEach((key,value)->{
                employeeList.add(value);
            });
            System.out.println("-------走缓存----------");
            return employeeList;
        }
        List<Employee>employees = employeeDao.getEmployeeByDept(dept);
        for(Employee employee : employees){
            boundHashOperations.put(employee.getUsername(),employee);
        }
        return employees;
    }

    /**
     * 这里不去到数据库增加数据，这里模拟已经增加了数据，发送成功的消息到消息队列
     * @param employee
     */
    @Override
    public void insert(Employee employee) {
        messageSender.employeeAddSend(employee);
    }

    private void fun(){
        employeeRedisTemplate.executePipelined(
                new RedisCallback<Long>() {
                    @Nullable
                    @Override
                    public Long doInRedis(RedisConnection connection) throws DataAccessException {
                        connection.openPipeline();
                        for (int i = 0; i < 1000000; i++) {
                            String key = "123" + i;
                            connection.zCount(key.getBytes(), 0,Integer.MAX_VALUE);
                        }
                        return null;
                    }
                }
        );

    }
}
