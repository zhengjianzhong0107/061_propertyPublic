package com.stu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.dao.HouseMapper;
import com.stu.model.House;
import com.stu.service.IHouseService;
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
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements IHouseService {
    @Autowired
    private HouseMapper houseDao;
    @Override
    public PageInfo<House> findHouseAll(int page, int pagesize, String numbers) {
        PageHelper.startPage(page,pagesize);
        List<House> list=houseDao.findHouseAll(numbers);
        PageInfo<House> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<House> findList() {
        return baseMapper.selectList(null);
    }

    @Override
    public IPage<House> findListByPage(Integer page, Integer pageCount){
        IPage<House> wherePage = new Page<>(page, pageCount);
        House where = new House();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(House house){
        return baseMapper.insert(house);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(House house){
        return baseMapper.updateById(house);
    }

    @Override
    public House findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public House queryHouseById(Integer houId) {
        return houseDao.queryHouseById(houId);
    }
}
