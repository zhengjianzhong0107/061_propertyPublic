package com.stu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.model.Userinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
@Component("userinfoDao")
public interface UserinfoMapper extends BaseMapper<Userinfo> {

    List<Userinfo> queryUserinfoAll(Userinfo userinfo);

    Userinfo queryUserByNameAndPwd(Userinfo userinfo);

    void deleteUserByUsername(@Param("username2") String username);
}
