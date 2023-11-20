package com.yang.freight.test.dao;

import com.yang.freight.common.HashedPassword;
import com.yang.freight.domain.boss.model.req.BossUpdatePasswordReq;
import com.yang.freight.domain.support.password.IEncryption;
import com.yang.freight.infrastructure.dao.IBossDao;
import com.yang.freight.infrastructure.po.Boss;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BossDaoTest {

    private Logger logger = LoggerFactory.getLogger(BossDaoTest.class);

    @Resource
    private IBossDao bossDao;

    @Resource
    private IEncryption encryption;

//    void insert(Boss boss);
//
//    int updateBoos(Boss boss);
//
//    int updatePassword(BossUpdatePasswordReq req);
//
//    int deleteById(long boosId);
//
//    Boss queryById(long bossId);

    @Test
    public void insertTest() throws NoSuchAlgorithmException {
        Boss boss = new Boss();
        boss.setBossId(100001L);
        boss.setBossName("老板测试名");
        boss.setPhone("19857295829");
        HashedPassword hashedPassword = encryption.encryptPassword("1234567");
        boss.setHashedPassword(hashedPassword.getHashedPassword());
        boss.setSalt(hashedPassword.getSalt());
        bossDao.insert(boss);
    }

    @Test
    public void updatePasswordTest() throws NoSuchAlgorithmException {
        BossUpdatePasswordReq req = new BossUpdatePasswordReq();
        req.setBossId(100001L);
        HashedPassword hashedPassword = encryption.encryptPassword("7654321");
        req.setHashedPassword(hashedPassword.getHashedPassword());
        req.setSalt(hashedPassword.getSalt());
        int i = bossDao.updatePassword(req);
        if (i == 1) {
            logger.info("更新成功");
            Boss boss = bossDao.queryById(100001L);
            HashedPassword hashedPassword1 = new HashedPassword();
            hashedPassword1.setSalt(boss.getSalt());
            hashedPassword1.setHashedPassword(boss.getHashedPassword());
            boolean result = encryption.verifyPassword("7654321", hashedPassword1);
            logger.info("result:{}",result);
        } else {
            logger.info("更新失败");
        }
    }

    @Test
    public void deleteByIdTest() {
        int i = bossDao.deleteById(100001L);
        if (i == 1) {
            logger.info("删除成功");
        } else {
            logger.info("删除失败");
        }
    }
}
