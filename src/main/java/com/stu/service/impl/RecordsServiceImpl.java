package com.stu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.dao.RecordsMapper;
import com.stu.model.RecordVo;
import com.stu.model.Records;
import com.stu.service.IRecordsService;
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
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records> implements IRecordsService {

    @Autowired
    private RecordsMapper recordsDao;

    @Override
    public PageInfo<RecordVo> findRecordsAll(int page, int limit, RecordVo recordVo) {
        PageHelper.startPage(page,limit);
        List<RecordVo> list=recordsDao.queryRecordsAll(recordVo);
        return new PageInfo<>(list);
    }

    @Override
    public Records queryByHouIdAndTypeId(Integer houId, Integer typeId) {
        return recordsDao.queryByHouIdAndTypeId(houId,typeId);
    }

    @Override
    public int add(Records building){
        return baseMapper.insert(building);
    }


    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Records building){
        return baseMapper.updateById(building);
    }

    @Override
    public Records findById(Long id){
        return  baseMapper.selectById(id);
    }



}
