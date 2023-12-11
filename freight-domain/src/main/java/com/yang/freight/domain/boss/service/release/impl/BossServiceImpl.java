package com.yang.freight.domain.boss.service.release.impl;

import com.yang.freight.common.Constants;
import com.yang.freight.common.HashedPassword;
import com.yang.freight.common.Return;
import com.yang.freight.domain.boss.model.req.*;
import com.yang.freight.domain.boss.model.vo.BossVO;
import com.yang.freight.domain.boss.repository.IBossRepository;
import com.yang.freight.domain.boss.service.release.IBossService;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.domain.support.password.IEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@Service
public class BossServiceImpl implements IBossService {

    @Resource
    private IBossRepository bossRepository;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Resource
    private IEncryption encryption;

    private Logger logger = LoggerFactory.getLogger(BossServiceImpl.class);

    @Override
    public BossVO createBoss(InitBossReq req) throws NoSuchAlgorithmException {
        BossVO bossVO = new BossVO();
        bossVO.setBossId(idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        HashedPassword hashedPassword = encryption.encryptPassword(req.getPassword());
        bossVO.setHashedPassword(hashedPassword.getHashedPassword());
        bossVO.setSalt(hashedPassword.getSalt());
        bossVO.setPhone(req.getPhone());
        boolean b = bossRepository.addBoss(bossVO);
        if (b) {
            return bossVO;
        }else {
            return null;
        }

    }

    @Override
    public void updateBoss(UpdateBossReq req) {

    }

    @Override
    public boolean updatePassword(UpdatePasswordReq req) {
        //1. 验证密码是否正确
        BossVO bossVO = bossRepository.queryById(req.getBossId());
        if (null == bossVO) {
            logger.info("无对应的老板信息bossId:{}",req.getBossId());
            return false;
        }
        try {
            boolean resultVerify = encryption.verifyPassword(req.getBeforePassword(), new HashedPassword(bossVO.getHashedPassword(), bossVO.getSalt()));

            if (resultVerify) {
                //2. 更新密码
                HashedPassword hashedPassword = encryption.encryptPassword(req.getAfterPassword());
                bossVO.setBossId(req.getBossId());
                bossVO.setHashedPassword(hashedPassword.getHashedPassword());
                bossVO.setSalt(hashedPassword.getSalt());
                return bossRepository.updatePassword(bossVO);
            }
            logger.info("历史密码输入错误");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateName(UpdateNameReq req) {
        //1. 更新姓名
        BossVO bossVO = new BossVO();
        bossVO.setBossId(req.getBossId());
        bossVO.setBossName(req.getBossName());
        return bossRepository.updateName(bossVO);
    }


    @Override
    public boolean updatePhone(UpdatePhoneReq req) {

        BossVO bossVO = new BossVO();
        bossVO.setBossId(req.getBossId());
        bossVO.setPhone(req.getAfterPhone());
        boolean b = bossRepository.updatePhone(bossVO);
        return b;
    }

//    @Override
//    public boolean releaseCargoInfo(ReleaseCargoInfoReq req) {
//        CargoVO cargoVO = new CargoVO();
//        BeanUtils.copyProperties(req,cargoVO);
//        cargoVO.setCargoId(idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
//        cargoVO.setStock(req.getCargoWeight());
//        boolean result = bossRepository.addCargo(cargoVO);
//        return result;
//    }

    @Override
    public BossVO queryByPhone(String phone) {
        return bossRepository.queryByPhone(phone);
    }

    @Override
    public BossVO checkAndInit(String phone) {
        return null;
    }

    @Override
    public Return<BossVO> bossLogon(InitBossReq req) {
        BossVO bossVO = bossRepository.queryByPhone(req.getPhone());

        if (null == bossVO) {
            logger.info("用户未注册！");
            return Return.error("用户未注册！");
        }

        HashedPassword hashedPassword = new HashedPassword(bossVO.getHashedPassword(),bossVO.getSalt());

        try {
            boolean result = encryption.verifyPassword(req.getPassword(), hashedPassword);
            if (result) {
                logger.info("登录成功");
                return Return.success(bossVO);
            }else {
                logger.info("密码错误");
                return Return.error("密码错误，请重新输入！");
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Return.error("登录失败，未知错误，请重试~");
    }

}
