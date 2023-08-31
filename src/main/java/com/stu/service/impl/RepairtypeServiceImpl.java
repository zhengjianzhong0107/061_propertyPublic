package com.stu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.dao.RepairtypeMapper;
import com.stu.model.Repairtype;
import com.stu.service.IRepairtypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
@Service
public class RepairtypeServiceImpl extends ServiceImpl<RepairtypeMapper, Repairtype> implements IRepairtypeService {

    @Override
    public IPage<Repairtype> findListByPage(Integer page, Integer pageCount){
        IPage<Repairtype> wherePage = new Page<>(page, pageCount);
        Repairtype where = new Repairtype();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public List<Repairtype> findList() {
        return   baseMapper.selectList(null);
    }

    @Override
    public int add(Repairtype repairtype){
        return baseMapper.insert(repairtype);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Repairtype repairtype){
        return baseMapper.updateById(repairtype);
    }

    @Override
    public Repairtype findById(Long id){
        return  baseMapper.selectById(id);
    }
}
