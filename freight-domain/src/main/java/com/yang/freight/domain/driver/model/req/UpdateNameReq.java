package com.yang.freight.domain.driver.model.req;

import lombok.Data;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/7
 * @Copyright：
 */
@Data
public class UpdateNameReq {

    private long driverId;

    private String driverName;
}
