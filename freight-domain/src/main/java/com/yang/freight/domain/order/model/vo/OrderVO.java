package com.yang.freight.domain.order.model.vo;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/29
 * @Copyright：
 */
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
    private int state;

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

    @Override
    public String toString() {
        return "OrderVO{" +
                "orderId=" + orderId +
                ", cargoId=" + cargoId +
                ", bossId=" + bossId +
                ", driverId=" + driverId +
                ", cargoWeight=" + cargoWeight +
                ", value=" + value +
                ", state=" + state +
                '}';
    }
}
