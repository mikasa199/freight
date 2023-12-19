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

    /**
     * 司机id
     */
    private long driverId;

    /**
     * 历史密码
     */
    private String beforePassword;

    /**
     * 新密码
     */
    private String afterPassword;

}
