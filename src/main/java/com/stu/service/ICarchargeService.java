package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.Carcharge;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
public interface ICarchargeService extends IService<Carcharge> {


    PageInfo<Carcharge> findCarchargeAll(int page,int pagesise,Carcharge carcharge);

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Carcharge>
     */
    IPage<Carcharge> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param carcharge 
     * @return int
     */
    int add(Carcharge carcharge);

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
     * @param carcharge 
     * @return int
     */
    int updateData(Carcharge carcharge);

    /**
     * id查询数据
     *
     * @param id id
     * @return Carcharge
     */
    Carcharge findById(Long id);
}
