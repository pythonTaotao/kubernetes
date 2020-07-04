package com.beryl.service.impl;

import com.beryl.mapper.StudentMapper;
import com.beryl.pojo.Student;
import com.beryl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author beryl
 * @date 2020/4/29-17:42
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> queryStuList() {
        return studentMapper.queryStuList();
    }

    @Override
    public Student queryStuByStuNo(Long stuNo) {
        return studentMapper.queryStuByStuNo(stuNo);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int deleteStudentByStuNo(Long stuNo) {
        return studentMapper.deleteStudentByStuNo(stuNo);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public int queryStuById(Integer id) {
        return studentMapper.queryStuById(id);
    }
}