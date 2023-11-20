package com.yang.freight.test.dao;

import com.yang.freight.infrastructure.dao.IAuthenticationDao;
import com.yang.freight.infrastructure.po.Authentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationDaoTest {

    private Logger logger = LoggerFactory.getLogger(AuthenticationDaoTest.class);

    @Resource
    private IAuthenticationDao authenticationDao;


//    void insert(Authentication authentication);
//
//    Authentication queryById(Long driverId);
//
//    int updateStatus(int status,long driverId);

    @Test
    public void insertTest() {
        Authentication authentication = new Authentication();
        authentication.setAuthenticationId(10001L);
        authentication.setDriverId(100001L);
        authentication.setAuthenticationStatus(0);
        authentication.setIdCardNumber("012345678901234567");
        authentication.setIdCardValidFrom(new Date(1949,10,1));
        authentication.setIdCardValidTo(new Date(2023,11,20));
        authentication.setDriverLicense("司机驾驶证测试信息");
        authentication.setDriverLicenseValidTo(new Date(2025,10,1));
        authentication.setQualificationCertificate("司机资格证书");

        authenticationDao.insert(authentication);
    }
}
