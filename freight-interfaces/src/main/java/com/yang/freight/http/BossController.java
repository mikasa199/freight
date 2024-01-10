package com.yang.freight.http;

import com.yang.freight.common.Return;
import com.yang.freight.domain.boss.model.req.*;
import com.yang.freight.domain.boss.model.vo.BossVO;
import com.yang.freight.domain.boss.service.release.IBossService;
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
        logger.info("用户注册(老板) phone:{},code:{},password:{}",phone,code,password);
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

                logger.info("登录成功，bossId：{}",boss.getBossId());
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
        Return<BossVO> bossVOReturn = bossService.bossLogon(req);
        if (1 == bossVOReturn.getCode()) {
            session.setAttribute("boss",bossVOReturn.getData().getBossId());

            logger.info("登录成功，bossId：{}",bossVOReturn.getData().getBossId());
        }
        return bossVOReturn;
    }

    /**
     * 验证码登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/logon/code")
    public Return<BossVO> loginByCode (@RequestBody Map map, HttpSession session) {

        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        //1. 获取session中发送给用户的验证码
        //Object codeInSession = session.getAttribute(phone);

        //1.1 从redis中获取缓存的验证码
        Object codeInRedis = redisTemplate.opsForValue().get(phone);


        logger.info("登录信息 phone:{},code:{},codeInSession:{}",phone,code,codeInRedis);


        //2. 检验验证码是否与发送的验证匹配
        if (null != codeInRedis && codeInRedis.equals(code)) {
            BossVO bossVO = bossService.checkAndInit(phone);

            //将用户id存入session中，标识用户已登录
            session.setAttribute("boss",bossVO.getBossId());
            logger.info("登录成功bossId:{}",bossVO.getBossId());

            //登陆成功后将redis中的验证码删除
            redisTemplate.delete(phone);
            return Return.success(bossVO);
        }
        return Return.error("验证码错误");
    }

//    @PostMapping("/addCargo")
//    public Return<String> addCargo(@RequestBody ReleaseCargoInfoReq req) {
//        logger.info(req.toString());
//        logger.info("开始发布货物信息：{}",req.toString());
//        boolean b = bossService.releaseCargoInfo(req);
//        if (b) {
//            return Return.success("发布货物信息成功，等待司机接单");
//        }else {
//            return Return.error("发布货物信息失败，请稍后重试");
//        }
//    }

    @PostMapping("/update/name")
    public Return<String> updateName(@RequestBody UpdateNameReq req) {
        logger.info(req.toString());
        boolean b = bossService.updateName(req);
        return b ? Return.success("姓名更新成功") : Return.error("姓名更新失败，请重试");
    }

    @PostMapping("/update/password")
    public Return<String> updatePassword(@RequestBody UpdatePasswordReq req) {
        logger.info(req.toString());
        boolean b = bossService.updatePassword(req);
        return b ? Return.success("密码更新成功") : Return.error("历史密码错误，请重新输入");
    }

    @PostMapping("/update/phone")
    public Return<String> updatePhone(@RequestBody UpdatePhoneReq req) {
        logger.info(req.toString());
        //1. 检验验证码是否正确
        Object codeInRedis = redisTemplate.opsForValue().get(req.getBeforePhone());
        if (!codeInRedis.equals(req.getCode())) {
            return Return.error("验证码错误");
        }
        boolean b = bossService.updatePhone(req);
        return b ? Return.success("手机号更换成功") : Return.error("手机号更新失败");
    }


    @GetMapping("/bossId")
    public Return<BossVO> queryById(Long bossId) {
        return bossService.queryById(bossId);
    }
}
