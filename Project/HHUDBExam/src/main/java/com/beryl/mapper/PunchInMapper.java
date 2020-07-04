package com.beryl.mapper;

import com.beryl.pojo.PunchIn;
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
public interface PunchInMapper {
    List<PunchIn> queryPunchInList();
    PunchIn queryPunchInByStuNo(Long stuNo);
    int addPunchIn(PunchIn punchIn);
    int deletePunchInByStuNo(Long stuNo);

    PunchIn queryLatestPunchIn(Long stuNo);

    List<PunchIn> queryListByStuNo(Long stuNo);
    //int updatePunchIn(PunchIn punchIn);
}