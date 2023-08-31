package com.stu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.model.Clockin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * 
 * @since 2021-04-27
 */
@Component("clockinDao")
public interface ClockinMapper extends BaseMapper<Clockin> {
    //查询所有
    List<Clockin> queryClockInAll(Clockin clockin);

    /**
     * 查找用户今天是否已经打卡，通过owner_id倒序排列查询第一个时间，就是该业主最近一次打卡时间
     */
    Date queryCountByOwnIdAndTime(@Param("ownId") Integer ownId);
}
