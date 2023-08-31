package com.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.stu.util.JsonObject;
import com.stu.util.R;
import com.stu.model.Clockin;
import com.stu.model.House;
import com.stu.model.Owner;
import com.stu.service.IBuildingService;
import com.stu.service.IClockinService;
import com.stu.service.IHouseService;
import com.stu.service.IOwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 
 * @since 2021-04-27
 */
@Api(tags = {""})
@RestController
@RequestMapping("/clockin")
public class ClockinController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IClockinService clockinService;

    @Resource
    private IOwnerService ownerService;

    @Resource
    private IBuildingService buildingService;

    @Resource
    private IHouseService houseService;

    @RequestMapping("/queryClockInAll")
    public JsonObject queryClockInAll(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "15") Integer pageSize,
                                      Clockin clockin){
        PageInfo<Clockin> pageInfo= clockinService.queryClockInAll(pageNum,pageSize,clockin);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @ApiOperation(value = "新增")
    @RequestMapping("/clockInAdd")
    public R add(String username){

        /**
         * 打卡前先判断这个用户在当天是否已经打卡，若已打卡提示您已经打卡
         * 通过两个参数ownerId+clockTime判断是否已经打卡，就是业主名+打卡时间
         * queryCountByOwnIdAndTime()，返回查找到记录的条数，条数大于0，表示已经打卡，反之未打卡
         *
         * 若未打卡则，添加打卡信息
         * 1、通过username查找对应的owner，可获取到其id
         * 2、通过owner中house_id，查找house
         * 3、通过house得到building_id
         * 4、new Date作为打卡时间
         */
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String todayDate = df.format(new Date());//今天的日期
        System.out.println(todayDate);

        Owner owner = ownerService.queryOwnerByName(username);
        Integer ownerId = owner.getId();
        Date timeFlag = clockinService.queryCountByOwnIdAndTime(ownerId);//数据库查到业主的日期
        String timeFlag1=df.format(timeFlag);

        if (timeFlag1.equals(todayDate)){//若今天日期等于数据库中已经查到业主的时间，则说明已经打卡
            return R.fail(400,"今日已打卡，请勿重复打卡");
        }
        //不相等，证明数据库还没有这个业主今日的打卡记录，正常打卡
        Integer houId = owner.getHouseId();
        House house = houseService.queryHouseById(houId);

        Integer buildId = house.getBuildingId();
        //Building building = buildingService.queryBuildById(buildId);

        Clockin clockin = new Clockin();
        clockin.setClockInTime(new Date());
        clockin.setOwnerId(ownerId);
        clockin.setHouseId(houId);
        clockin.setBuildingId(buildId);
        System.out.println(clockin);
        int num = clockinService.add(clockin);
        return R.ok();
    }

    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        for(String id:list){
            Long idLong=new Long(id);
            clockinService.delete(idLong);
        }
        return R.ok();
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Clockin clockin){
        return clockinService.updateData(clockin);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Clockin> findListByPage(@RequestParam Integer page,
                                         @RequestParam Integer pageCount){
        return clockinService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Clockin findById(@PathVariable Long id){
        return clockinService.findById(id);
    }

}
