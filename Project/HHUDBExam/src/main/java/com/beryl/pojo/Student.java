package com.beryl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author beryl
 * @date 2020/2/20-15:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;
    private Long stuNo;
    private String name;
    private String password;
    private String classId;
    private String email;
    private String phone;
}