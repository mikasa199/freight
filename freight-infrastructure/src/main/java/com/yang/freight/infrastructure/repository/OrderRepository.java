package com.yang.freight.infrastructure.repository;

import com.yang.freight.common.Constants;
import com.yang.freight.common.Page;
import com.yang.freight.domain.cargo.model.req.SubCargoReq;
import com.yang.freight.domain.order.model.req.SubmitOrderReq;
import com.yang.freight.domain.order.model.req.StateTransferReq;
import com.yang.freight.domain.order.model.res.OrderRes;
import com.yang.freight.domain.order.model.vo.OrderVO;
import com.yang.freight.domain.order.repository.IOrderRepository;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.domain.support.location.LocationUtils;
import com.yang.freight.infrastructure.dao.ICargoDao;
import com.yang.freight.infrastructure.dao.IOrderDao;
import com.yang.freight.infrastructure.po.Cargo;
import com.yang.freight.infrastructure.po.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
@Repository
public class OrderRepository implements IOrderRepository {

    @Resource
    private ICargoDao cargoDao;

    @Resource
    private IOrderDao orderDao;

    @Resource
    private Map<Constants.Ids,IIdGenerator> idGeneratorMap;

    private Logger logger = LoggerFactory.getLogger(OrderRepository.class);



    @Override
    public OrderVO createOrder(SubmitOrderReq req) {

        Cargo cargo = cargoDao.queryById(req.getCargoId());
        logger.info(req.toString());
        logger.info(cargo.toString());

        Order order = new Order();
        IIdGenerator iIdGenerator = idGeneratorMap.get(Constants.Ids.ShortCode);
        order.setOrderId(iIdGenerator.nextId());
        order.setDriverId(req.getDriverId());
        order.setCargoId(req.getCargoId());
        order.setBossId(cargo.getBossId());
        order.setCargoWeight(req.getSubStock());
        // 货物重量 * 货物单价
        order.setValue(req.getSubStock().multiply(cargo.getValue()));
        order.setState(-1);

        orderDao.insert(order);

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return orderVO;
    }

    @Override
    public boolean subStock(SubmitOrderReq req, long orderId) {
        Cargo cargo = cargoDao.queryById(req.getCargoId());

        int i1 = cargo.getStock().compareTo(req.getSubStock());
        if (i1 < 0) {
            logger.info("货物库存不足，无法扣减库存。");
            return false;
        }else {
            SubCargoReq subCargoReq = new SubCargoReq();
            subCargoReq.setCargoId(req.getCargoId());
            subCargoReq.setSubStock(req.getSubStock());
            int i = cargoDao.subStock(subCargoReq);
            if (i == 0) {
                return false;
            }else {
                //更新订单状态
                StateTransferReq stateTransferReq = new StateTransferReq();
                stateTransferReq.setOrderId(orderId);
                stateTransferReq.setBeforeState(-1);
                stateTransferReq.setAfterState(0);
                orderDao.updateState(stateTransferReq);
                return true;
            }
        }
    }

    @Override
    public OrderVO queryListByOrderId(long orderId) {
        Order order = orderDao.queryByOrderId(orderId);
        if (null == order) {
            logger.info("未查询到对应的订单信息 orderId:{}",orderId);
            return null;
        }
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return orderVO;
    }

    @Override
    public Page<OrderRes> queryListByCargoId(Page<OrderRes> page, long cargoId) {

        long count =  countByCargoId(cargoId);

        long current = page.getCurrent();
        long size = page.getSize();

        if (count % size == 0) {
            page.setTotal(count / size);
        }else {
            page.setTotal(count / size + 1);
        }
        List<OrderRes> orderList = orderDao.queryByCargoId((current - 1) * size, size, cargoId);
        if (orderList.size() == 0) {
            logger.info("没有查询到对应货物的订单 cargoId:{}",cargoId);
            return null;
        }
        page.setRecords(orderList);
        return page;
    }

    @Override
    public Page<OrderRes> queryListByDriverId(Page<OrderRes> page, long driverId) {

        long count =  countByDriverId(driverId);

        long current = page.getCurrent();
        long size = page.getSize();

        if (count % size == 0) {
            page.setTotal(count / size);
        }else {
            page.setTotal(count / size + 1);
        }

        List<OrderRes> orderList = orderDao.queryByDriverId((current - 1) * size, size, driverId);

        if (orderList.size() == 0) {
            logger.info("没有查询到对应司机的订单 driverId:{}",driverId);
            return page;
        }
        for(OrderRes res : orderList) {
            logger.info(res.getBeginLocation()+res.getEndLocation());
            res.setBeginLocation(LocationUtils.coordinateToAddress(res.getBeginLocation()));
            res.setEndLocation(LocationUtils.coordinateToAddress(res.getEndLocation()));
            logger.info(res.getBeginLocation()+res.getEndLocation());
        }
        page.setRecords(orderList);
        return page;
    }

    @Override
    public long countByDriverId(long driverId) {
        return orderDao.countByDriverId(driverId);
    }

    @Override
    public long countByCargoId(long cargoId) {
        return orderDao.countByCargoId(cargoId);
    }
}
