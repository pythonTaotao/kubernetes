package com.beryl.service;

import com.beryl.pojo.PunchIn;

import java.util.List;

/**
 * @author beryl
 * @date 2020/4/29-17:35
 */
public interface PunchInService {
    List<PunchIn> queryPunchInList();
    PunchIn queryPunchInByStuNo(Long stuNo);
    int addPunchIn(PunchIn punchIn);
    int deletePunchInByStuNo(Long stuNo);

    PunchIn queryLatestPunchIn(Long stuNo);

    List<PunchIn> queryListByStuNo(Long stuNo);
}