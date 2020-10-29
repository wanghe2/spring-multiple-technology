package com.wang.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel("雇工信息")
@Entity
@Table(name="employee")
public class Employee implements Serializable {

    @ApiModelProperty(value="id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(value="用户名",required = true)
    @Column(name="username")
    private String username;

    @ApiModelProperty(value="年龄")
    private Integer age;

    @ApiModelProperty(value="薪水")
    private Integer salary;

    @ApiModelProperty(value="职位")
    private String position;

    @ApiModelProperty(value="部门")
    private Integer dept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }


}
