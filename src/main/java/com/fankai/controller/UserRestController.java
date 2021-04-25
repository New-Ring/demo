package com.fankai.controller;

import com.fankai.annotation.Bpm;
import com.fankai.aspect.BizException;
import com.fankai.bpm.BpmUtil;
import com.fankai.entity.User;
import com.fankai.enums.ProcessDefId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {
    @PostMapping("/user")
    public boolean insert(@RequestBody User user) {
        System.out.println("开始新增...");
        //如果姓名为空就手动抛出一个自定义的异常！
        if(user.getName()==null){
            throw  new BizException("-1","用户姓名不能为空！");
        }
        return true;
    }

    @PutMapping("/user")
    public boolean update(@RequestBody User user) {
        System.out.println("开始更新...");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str=null;
        str.equals("111");
        return true;
    }

    @DeleteMapping("/user")
    public boolean delete(@RequestBody User user)  {
        System.out.println("开始删除...");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }

    @GetMapping("/user")
    @Bpm(detail = "注解aop案例",processDefId= ProcessDefId.PROJECT_AC_BUDGET_SEND)
    public List<User> findByUser(User user) throws Exception{
        System.out.println("开始查询...:"+user.getName());
        List<User> userList =new ArrayList<>();
        User user2=new User();
        user2.setId(1L);
        user2.setName(user.getName());
        user2.setAge(18);
        userList.add(user2);
        try{
            BpmUtil.createBpmFlow(null,null,null,null,null,null);
        }catch (Exception e){
            throw new Exception("审批流发起失败");
        }

        return userList;
    }
}
