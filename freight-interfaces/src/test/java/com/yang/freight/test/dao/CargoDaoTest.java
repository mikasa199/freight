package com.yang.freight.test.dao;

import com.yang.freight.common.Location;
import com.yang.freight.domain.cargo.model.req.SubCargoReq;
import com.yang.freight.infrastructure.dao.ICargoDao;
import com.yang.freight.infrastructure.po.Cargo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
        BigDecimal weight = new BigDecimal(10000);
        cargo.setCargoWeight(weight);
        cargo.setBeginTime(new Date(2023,11,20));
        cargo.setEndTime(new Date(2023,11,30));
        cargo.setBeginLocation("苏州");
        cargo.setEndLocation("武汉");

        BigDecimal value = new BigDecimal(80000);
        BigDecimal stock = new BigDecimal(10000);
        cargo.setValue(value);
        cargo.setStock(stock);
        cargo.setInfo("货物备注信息");

        cargoDao.insert(cargo);

    }

    @Test
    public void insertTest1() {
        BigDecimal weight = new BigDecimal(10000);
        BigDecimal value = new BigDecimal(80000);
        BigDecimal stock = new BigDecimal(10000);
        BigDecimal distance = new BigDecimal(10000);
        //北京市朝阳区阜通东大街6号
        Location beginLocation = new Location(116.310003, 39.991957);
        //湖州市吴兴区二环东路759号
        Location endLocation = new Location(120.134117,30.874626);

        for (int i=0;i < 20;i++) {
            Cargo cargo = new Cargo();
            cargo.setCargoId(100002L + i);
            cargo.setBossId(100001L);
            cargo.setCargoName("货物测试名");
            cargo.setCargoWeight(weight);
            cargo.setBeginTime(new Date(2023,11,20));
            cargo.setEndTime(new Date(2023,11,30));
            cargo.setBeginLocation(beginLocation.toCoordinateString());
            cargo.setEndLocation(endLocation.toCoordinateString());
            cargo.setDistance(distance);
            cargo.setValue(value);
            cargo.setStock(stock);
            cargo.setInfo("货物备注信息" + i);

            cargoDao.insert(cargo);
        }

    }

    @Test
    public void insertTest2() {
//        BigDecimal weight = new BigDecimal(10000);
//        BigDecimal value = new BigDecimal(80000);
//        BigDecimal stock = new BigDecimal(10000);
//        BigDecimal distance = new BigDecimal(10000);
        //北京市朝阳区阜通东大街6号
        Location beginLocation = new Location(116.310003, 39.991957);
        //湖州市吴兴区二环东路759号
        Location endLocation = new Location(120.134117,30.874626);

        for (int i=0;i < 20;i++) {
            BigDecimal weight = new BigDecimal(10000+6*i);
            BigDecimal value = new BigDecimal(80000+5*i);
            BigDecimal stock = new BigDecimal(10000+4*i);
            BigDecimal distance = new BigDecimal(10000+30*i);
            Cargo cargo = new Cargo();
            cargo.setCargoId(100042L + i);
            cargo.setBossId(100001L);
            cargo.setCargoName("货物测试名"+i);
            cargo.setCargoWeight(weight);

            LocalDate localDate1 = LocalDate.of(2023, 12, 20);
            ZonedDateTime zonedDateTime1 = localDate1.atStartOfDay(ZoneOffset.UTC);
            Date beginDate = Date.from(zonedDateTime1.toInstant());

            LocalDate localDate2 = LocalDate.of(2023, 12, 30);
            ZonedDateTime zonedDateTime2 = localDate2.atStartOfDay(ZoneOffset.UTC);
            Date endDate = Date.from(zonedDateTime2.toInstant());

            cargo.setBeginTime(beginDate);
            cargo.setEndTime(endDate);
            cargo.setBeginLocation(beginLocation.toCoordinateString());
            cargo.setEndLocation(endLocation.toCoordinateString());
            cargo.setDistance(distance);
            cargo.setValue(value);
            cargo.setStock(stock);
            cargo.setInfo("货物备注信息" + i*10);

            cargoDao.insert(cargo);
        }

    }

    @Test
    public void subStockTest() {
        SubCargoReq req = new SubCargoReq();
        req.setCargoId(100001L);
        BigDecimal bigDecimal = new BigDecimal(8000L);
        req.setSubStock(bigDecimal);

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

    @Test
    public void queryById() {
        Cargo cargo = cargoDao.queryById(1731678346183606272L);
        logger.info(cargo.toString());
    }
}
