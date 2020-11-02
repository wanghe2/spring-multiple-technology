package com.wang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api(value="页面",tags={"用户页面"})
@RequestMapping("/page")
public class PageController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ApiOperation(value = "访问用户界面",notes = "测试使用，thymeleaf模板")
    public String userPage(Model model){
        model.addAttribute("name","wang he");
        return "user";
    }

}
