package com.yang.freight.test.dao;

import com.yang.freight.infrastructure.dao.IOrderDao;
import com.yang.freight.infrastructure.po.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

    private Logger logger = LoggerFactory.getLogger(OrderDaoTest.class);

    @Resource
    private IOrderDao orderDao;

//    void insert(Order order);

//    int updateState(StateTransferReq req);

    @Test
    public void insertTest() {
        Order order = new Order();
        order.setOrderId(100001L);
        order.setCargoId(100001L);
        order.setBossId(100001L);
        order.setDriverId(100001L);
        BigDecimal big1 = new BigDecimal(8000L);

        order.setCargoWeight(big1);
        order.setValue(big1);
        order.setState(0);
        order.setInfo("订单备注信息");
        orderDao.insert(order);
    }
}
