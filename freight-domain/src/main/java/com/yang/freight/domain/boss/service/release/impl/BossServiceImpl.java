package com.yang.freight.domain.boss.service.release.impl;

import com.yang.freight.common.Constants;
import com.yang.freight.common.HashedPassword;
import com.yang.freight.common.Return;
import com.yang.freight.domain.boss.model.req.BossUpdatePasswordReq;
import com.yang.freight.domain.boss.model.req.InitBossReq;
import com.yang.freight.domain.boss.model.req.ReleaseCargoInfoReq;
import com.yang.freight.domain.boss.model.req.UpdateBossReq;
import com.yang.freight.domain.boss.model.vo.BossVO;
import com.yang.freight.domain.boss.repository.IBossRepository;
import com.yang.freight.domain.boss.service.release.IBossService;
import com.yang.freight.domain.driver.model.vo.DriverVO;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.domain.support.password.IEncryption;
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
    public void updatePassword(BossUpdatePasswordReq req) {

    }

    @Override
    public void releaseCargoInfo(ReleaseCargoInfoReq req) {

    }

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
            return Return.error("用户未注册！");
        }

        HashedPassword hashedPassword = new HashedPassword(bossVO.getHashedPassword(),bossVO.getSalt());

        try {
            boolean result = encryption.verifyPassword(req.getPassword(), hashedPassword);
            if (result) {
                return Return.success(bossVO);
            }else {
                return Return.error("密码错误，请重新输入！");
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Return.error("登录失败，未知错误，请重试~");
    }
}