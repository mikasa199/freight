package com.yang.freight.domain.driver.model.req;

import lombok.Data;

/**
 * @description: 更新密码请求类
 * @author：杨超
 * @date: 2023/12/7
 * @Copyright：
 */
@Data
public class UpdatePhoneReq {

    /**
     * 司机id
     */
    private long driverId;

    /**
     * 旧的手机号
     */
    private String beforePhone;

    /**
     * 新的手机号
     */
    private String afterPhone;

    /**
     * 验证码
     */
    private String code;


}
