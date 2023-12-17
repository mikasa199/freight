package com.yang.freight.infrastructure.po;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 司机认证信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
@Data
public class Authentication {
    /**
     * 自增id
     */
    private Long id;

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
    private LocalDateTime idCardValidFrom;

    /**
     * 身份证有效期截至时间
     */
    private LocalDateTime idCardValidTo;

    /**
     * 驾驶证
     */
    private String driverLicense;

    /**
     * 驾驶证有效期截止日期
     */
    private LocalDateTime driverLicenseValidTo;

    /**
     * 从业资格证
     */
    private String qualificationCertificate;

}
