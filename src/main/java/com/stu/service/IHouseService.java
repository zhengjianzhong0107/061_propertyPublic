package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.House;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
public interface IHouseService extends IService<House> {

    /**
     * 分页查询房屋信息
     */
    PageInfo<House> findHouseAll(int page,int pagesize,String numbers);

    List<House> findList();

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<House>
     */
    IPage<House> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param house 
     * @return int
     */
    int add(House house);

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
     * @param house 
     * @return int
     */
    int updateData(House house);

    /**
     * id查询数据
     *
     * @param id id
     * @return House
     */
    House findById(Long id);

    House queryHouseById(Integer houId);
}
