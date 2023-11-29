package com.yang.freight.domain.driver.model.req;

/**
 * @description: 提交订单请求类
 * @author：杨超
 * @date: 2023/11/29
 * @Copyright：
 */
public class SubmitOrderReq {

    private long cargoId;

    private long driverId;

    private long subStock;

    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getSubStock() {
        return subStock;
    }

    public void setSubStock(long subStock) {
        this.subStock = subStock;
    }

    @Override
    public String toString() {
        return "SubmitOrderReq{" +
                "cargoId=" + cargoId +
                ", driverId=" + driverId +
                ", subStock=" + subStock +
                '}';
    }
}
