package com.stu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.dao.OwnerMapper;
import com.stu.model.Owner;
import com.stu.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements IOwnerService {

    @Autowired
    private OwnerMapper ownerDao;
    @Override
    public PageInfo<Owner> findOwnerAll(int page, int pagesize, Owner owner) {
        PageHelper.startPage(page,pagesize);
        List<Owner> list=ownerDao.queryOwnerAll(owner);
        PageInfo<Owner> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public IPage<Owner> findListByPage(Integer page, Integer pageCount){
        IPage<Owner> wherePage = new Page<>(page, pageCount);
        Owner where = new Owner();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public Owner queryOwnerByName(String username) {
        return ownerDao.queryOwnerByName(username);
    }

    @Override
    public int add(Owner owner){
        return baseMapper.insert(owner);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Owner owner){
        return baseMapper.updateById(owner);
    }

    @Override
    public Owner findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public void deleteOwnerUserByUserName(String username) {
        ownerDao.deleteOwnerUserByUserName(username);
    }

    @Override
    public Owner queryOwnerById(long parseLong) {
        return ownerDao.queryOwnerById(parseLong);
    }
}
