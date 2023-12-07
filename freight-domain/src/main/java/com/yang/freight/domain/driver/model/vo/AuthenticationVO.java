package com.yang.freight.domain.driver.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/6
 * @Copyright：
 */
@Data
public class AuthenticationVO {
    /**
     * 认证信息id
     */
    private Long authenticationId;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 认证信息状态 0: 未认证, 1: 已认证, 2: 认证失败',
     */
    private int authenticationStatus;

    /**
     * 身份证号码
     */
    private String idCardNumber;

    /**
     * 身份证有效期开始时间
     */
    private Date idCardValidFrom;

    /**
     * 身份证有效期截至时间
     */
    private Date idCardValidTo;

    /**
     * 驾驶证
     */
    private String driverLicense;

    /**
     * 驾驶证有效期截止日期
     */
    private Date driverLicenseValidTo;

    /**
     * 从业资格证
     */
    private String qualificationCertificate;
}
