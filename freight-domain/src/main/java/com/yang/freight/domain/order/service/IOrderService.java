package com.yang.freight.domain.order.service;

import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.order.model.req.SubmitOrderReq;
import com.yang.freight.domain.order.model.res.OrderRes;
import com.yang.freight.domain.order.model.vo.OrderVO;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
public interface IOrderService {

    /**
     * 提交扣减库存的订单
     * @param req
     * @return
     */
    boolean submitOrder(SubmitOrderReq req);

    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    Return<OrderRes> queryByOrderId(long orderId);

    /**
     * 根据货物id查询对应的订单信息
     * @param cargoId
     * @return
     */
    Return<Page<OrderRes>> queryByCargoId(Page<OrderRes> page,long cargoId);

    /**
     * 根据司机id查询对应的订单信息
     * @param driverId
     * @return
     */
    Return<Page<OrderRes>> queryByDriverId(Page<OrderRes> page,long driverId);
}
