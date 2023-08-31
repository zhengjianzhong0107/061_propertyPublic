package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.Parking;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
public interface IParkingService extends IService<Parking> {

    PageInfo<Parking> findParkAll(int page, int pageSize, String numbers);

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Parking>
     */
    IPage<Parking> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param parking 
     * @return int
     */
    int add(Parking parking);

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
     * @param parking 
     * @return int
     */
    int updateData(Parking parking);

    /**
     * id查询数据
     *
     * @param id id
     * @return Parking
     */
    Parking findById(Long id);

    List<Parking> queryParkingAll();

    List<Parking> queryParkingByStatus();
}
