package com.yang.freight.domain.order.model.vo;

import lombok.Data;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/29
 * @Copyright：
 */
@Data
public class OrderVO {

    private Long orderId;
    /**
     * 货物id
     */
    private Long cargoId;

    /**
     * 老板id
     */
    private Long bossId;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 订单货物重量
     */
    private Long cargoWeight;

    /**
     * 订单价格
     */
    private Long value;

    /**
     * 订单状态 -1:库存未扣减 0:已接单 1:正在运输 2:运输结束 3:确认到货 4:已付款
     */
    private Integer state;

}
