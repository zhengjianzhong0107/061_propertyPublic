package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.Repair;
import com.stu.model.Tongji;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
public interface IRepairService extends IService<Repair> {

    PageInfo<Repair> findRepairAll(int page, int pagesise, Repair repair);

    List<Repair> queryList();
    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Repair>
     */
    IPage<Repair> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param repair 
     * @return int
     */
    int add(Repair repair);

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
     * @param repair 
     * @return int
     */
    int updateData(Repair repair);

    /**
     * id查询数据
     *
     * @param id id
     * @return Repair
     */
    Repair findById(Long id);


    List<Tongji> queryTongji();
}
