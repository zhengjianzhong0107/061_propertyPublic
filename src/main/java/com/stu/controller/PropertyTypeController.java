package com.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stu.model.PropertyType;
import com.stu.service.IPropertyTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
@Api(tags = {""})
@RestController
@RequestMapping("/propertyType")
public class PropertyTypeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IPropertyTypeService propertyTypeService;


    @RequestMapping("/queryAll")
    public List<PropertyType> queryList(){
        List<PropertyType> list=  propertyTypeService.findAll();
        return list;
    }



    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody PropertyType propertyType){
        return propertyTypeService.add(propertyType);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return propertyTypeService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody PropertyType propertyType){
        return propertyTypeService.updateData(propertyType);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<PropertyType> findListByPage(@RequestParam Integer page,
                                              @RequestParam Integer pageCount){
        return propertyTypeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public PropertyType findById(@PathVariable Long id){
        return propertyTypeService.findById(id);
    }

}
