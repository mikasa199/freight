package com.yang.freight.infrastructure.po;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 订单信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
@Data
public class Order {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 订单id
     */
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
    private BigDecimal cargoWeight;

    /**
     * 订单价格
     */
    private BigDecimal value;

    /**
     * 订单状态 -1:库存未扣减 0:订单已接收 1:雇主已付款 2:司机正在运货 3:货物已送达目的地 4:司机获取订单的佣金 5:订单完成 6:订单取消
     */
    private int state;

    /**
     * 订单备注信息
     */
    private String info;

    /**
     * 订单创建时间
     */
    //private Date createTime;
    private LocalDateTime createTime;


    /**
     * 订单更新时间
     */
    private LocalDateTime updateTime;


}
