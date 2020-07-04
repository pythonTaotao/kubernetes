package com.beryl.controller;

import com.beryl.mapper.PunchInMapper;
import com.beryl.mapper.StudentMapper;
import com.beryl.pojo.PunchIn;
import com.beryl.pojo.Student;
import com.beryl.service.PunchInService;
import com.beryl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author beryl
 * @date 2020/2/20-15:43
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private PunchInService punchInService;

//    @RequestMapping("/queryStuList")
//    public List<Student> queryStuList() {
//        return studentService.queryStuList();

//    }
    @ResponseBody
    @RequestMapping("/login")
    public Map<String,String> login(String stuNo,String password) {
        Map<String, String> result = new HashMap<>();
        Student student = studentService.queryStuByStuNo(Long.parseLong(stuNo));
        if (student == null) {  //为空
            result.put("msg", "用户名不存在");
        } else {
            if (password.equals(student.getPassword())) {  //密码正确
                result.put("msg", "登录成功");;
            } else {  //密码错误
                result.put("msg", "密码错误");
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/register")
    public Map<String,String> register(Student student) {
        Map<String,String> result = new HashMap<>();
        try {
            studentService.addStudent(student);
            result.put("msg", "success");
        } catch (Exception e) {  //注册失败
            result.put("msg", "error");
        }
        return result;
    }

    @RequestMapping("/showInfo")
    public String showInfo(String stuNo, Model model) {
        Student student = studentService.queryStuByStuNo(Long.parseLong(stuNo));
        model.addAttribute("student", student);
        return "stuInfo";
    }
    @RequestMapping("/update")
    public String updateInfo(Student student, Model model) {

        try {
            studentService.updateStudent(student);
            //修改成功
            model.addAttribute("student", student);
            model.addAttribute("msg", "修改成功");
        } catch (Exception e) {  //修改失败
            model.addAttribute("student", studentService.queryStuById(student.getId()));
            model.addAttribute("msg", "修改失败，请检查数据格式是否有误");
            e.printStackTrace();
        }
        return "stuInfo";
    }

    @RequestMapping("/addPunchIn")
    public String punchIn(String stuNo, Model model) throws ParseException {

        model.addAttribute("student", studentService.queryStuByStuNo(Long.parseLong(stuNo)));
        Date curDate = new Date();

        //先判断今天是否已经签到
        //从数据库获取最近的此学号的签到记录
        PunchIn latestPunchIn = punchInService.queryLatestPunchIn(Long.parseLong(stuNo));
        //只判断日期
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        if (latestPunchIn!=null) {  //重要！！有可能数据库没有此学号记录，那么后面会有空指针异常
            if (df1.format(curDate).equals(df1.format(latestPunchIn.getPunchInTime()))) {
                model.addAttribute("msg", "今日已签到，请勿重复签到");
                List<PunchIn> punchIns = punchInService.queryListByStuNo(Long.parseLong(stuNo));
                model.addAttribute("punchIns", punchIns);
                return "checkIn";
            }
        }

        //比较开始和结束的时分秒，拼接时今天的日期，判断签到有效时间范围
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String date = df2.format(curDate);
        String beginTime=new String(date+" 14:30:00");
        String endTime=new String(date+" 17:00:00");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate=df.parse(beginTime);
        Date endDate=df.parse(endTime);

        if (curDate.after(beginDate) && curDate.before(endDate)) {  //在签到时间内
            Student student = studentService.queryStuByStuNo(Long.parseLong(stuNo));
            PunchIn punchIn = new PunchIn(student.getStuNo(), student.getName(), curDate);
            punchInService.addPunchIn(punchIn);
            model.addAttribute("msg", "签到成功");
        } else {   //不在签到时间内
            model.addAttribute("msg", "签到失败，不在签到时间内");
        }

        List<PunchIn> punchIns = punchInService.queryListByStuNo(Long.parseLong(stuNo));
        model.addAttribute("punchIns", punchIns);
        return "checkIn";
    }

}