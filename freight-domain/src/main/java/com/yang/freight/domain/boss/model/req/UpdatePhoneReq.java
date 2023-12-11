package com.yang.freight.domain.boss.model.req;

import lombok.Data;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/8
 * @Copyright：
 */
@Data
public class UpdatePhoneReq {
    /**
     * 司机id
     */
    private long bossId;

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
