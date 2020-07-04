package com.beryl.mapper;

import com.beryl.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author beryl
 * @date 2020/2/20-15:27
 */
@Mapper
@Repository
public interface StudentMapper {
    List<Student> queryStuList();
    Student queryStuByStuNo(Long stuNo);
    int addStudent(Student student);
    int deleteStudentByStuNo(Long stuNo);
    int updateStudent(Student student);

    int queryStuById(Integer id);
}