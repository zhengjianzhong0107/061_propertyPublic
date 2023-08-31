package com.stu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.dao.ParkingMapper;
import com.stu.model.Parking;
import com.stu.service.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ParkingServiceImpl extends ServiceImpl<ParkingMapper, Parking> implements IParkingService {

    @Autowired
    private ParkingMapper parkingDao;

    @Override
    public PageInfo<Parking> findParkAll(int page, int pageSize, String numbers) {
        PageHelper.startPage(page,pageSize);
        //查询的结果集
        List<Parking> list=parkingDao.queryParkAll(numbers);
        PageInfo<Parking> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public IPage<Parking> findListByPage(Integer page, Integer pageCount){
        IPage<Parking> wherePage = new Page<>(page, pageCount);
        Parking where = new Parking();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Parking parking){
        return baseMapper.insert(parking);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Parking parking){
        return baseMapper.updateById(parking);
    }

    @Override
    public Parking findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<Parking> queryParkingAll() {
        return null;
    }

    @Override
    public List<Parking> queryParkingByStatus() {
        return parkingDao.queryParkAllByStatus();
    }


}
