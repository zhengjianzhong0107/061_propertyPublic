package com.stu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.dao.BuildingMapper;
import com.stu.model.Building;
import com.stu.service.IBuildingService;
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
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {

    @Autowired
    private BuildingMapper buildingDao;
    @Override
    public PageInfo<Building> findBuildAll(int page, int pageSize, String numbers) {
        PageHelper.startPage(page,pageSize);
        List<Building> list=buildingDao.queryBuildAll(numbers);
        return new PageInfo<>(list);
    }

    @Override
    public IPage<Building> findListByPage(Integer page, Integer pageCount){
        IPage<Building> wherePage = new Page<>(page, pageCount);
        Building where = new Building();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Building building){
        return baseMapper.insert(building);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Building building){
        return baseMapper.updateById(building);
    }

    @Override
    public Building findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public Building queryBuildById(Integer buildId) {
        return buildingDao.queryBuildById(buildId);
    }
}
