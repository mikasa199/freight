package com.yang.freight.domain.cargo.model.req;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
@Data
public class ReleaseCargoInfoReq {

    private Long bossId;

    private String cargoName;

    private BigDecimal cargoWeight;

    private String beginLocation;

    private String endLocation;

    private BigDecimal value;

    private Date beginTime;

    private Date endTime;

    private String info;

}
