package com.yang.freight.domain.driver.model.req;


import lombok.Data;

import java.util.Date;

/**
 * @description: 添加身份认证信息
 * @author：杨超
 * @date: 2023/12/6
 * @Copyright：
 */
@Data
public class AddAuthenticationReq {

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 司机姓名
     */
    private String driverName;

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
