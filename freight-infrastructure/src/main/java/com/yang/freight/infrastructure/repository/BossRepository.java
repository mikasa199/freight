package com.yang.freight.infrastructure.repository;

import com.yang.freight.common.Constants;
import com.yang.freight.domain.boss.model.req.InitBossReq;
import com.yang.freight.domain.boss.model.vo.BossVO;
import com.yang.freight.domain.boss.repository.IBossRepository;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.domain.support.password.IEncryption;
import com.yang.freight.infrastructure.dao.IBossDao;
import com.yang.freight.infrastructure.po.Boss;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/1
 * @Copyright：
 */
@Repository
public class BossRepository implements IBossRepository {

    @Resource
    private IBossDao bossDao;

    @Resource
    private IEncryption encryption;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;


    @Override
    public boolean addBoss(BossVO bossVO) {

        Boss boss = new Boss();
        BeanUtils.copyProperties(bossVO,boss);
        bossDao.insert(boss);
        return true;
    }

    @Override
    public BossVO queryByPhone(String phone) {

        Boss boss = bossDao.queryByPhone(phone);
        if (null != boss) {
            BossVO bossVO = new BossVO();
            bossVO.setBossId(boss.getBossId());
            bossVO.setPhone(phone);
            bossVO.setBossName(boss.getBossName());
            bossVO.setHashedPassword(boss.getHashedPassword());
            bossVO.setSalt(boss.getSalt());
            return bossVO;
        }else {
            return null;
        }
    }
}
