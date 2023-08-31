package com.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.stu.util.JsonObject;
import com.stu.util.R;
import com.stu.model.Clockinnew;
import com.stu.model.Owner;
import com.stu.model.Userinfo;
import com.stu.service.IClockInNewService;
import com.stu.service.IOwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@RequestMapping("/clockinnew")
public class ClockInNewController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IClockInNewService clockinnewService;

    @Resource
    private IOwnerService ownerService;


    @RequestMapping("/queryClockInAll")
    public JsonObject queryClockInAll(Clockinnew clockinnew, String name,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "15") Integer pageSize
                                      ){
        if(name!=null){
            Owner owner = new Owner();
            owner.setUsername(name);
            clockinnew.setOwner(owner);
        }
        PageInfo<Clockinnew> pageInfo= clockinnewService.queryClockInAll(pageNum,pageSize,clockinnew);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());
    }

    @RequestMapping("/queryClockInAll2")
    public JsonObject queryClockInAll2(Clockinnew clockinnew, HttpServletRequest request,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "15") Integer pageSize
                                      ){
        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();

        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        clockinnew.setOwnerId(owner.getId());
        PageInfo<Clockinnew> pageInfo= clockinnewService.queryClockInAll(pageNum,pageSize,clockinnew);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());
    }

    @RequestMapping("/queryOwner")
    public  List<Owner> queryOwner(HttpServletRequest request,Clockinnew clockinnew){
        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();

        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        List<Owner> list = new ArrayList<>();
        list.add(owner);
        return list;
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public R add(@RequestBody Clockinnew clockinnew, HttpServletRequest request){

        /**
         * 打卡前先判断这个用户在当天是否已经打卡，若已打卡提示您已经打卡
         * 通过ownerId,查询最近一条数据日期是否是今天，判断是否已经打卡，若填的日期不是今天，那么提示日期错误
         * queryCountByOwnIdAndTime()，返回查找到记录的条数，条数大于0，表示已经打卡，反之未打卡
         *
         * 若未打卡则，添加打卡信息
         * 通过username查找对应的owner，可获取到其id
         */
        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String todayDate = df.format(new Date());//今天的日期
        //System.out.println(todayDate);

        Owner owner = ownerService.queryOwnerByName(username);
        Integer ownerId = owner.getId();

        Date timeFlag = clockinnewService.queryCountByOwnId(ownerId);//数据库查到业主的最近一次打卡日期
        String timeFlag1="";
        if (timeFlag!=null){
            timeFlag1=df.format(timeFlag);//查到的最近打卡日期
        }

        if (timeFlag1.equals(todayDate)){//若今天日期等于数据库中已经查到业主的时间，则说明已经打卡
                return R.fail(400,"今日已打卡，请勿重复打卡");
        }
        //不相等，证明数据库还没有这个业主今日的打卡记录，正常打卡
        clockinnew.setOwnerId(owner.getId());

        //若为疑似病例或者确诊那么提示请填写备注
        if (clockinnew.getType1()==1 || clockinnew.getType2()==1){
            if (clockinnew.getRemarks()==null || clockinnew.getRemarks()==""){
                return R.fail(400,"请填写备注并详细说明");
            }
        }
        int num = clockinnewService.add(clockinnew);
        return R.ok();

    }

    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        for(String id:list){
            Long idLong=new Long(id);
            clockinnewService.delete(idLong);
        }
        return R.ok();
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Clockinnew clockinnew){
        return clockinnewService.updateData(clockinnew);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Clockinnew> findListByPage(@RequestParam Integer page,
                                            @RequestParam Integer pageCount){
        return clockinnewService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Clockinnew findById(@PathVariable Long id){
        return clockinnewService.findById(id);
    }

}
