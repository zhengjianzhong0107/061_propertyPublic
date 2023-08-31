package com.stu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.model.Complaint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
@Component("complaintDao")
public interface ComplaintMapper extends BaseMapper<Complaint> {

     List<Complaint> queryComplaintAll(Complaint complaint);
}
