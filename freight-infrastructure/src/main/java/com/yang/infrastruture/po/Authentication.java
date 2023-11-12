package com.yang.infrastruture.po;

import java.util.Date;

/**
 * @description: 司机认证信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(Long authenticationId) {
        this.authenticationId = authenticationId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public int getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(int authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Date getIdCardValidFrom() {
        return idCardValidFrom;
    }

    public void setIdCardValidFrom(Date idCardValidFrom) {
        this.idCardValidFrom = idCardValidFrom;
    }

    public Date getIdCardValidTo() {
        return idCardValidTo;
    }

    public void setIdCardValidTo(Date idCardValidTo) {
        this.idCardValidTo = idCardValidTo;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Date getDriverLicenseValidTo() {
        return driverLicenseValidTo;
    }

    public void setDriverLicenseValidTo(Date driverLicenseValidTo) {
        this.driverLicenseValidTo = driverLicenseValidTo;
    }

    public String getQualificationCertificate() {
        return qualificationCertificate;
    }

    public void setQualificationCertificate(String qualificationCertificate) {
        this.qualificationCertificate = qualificationCertificate;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "id=" + id +
                ", authenticationId=" + authenticationId +
                ", driverId=" + driverId +
                ", authenticationStatus=" + authenticationStatus +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", idCardValidFrom=" + idCardValidFrom +
                ", idCardValidTo=" + idCardValidTo +
                ", driverLicense='" + driverLicense + '\'' +
                ", driverLicenseValidTo=" + driverLicenseValidTo +
                ", qualificationCertificate='" + qualificationCertificate + '\'' +
                '}';
    }
}
