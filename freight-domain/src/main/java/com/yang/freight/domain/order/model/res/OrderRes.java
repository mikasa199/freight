package com.yang.freight.domain.order.model.res;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
@Data
public class OrderRes {
    private Long orderId;
    /**
     * 货物id
     */
    private Long cargoId;
    /**
     * 货物名称
     */
    private String cargoName;
    /**
     * 货物运输起点
     */
    private String beginLocation;
    /**
     * 货物运输终点
     */
    private String endLocation;
    /**
     * 距离
     */
    private BigDecimal distance;
    /**
     * 货物运输开始时间
     */
    private Date beginTime;

    /**
     * 货物运输结束时间
     */
    private Date endTime;

    /**
     * 剩余货物库存
     */
    private BigDecimal stock;

    /**
     * 货物备注信息
     */
    private String info;
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

    private Date createdTime;

    private Date updatedTime;

    private String driverName;

    private String phone;
}
