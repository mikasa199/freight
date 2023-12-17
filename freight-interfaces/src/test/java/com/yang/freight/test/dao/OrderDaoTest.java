package com.yang.freight.test.dao;

import com.yang.freight.domain.order.model.res.OrderRes;
import com.yang.freight.domain.order.repository.IOrderRepository;
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
import java.util.List;

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

    private IOrderRepository orderRepository;

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

    @Test
    public void queryTest() {
        List<OrderRes> orderRes = orderDao.queryByCargoId(0, 10, 1733889063930462208L);
        List<OrderRes> orderRes1 = orderDao.queryByDriverId(0, 10, 1732419097892913152L);
        if (orderRes.size() != 0) {
            for (OrderRes res : orderRes) {
                logger.info(res.toString());
            }

        }
        if (orderRes1.size() != 0) {
            for (OrderRes res : orderRes1) {
                logger.info(res.toString());
            }
        }
        int i = orderDao.countByDriverId(1732419097892913152L);
        logger.info("size:{}",orderRes.size());
        logger.info("size:{},count:{}",orderRes1.size(), i);
    }

}
