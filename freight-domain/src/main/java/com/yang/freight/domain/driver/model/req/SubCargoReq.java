package com.yang.freight.domain.driver.model.req;

/**
 * @description: 扣减货物库存
 * @author：杨超
 * @date: 2023/11/19
 * @Copyright：
 */
public class SubCargoReq {

    private long cargoId;

    private long subStock;

    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
    }

    public long getSubStock() {
        return subStock;
    }

    public void setSubStock(long subStock) {
        this.subStock = subStock;
    }
}
