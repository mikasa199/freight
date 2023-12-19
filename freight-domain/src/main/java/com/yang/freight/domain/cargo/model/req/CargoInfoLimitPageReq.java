package com.yang.freight.domain.cargo.model.req;

import com.yang.freight.common.PageRequest;
import lombok.Data;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@Data
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

}
