package com.yang.freight.infrastructure.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 货物信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
@Data
public class Cargo {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 货物 id
     */
    private Long cargoId;

    /**
     * 发布该货物信息的 老板id
     */
    private Long bossId;

    /**
     * 货物名称
     */
    private String cargoName;

    /**
     * 货物总重量
     */
    private BigDecimal cargoWeight;

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
     * 运费总价
     */
    private BigDecimal value;

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

}
