package com.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stu.model.Repairtype;
import com.stu.service.IRepairtypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
@Api(tags = {""})
@RestController
@RequestMapping("/repairtype")
public class RepairtypeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IRepairtypeService repairtypeService;



    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Repairtype repairtype){
        return repairtypeService.add(repairtype);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return repairtypeService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Repairtype repairtype){
        return repairtypeService.updateData(repairtype);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Repairtype> findListByPage(@RequestParam Integer page,
                                            @RequestParam Integer pageCount){
        return repairtypeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Repairtype findById(@PathVariable Long id){
        return repairtypeService.findById(id);
    }

}
