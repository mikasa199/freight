package com.yang.freight.domain.driver.model.req;

import com.yang.freight.common.PageRequest;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public class CargoInfoLimitPageReq extends PageRequest {
    /**
     * 货物id
     */
    private long cargoId;
    /**
     * 货物名称
     */
    private String cargoName;

    public CargoInfoLimitPageReq(int page, int rows) {
        super(page, rows);
    }

    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
}
