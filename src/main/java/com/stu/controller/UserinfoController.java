package com.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.stu.util.JsonObject;
import com.stu.util.R;
import com.stu.model.Userinfo;
import com.stu.service.IOwnerService;
import com.stu.service.IUserinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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
@RequestMapping("/userinfo")
public class UserinfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserinfoService userinfoService;

    @Resource
    private IOwnerService ownerService;

    @RequestMapping("/queryUserInfoAll")
    public JsonObject queryUserInfoAll(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "15") Integer limit,
                                       Userinfo userinfo){
        JsonObject object=new JsonObject();
        PageInfo<Userinfo> pageInfo= userinfoService.findUserinfoAll(page,limit,userinfo);
        object.setCode(0);
        object.setMsg("ok");
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;
    }

    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String  ids){
        List<String> list= Arrays.asList(ids.split(","));
        //遍历遍历进行删除
        for(String id:list){
            Userinfo user = userinfoService.findById(Long.parseLong(id));
            ownerService.deleteOwnerUserByUserName(user.getUsername());
            userinfoService.delete(Long.parseLong(id));
        }
        return R.ok();
    }


    @ApiOperation(value = "新增")
    @RequestMapping("/add")
    public R add(@RequestBody Userinfo userinfo){
        userinfoService.add(userinfo);
        return R.ok();
    }


    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(String oldPwd,String newPwd,Integer id){
        //根据id获取当前的数据记录
        Userinfo user=userinfoService.findById(new Long(id));
        if(oldPwd.equals(user.getPassword())){//输入的老密码和原密码一致
            user.setPassword(newPwd);
            userinfoService.updateData(user);
            return R.ok();
        }else{
            return R.fail("两次密码不一致");
        }
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Userinfo> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount){
        return userinfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Userinfo findById(@PathVariable Long id){
        return userinfoService.findById(id);
    }

}
