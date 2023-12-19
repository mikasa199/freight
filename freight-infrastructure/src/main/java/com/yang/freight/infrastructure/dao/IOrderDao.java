package com.yang.freight.infrastructure.dao;

import com.yang.freight.domain.order.model.req.StateTransferReq;
import com.yang.freight.domain.order.model.res.OrderRes;
import com.yang.freight.infrastructure.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/12
 * @Copyright：
 */
@Mapper
public interface IOrderDao {

    /**
     * 新增订单
     * @param order
     */
    void insert(Order order);

    /**
     * 更新订单状态
     *
     * @param req@return
     */
    int updateState(StateTransferReq req);

    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    Order queryByOrderId(@Param("orderId") long orderId);

    /**
     * 根据司机id获取订单id
     * @param driverId
     * @return
     */
    List<OrderRes> queryByDriverId(@Param("page") long page, @Param("pageSize") long pageSize, @Param("driverId") long driverId);

    /**
     * 根据货物id获取订单信息
     * @param cargoId
     * @return
     */
    List<OrderRes> queryByCargoId(@Param("page") long page, @Param("pageSize") long pageSize,@Param("cargoId") long cargoId);

    /**
     * 统计该司机id的订单数
     * @param driverId
     * @return
     */
    int countByDriverId(@Param("driverId") long driverId);

    /**
     * 统计对应货物id的订单数
     * @param cargoId
     * @return
     */
    int countByCargoId(@Param("cargoId") long cargoId);
}
