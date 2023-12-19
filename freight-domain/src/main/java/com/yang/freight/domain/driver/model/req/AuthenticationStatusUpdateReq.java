package com.yang.freight.domain.driver.model.req;

import lombok.Data;

/**
 * @description: 资格认证信息状态更新
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
@Data
public class AuthenticationStatusUpdateReq {

    private long driverId;

    private int beforeStatus;

    private int afterStatus;

}
