package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.Clockin;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * 
 * @since 2021-04-27
 */
public interface IClockinService extends IService<Clockin> {

    PageInfo<Clockin> queryClockInAll(int pageNum,int pageSize,Clockin clockin);

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Clockin>
     */
    IPage<Clockin> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param clockin 
     * @return int
     */
    int add(Clockin clockin);

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
     * @param clockin 
     * @return int
     */
    int updateData(Clockin clockin);

    /**
     * id查询数据
     *
     * @param id id
     * @return Clockin
     */
    Clockin findById(Long id);

    Date queryCountByOwnIdAndTime(Integer ownId);
}
