package com.yang.freight.controller;

import com.yang.freight.common.Return;
import com.yang.freight.domain.driver.model.req.InitDriverReq;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.driver.model.vo.DriverVO;
import com.yang.freight.domain.driver.service.deploy.IDriverDeploy;
import com.yang.freight.infrastructure.po.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/24
 * @Copyright：
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

    private Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Resource
    private IDriverDeploy driverDeploy;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public Return<String> addDriver(@RequestBody InitDriverReq req) {

        //TODO 需要添加逻辑（判断用户手机号是否已经注册过）
        //两种方案1.设置phone数据库唯一字段，但是插入失败会报错，需要对报错

        boolean result = driverDeploy.createDriver(req);
        if (result) {
            return Return.success("新增司机成功");
        }else {
            return Return.error("新增司机失败");
        }

    }


    @PostMapping("/login")
    public Return<String> login(@RequestBody InitDriverReq req) {
        // 1. 从数据库中获取对应手机号的账户密码

        // 2. 如果查询到密码，则和用户输入的密码进行比较

        Return<String> stringReturn = driverDeploy.driverLogin(req);
        return stringReturn;
    }

    @PostMapping("/sendMsg")
    public Return<String> sendMsg (@RequestParam String phone) throws Exception {
        logger.info("给用户phone：{} 发送验证码",phone);
        if (null !=phone) {
            //实际发送验证码
            String code = driverDeploy.sendMsg(phone);

            //测试使用
            //String code = "1234";

            //1.将用户手机号和验证码存入session中
            //session.setAttribute(phone,code);

            //2.将用户的手机号和验证码存入redis中，并设置有效期五分钟
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);


            logger.info("phone:{},code:{}",phone,code);
            return Return.success("发送验证码成功");
        }
        return Return.error("出错啦");
    }

    @PostMapping("/login/code")
    public Return<DriverVO> loginByCode (@RequestBody Map map, HttpSession session) {
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        //1. 获取session中发送给用户的验证码
        //Object codeInSession = session.getAttribute(phone);

        //1.1 从redis中获取缓存的验证码
        Object codeInRedis = redisTemplate.opsForValue().get(phone);


        logger.info("登录信息 phone:{},code:{},codeInSession:{}",phone,code,codeInRedis);


        //2. 检验验证码是否与发送的验证匹配
        if (null != codeInRedis && codeInRedis.equals(code)) {
            DriverVO driverVO = driverDeploy.checkAndInit(phone);

            //将用户id存入session中，标识用户已登录
            session.setAttribute("driver",driverVO.getDriverId());
            logger.info("登录成功studentId:{}",driverVO.getDriverId());

            //登陆成功后将redis中的验证码删除
            redisTemplate.delete(phone);
            return Return.success(driverVO);
        }
        return Return.error("验证码错误");
    }

    public Return<List<CargoVO>> queryCargoPages() {
        return null;
    }
}
