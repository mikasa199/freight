package com.yang.freight.domain.driver.model.vo;

import java.util.Arrays;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public class DriverVO {

    private Long driverId;
    private String driverName;
    private String phone;
    private byte[] hashedPassword;
    private byte[] salt;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "DriverVO{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", phone='" + phone + '\'' +
                ", hashedPassword=" + Arrays.toString(hashedPassword) +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}
