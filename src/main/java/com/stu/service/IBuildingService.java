package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.Building;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
public interface IBuildingService extends IService<Building> {

    PageInfo<Building> findBuildAll(int page,int pageSize,String numbers);

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Building>
     */
    IPage<Building> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param building 
     * @return int
     */
    int add(Building building);

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
     * @param building 
     * @return int
     */
    int updateData(Building building);

    /**
     * id查询数据
     *
     * @param id id
     * @return Building
     */
    Building findById(Long id);

    Building queryBuildById(Integer buildId);
}
