package com.yang.freight.infrastructure.po;

import java.util.Date;

/**
 * @description: 订单信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
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
    private Long cargoWeight;

    /**
     * 订单价格
     */
    private Long value;

    /**
     * 订单状态 0:已接单 1:正在运输 2:运输结束 3:确认到货 4:已付款,
     */
    private int state;

    /**
     * 订单备注信息
     */
    private String info;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    public Long getBossId() {
        return bossId;
    }

    public void setBossId(Long bossId) {
        this.bossId = bossId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Long cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", cargoId=" + cargoId +
                ", bossId=" + bossId +
                ", driverId=" + driverId +
                ", cargoWeight=" + cargoWeight +
                ", value=" + value +
                ", state=" + state +
                ", info='" + info + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
