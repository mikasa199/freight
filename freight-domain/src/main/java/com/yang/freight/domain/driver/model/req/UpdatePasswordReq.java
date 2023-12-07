package com.yang.freight.domain.driver.model.req;

import lombok.Data;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/19
 * @Copyright：
 */
@Data
public class UpdatePasswordReq {

    private long driverId;

    private String beforePassword;

    private String afterPassword;

}
