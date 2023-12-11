package com.yang.freight.domain.order.model.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 提交订单请求类
 * @author：杨超
 * @date: 2023/11/29
 * @Copyright：
 */
@Data
public class SubmitOrderReq {

    private long cargoId;

    private long driverId;

    private BigDecimal subStock;

}
