package com.yang.freight.domain.order.repository;

import com.yang.freight.common.Page;
import com.yang.freight.domain.order.model.req.SubmitOrderReq;
import com.yang.freight.domain.order.model.res.OrderRes;
import com.yang.freight.domain.order.model.vo.OrderVO;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
public interface IOrderRepository {
    /**
     * 创建订单
     * @param req
     * @return
     */
    public OrderVO createOrder(SubmitOrderReq req);

    /**
     * 扣减库存
     * @param req
     * @param orderId
     * @return 是否扣减成功
     */
    public boolean subStock(SubmitOrderReq req, long orderId);

    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    OrderVO queryListByOrderId(long orderId);

    /**
     * 根据货物id查询订单信息
     * @param cargoId
     * @return
     */
    Page<OrderRes> queryListByCargoId(Page<OrderRes> page, long cargoId);

    /**
     * 根据司机id查询订单信息
     * @param driverId
     * @return
     */
    Page<OrderRes> queryListByDriverId(Page<OrderRes> page, long driverId);

    long countByDriverId(long driverId);

    long countByCargoId(long cargoId);

}
