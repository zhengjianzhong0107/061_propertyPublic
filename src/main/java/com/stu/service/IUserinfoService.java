package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.Userinfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
public interface IUserinfoService extends IService<Userinfo> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Userinfo>
     */
    IPage<Userinfo> findListByPage(Integer page, Integer pageCount);

    PageInfo<Userinfo> findUserinfoAll(int page, int pageSize, Userinfo userinfo);

    /**
     * 添加
     *
     * @param userinfo 
     * @return int
     */
    int add(Userinfo userinfo);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param userinfo 
     * @return int
     */
    int updateData(Userinfo userinfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return Userinfo
     */
    Userinfo findById(Long id);

    Userinfo queryUserByNameAndPwd(Userinfo userinfo);

    void deleteUserByUsername(String username);
}
