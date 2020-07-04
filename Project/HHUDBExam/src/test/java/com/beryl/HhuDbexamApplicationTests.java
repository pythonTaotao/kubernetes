package com.beryl;

import com.beryl.mapper.PunchInMapper;
import com.beryl.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HhuDbexamApplicationTests {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PunchInMapper punchInMapper;
    @Test
    void contextLoads() {

    }

}
