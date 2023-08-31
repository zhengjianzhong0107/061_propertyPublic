package com.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.stu.model.*;
import com.stu.util.JsonObject;
import com.stu.util.R;
import com.stu.service.IHouseService;
import com.stu.service.IOwnerService;
import com.stu.service.IPropertyInfoService;
import com.stu.service.IPropertyTypeService;
import com.stu.model.House;
import com.stu.model.Owner;
import com.stu.model.PropertyInfo;
import com.stu.model.PropertyType;
import com.stu.model.Userinfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
@RequestMapping("/propertyinfo")
public class PropertyInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IPropertyInfoService propertyInfoService;

    @Resource
    private IHouseService houseService;

    @Resource
    private IOwnerService ownerService;

    @Resource
    private IPropertyTypeService propertyTypeService;

    @RequestMapping("/queryPropertyAll")
    public JsonObject queryPropertyAll(PropertyInfo propertyInfo, String numbers,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "15") Integer limit){
        if(numbers!=null){
            House house=new House();
            house.setNumbers(numbers);
            propertyInfo.setHouse(house);
        }

        PageInfo<PropertyInfo> pageInfo=propertyInfoService.findPropertyInfoAll(page,limit,propertyInfo);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }


    @RequestMapping("/queryPropertyAll2")
    public JsonObject queryPropertyAll2(PropertyInfo propertyInfo, HttpServletRequest request,
                                       @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        Integer houId= owner.getHouseId();
        propertyInfo.setHouseId(houId);
        PageInfo<PropertyInfo> pageInfo=propertyInfoService.findPropertyInfoAll(page,limit,
                propertyInfo);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }


    @ApiOperation(value = "新增")
    @RequestMapping("/initData")
    public R initData(@RequestBody PropertyInfo propertyInfo){
        //获取开始时间  结束时间  备注
        List<House> list=houseService.findList();
        for(House house:list){
           //查询物业费收费的标准  建议 物业收费类型通过前台传值
            PropertyType type=propertyTypeService.findById(new Long(1));
            double price=type.getPrice();//收费标准
            Integer status= house.getStatus();
            if(status!=null || status!=0){//如果已经收房
                //物业费
                double money=  house.getArea()*price;
                propertyInfo.setMoney(money);
                propertyInfo.setHouseId(house.getId());
                propertyInfo.setStatus(0);
                propertyInfo.setTypeId(1);
                propertyInfoService.add(propertyInfo);
            }
        }

        return R.ok();
    }





    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        for(String id:list){
            Long idLong=new Long(id);
            propertyInfoService.delete(idLong);
        }
        return R.ok();
    }


    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(Integer id){
        PropertyInfo propertyInfo =new PropertyInfo();
        propertyInfo.setId(id);
        propertyInfo.setStatus(1);
        int num=propertyInfoService.updateData(propertyInfo);
        if(num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<PropertyInfo> findListByPage(@RequestParam Integer page,
                                              @RequestParam Integer pageCount){
        return propertyInfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public PropertyInfo findById(@PathVariable Long id){
        return propertyInfoService.findById(id);
    }

}
