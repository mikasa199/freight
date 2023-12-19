package com.yang.freight.infrastructure.repository;

import com.yang.freight.common.Constants;
import com.yang.freight.domain.boss.model.vo.BossVO;
import com.yang.freight.domain.boss.repository.IBossRepository;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.domain.support.password.IEncryption;
import com.yang.freight.infrastructure.dao.IBossDao;
import com.yang.freight.infrastructure.dao.ICargoDao;
import com.yang.freight.infrastructure.po.Boss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private ICargoDao cargoDao;

    @Resource
    private IEncryption encryption;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    private Logger logger = LoggerFactory.getLogger(BossRepository.class);


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
            logger.info(boss.toString());
            BossVO bossVO = new BossVO();
            bossVO.setBossId(boss.getBossId());
            bossVO.setPhone(phone);
            bossVO.setBossName(boss.getBossName());
            bossVO.setHashedPassword(boss.getHashedPassword());
            bossVO.setSalt(boss.getSalt());
            logger.info(bossVO.toString());
            return bossVO;
        }else {
            return null;
        }
    }

    @Override
    public BossVO queryById(long bossId) {
        Boss boss = bossDao.queryById(bossId);
        if (null != boss) {
            BossVO bossVO = new BossVO();
            bossVO.setBossId(boss.getBossId());
            bossVO.setBossName(boss.getBossName());
            bossVO.setPhone(boss.getPhone());
            bossVO.setHashedPassword(boss.getHashedPassword());
            bossVO.setSalt(boss.getSalt());
            return bossVO;
        }
        logger.info("没有查询到对应的老板信息 bossId:{}",bossId);
        return null;
    }

//    @Override
//    public boolean addCargo(CargoVO cargoVO) {
//        Cargo cargo = new Cargo();
//        BeanUtils.copyProperties(cargoVO,cargo);
//
//        //计算起点与终点之间的距离
//        String beginLocation = cargo.getBeginLocation();
//        String endLocation = cargo.getEndLocation();
//        String[] s1 = beginLocation.split(",");
//        String[] s2 = endLocation.split(",");
//        Double distance = 0.001 * LocationUtils.distance(new Location(Double.parseDouble(s1[0]), Double.parseDouble(s1[1])), new Location(Double.parseDouble(s2[0]), Double.parseDouble(s2[1])));
//        cargo.setDistance(new BigDecimal(distance));
//
//        int result = cargoDao.insert(cargo);
//        return result == 1;
//    }

    @Override
    public boolean updateName(BossVO bossVO) {
        Boss boss = new Boss();
        BeanUtils.copyProperties(bossVO,boss);
        int i = bossDao.updateBossName(boss);
        return i == 1;
    }

    @Override
    public boolean updatePassword(BossVO bossVO) {
        Boss boss = new Boss();
        BeanUtils.copyProperties(bossVO,boss);
        int i = bossDao.updatePassword(boss);
        return i == 1;
    }

    @Override
    public boolean updatePhone(BossVO bossVO) {
        Boss boss = new Boss();
        BeanUtils.copyProperties(bossVO,boss);
        int i = bossDao.updatePhone(boss);
        return i == 1;
    }
}
