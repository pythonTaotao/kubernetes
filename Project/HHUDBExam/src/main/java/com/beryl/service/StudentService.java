package com.beryl.service;

import com.beryl.pojo.Student;

import java.util.List;

/**
 * @author beryl
 * @date 2020/4/29-17:35
 */
public interface StudentService {
    List<Student> queryStuList();
    Student queryStuByStuNo(Long stuNo);
    int addStudent(Student student);
    int deleteStudentByStuNo(Long stuNo);
    int updateStudent(Student student);

    int queryStuById(Integer id);

}
