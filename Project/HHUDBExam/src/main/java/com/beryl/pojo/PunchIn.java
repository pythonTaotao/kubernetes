package com.beryl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author beryl
 * @date 2020/4/29-16:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PunchIn {
    private Long stuNo;
    private String name;
    private Date punchInTime;
}