package com.yang.freight.infrastructure.dao;

import com.yang.freight.domain.order.model.req.StateTransferReq;
import com.yang.freight.infrastructure.po.Order;
import org.apache.ibatis.annotations.Mapper;

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
}
