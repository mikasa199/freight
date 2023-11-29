package com.yang.freight.test.dao;

import com.yang.freight.domain.driver.model.req.SubCargoReq;
import com.yang.freight.infrastructure.dao.ICargoDao;
import com.yang.freight.infrastructure.po.Cargo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CargoDaoTest {

    @Resource
    private ICargoDao cargoDao;

    private Logger logger = LoggerFactory.getLogger(CargoDaoTest.class);


//    void insert(Cargo cargo);
//
//
//    int subStock(SubCargoReq req);

    @Test
    public void insertTest() {
        Cargo cargo = new Cargo();
        cargo.setCargoId(100001L);
        cargo.setBossId(100001L);
        cargo.setCargoName("货物测试名");
        cargo.setCargoWeight(10000L);
        cargo.setBeginTime(new Date(2023,11,20));
        cargo.setEndTime(new Date(2023,11,30));
        cargo.setBeginLocation("苏州");
        cargo.setEndLocation("武汉");
        cargo.setValue(80000L);
        cargo.setStock(10000L);
        cargo.setInfo("货物备注信息");

        cargoDao.insert(cargo);

    }

    @Test
    public void subStockTest() {
        SubCargoReq req = new SubCargoReq();
        req.setCargoId(100001L);
        req.setSubStock(8000L);

        int i = cargoDao.subStock(req);

        if (i == 1) {
            logger.info("扣减库存成功");
        }else {
            logger.info("扣减库存失败");
        }
    }

    @Test
    public void queryListTest() {
        List<Cargo> cargoList = cargoDao.queryList(0, 1, "");
        logger.info("list size:{}",cargoList.size());
    }
}
