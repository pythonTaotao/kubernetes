package com.beryl.controller;

import com.beryl.mapper.StudentMapper;
import com.beryl.pojo.PunchIn;
import com.beryl.pojo.Student;
import com.beryl.service.PunchInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author beryl
 * @date 2020/4/30-13:50
 */
@Controller
public class RemoteController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PunchInService punchInService;

    @RequestMapping("/toRegister")
    private String toRegister() {
        return "register";
    }
    @RequestMapping("/toLogin")
    private String toLogin() {
        return "login";
    }

    @RequestMapping("/toStuPage")
    private String toStuPage(String stuNo, Model model) {
        Student student = studentMapper.queryStuByStuNo(Long.parseLong(stuNo));
        model.addAttribute("student", student);
        return "student";
    }

    @RequestMapping("/toCheckIn")
    private String toCheckIn(String stuNo, Model model) {
        Student student = studentMapper.queryStuByStuNo(Long.parseLong(stuNo));
        model.addAttribute("student", student);
        List<PunchIn> punchIns = punchInService.queryListByStuNo(Long.parseLong(stuNo));
        model.addAttribute("punchIns", punchIns);
        return "checkIn";
    }



}