package com.beryl.service.impl;

import com.beryl.mapper.PunchInMapper;
import com.beryl.pojo.PunchIn;
import com.beryl.service.PunchInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author beryl
 * @date 2020/4/29-17:42
 */
@Service
public class PunchInServiceImpl implements PunchInService {
    @Autowired
    private PunchInMapper punchInMapper;
    @Override
    public List<PunchIn> queryPunchInList() {
        return punchInMapper.queryPunchInList();
    }

    @Override
    public PunchIn queryPunchInByStuNo(Long stuNo) {
        return punchInMapper.queryPunchInByStuNo(stuNo);
    }

    @Override
    public int addPunchIn(PunchIn punchIn) {
        return punchInMapper.addPunchIn(punchIn);
    }

    @Override
    public int deletePunchInByStuNo(Long stuNo) {
        return punchInMapper.deletePunchInByStuNo(stuNo);
    }

    @Override
    public PunchIn queryLatestPunchIn(Long stuNo) {
        return punchInMapper.queryLatestPunchIn(stuNo);
    }

    @Override
    public List<PunchIn> queryListByStuNo(Long stuNo) {
        return punchInMapper.queryListByStuNo(stuNo);
    }
}