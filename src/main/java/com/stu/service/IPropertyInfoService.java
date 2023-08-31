package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.PropertyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
public interface IPropertyInfoService extends IService<PropertyInfo> {


    PageInfo<PropertyInfo> findPropertyInfoAll(int page, int pagesise,
                                               PropertyInfo propertyInfo);

    /**
     * 根据时间和房子id获取相关记录信息
     */
    void deleteInfoByHouIdAndTime(@Param("houId") Integer houId,@Param("endDate") Date endDate);


    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<PropertyInfo>
     */
    IPage<PropertyInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param propertyInfo 
     * @return int
     */
    int add(PropertyInfo propertyInfo);

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
     * @param propertyInfo 
     * @return int
     */
    int updateData(PropertyInfo propertyInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return PropertyInfo
     */
    PropertyInfo findById(Long id);
}
