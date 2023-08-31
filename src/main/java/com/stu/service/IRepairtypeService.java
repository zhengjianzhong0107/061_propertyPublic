package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stu.model.Repairtype;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
public interface IRepairtypeService extends IService<Repairtype> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Repairtype>
     */
    IPage<Repairtype> findListByPage(Integer page, Integer pageCount);

    //查询所有的类型记录
    List<Repairtype> findList();

    /**
     * 添加
     *
     * @param repairtype 
     * @return int
     */
    int add(Repairtype repairtype);

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
     * @param repairtype 
     * @return int
     */
    int updateData(Repairtype repairtype);

    /**
     * id查询数据
     *
     * @param id id
     * @return Repairtype
     */
    Repairtype findById(Long id);
}
