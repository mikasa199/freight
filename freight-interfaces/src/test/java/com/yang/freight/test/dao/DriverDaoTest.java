package com.yang.freight.test.dao;

import com.yang.freight.common.HashedPassword;
import com.yang.freight.domain.driver.model.req.DriverUpdatePasswordReq;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.domain.support.password.IEncryption;
import com.yang.freight.infrastructure.dao.IDriverDao;
import com.yang.freight.infrastructure.po.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
public class DriverDaoTest {

    @Resource
    private IDriverDao driverDao;

    @Resource
    private IEncryption encryption;

    private Logger logger = LoggerFactory.getLogger(DriverDaoTest.class);



    @Test
    public void insertDriverTest() throws NoSuchAlgorithmException {
        Driver driver = new Driver();
        driver.setDriverId(100001L);
        driver.setDriverName("司机名");
        driver.setPhone("19857295839");
        HashedPassword hashedPassword = encryption.encryptPassword("1234567");
        driver.setHashedPassword(hashedPassword.getHashedPassword());
        driver.setSalt(hashedPassword.getSalt());
        driverDao.insert(driver);
    }

    @Test
    public void queryByIdTest() throws NoSuchAlgorithmException {
        String s = "1234567";
        Driver driver = driverDao.queryById(100001L);
        HashedPassword hashedPassword = new HashedPassword();
        hashedPassword.setHashedPassword(driver.getHashedPassword());
        hashedPassword.setSalt(driver.getSalt());
        boolean result = encryption.verifyPassword(s, hashedPassword);
        logger.info("hashedPassword:{},password:{}",hashedPassword.getHashedPassword(),s);
        logger.info("result:{}",result);
    }

    @Test
    public void updatePasswordTest() throws NoSuchAlgorithmException {
        DriverUpdatePasswordReq req = new DriverUpdatePasswordReq();
        req.setDriverId(100001L);
        HashedPassword hashedPassword = encryption.encryptPassword("7654321");
        req.setHashedPassword(hashedPassword.getHashedPassword());
        req.setSalt(hashedPassword.getSalt());
        Driver driver1 = new Driver();
        BeanUtils.copyProperties(req,driver1);
        int i = driverDao.updateHashedPasswordInt(driver1);
        if (i == 1) {
            logger.info("更新成功");
        } else {
            logger.info("更新失败");
        }

        Driver driver = driverDao.queryById(100001L);
        HashedPassword hashedPassword1 = new HashedPassword();
        hashedPassword1.setHashedPassword(driver.getHashedPassword());
        hashedPassword1.setSalt(driver.getSalt());
        boolean result = encryption.verifyPassword("7654321", hashedPassword1);

        logger.info("result:{}",result);
    }

    @Test
    public void queryByPhoneTest() {
        Driver driver = driverDao.queryByPhone("19857295829");
        if (driver != null) {
            logger.info(driver.toString());
        }else {
            logger.info("driver is null");
        }
    }
}
