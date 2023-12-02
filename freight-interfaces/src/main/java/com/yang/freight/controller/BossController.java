package com.yang.freight.controller;

import com.yang.freight.common.Return;
import com.yang.freight.domain.boss.model.req.InitBossReq;
import com.yang.freight.domain.boss.model.vo.BossVO;
import com.yang.freight.domain.boss.service.release.IBossService;
import com.yang.freight.domain.driver.model.req.InitDriverReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/29
 * @Copyright：
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/boss")
public class BossController {


    @Resource
    private IBossService bossService;

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(BossController.class);


    @PostMapping("/login")
    public Return<BossVO> login(@RequestBody Map map, HttpSession session) throws NoSuchAlgorithmException {
        String phone = (String)map.get("phone");
        String code = (String)map.get("code");
        String password = (String)map.get("password");
        Object codeInRedis = redisTemplate.opsForValue().get(phone);

        if (null != codeInRedis && codeInRedis.equals(code)) {
            BossVO bossVO = bossService.queryByPhone(phone);
            if (null == bossVO) {
                InitBossReq initBossReq = new InitBossReq();
                initBossReq.setPhone(phone);
                initBossReq.setPassword(password);
                BossVO boss = bossService.createBoss(initBossReq);
                logger.info("是否注册成功：{}",null != boss);
                session.setAttribute("boss",boss.getBossId());

                logger.info("登陆成功，bossId：{}",boss.getBossId());
                redisTemplate.delete(phone);
                return Return.success(boss);
            }else {
                logger.info("注册失败，手机号已被注册：{}",bossVO.getPhone());
                return Return.error("该手机号已被注册过!");
            }
        }
        return Return.error("验证码错误！");
    }

    @PostMapping("/logon")
    public Return<BossVO> logon(@RequestBody InitBossReq req,HttpSession session){
        logger.info("用户登录：手机号：{}，密码：{}",req.getPhone(),req.getPassword());
        return bossService.bossLogon(req);
    }

}
