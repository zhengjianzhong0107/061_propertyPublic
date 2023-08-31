package com.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.stu.model.Owner;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
public interface IOwnerService extends IService<Owner> {

    PageInfo<Owner> findOwnerAll(int page, int pagesize, Owner owner);
    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Owner>
     */
    IPage<Owner> findListByPage(Integer page, Integer pageCount);

    Owner queryOwnerByName(String username);

    /**
     * 添加
     *
     * @param owner 
     * @return int
     */
    int add(Owner owner);

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
     * @param owner 
     * @return int
     */
    int updateData(Owner owner);

    /**
     * id查询数据
     *
     * @param id id
     * @return Owner
     */
    Owner findById(Long id);

    void deleteOwnerUserByUserName(String username);

    Owner queryOwnerById(long parseLong);
}
