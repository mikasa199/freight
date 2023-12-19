package com.yang.freight.domain.cargo.model.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 扣减货物库存
 * @author：杨超
 * @date: 2023/11/19
 * @Copyright：
 */
@Data
public class SubCargoReq {

    private long cargoId;

    private BigDecimal subStock;

}
