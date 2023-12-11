package com.yang.freight.domain.order.service.impl;

import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.cargo.model.vo.CargoVO;
import com.yang.freight.domain.cargo.service.ICargoService;
import com.yang.freight.domain.order.model.req.SubmitOrderReq;
import com.yang.freight.domain.order.model.res.OrderRes;
import com.yang.freight.domain.order.model.vo.OrderVO;
import com.yang.freight.domain.order.repository.IOrderRepository;
import com.yang.freight.domain.order.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderRepository orderRepository;

    @Resource
    private ICargoService cargoService;

    @Override
    public boolean submitOrder(SubmitOrderReq req) {


        //1. 生成订单并设置订单状态
        OrderVO order = orderRepository.createOrder(req);

        //2. 扣减库存
        boolean b = orderRepository.subStock(req, order.getOrderId());

        return b;
    }

    @Override
    public Return<OrderRes> queryByOrderId(long orderId) {
        OrderVO orderVO = orderRepository.queryListByOrderId(orderId);
        if (null == orderVO) {
            return Return.error("没有查询到对应的订单信息");
        }
        CargoVO cargoVO = cargoService.queryById(orderVO.getCargoId());
        OrderRes orderRes = new OrderRes();
        BeanUtils.copyProperties(cargoVO,orderRes);
        BeanUtils.copyProperties(orderVO,orderRes);
        return Return.success(orderRes);
    }

    @Override
    public Return<Page<OrderRes>> queryByCargoId(Page<OrderRes> page,long cargoId) {
        Page<OrderRes> orderResPage = orderRepository.queryListByCargoId(page, cargoId);
        if (null == orderResPage) {
            return Return.error("没有查询到对应的订单信息");
        }
        return Return.success(orderResPage);
    }

    @Override
    public Return<Page<OrderRes>> queryByDriverId(Page<OrderRes> page,long driverId) {
        Page<OrderRes> orderResPage = orderRepository.queryListByDriverId(page, driverId);
        if (null == orderResPage) {
            return Return.error("没有查询到对应的订单信息");
        }
        return Return.success(orderResPage);
    }
}
