package com.yang.freight.domain.driver.model.req;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/19
 * @Copyright：
 */
public class DriverUpdatePasswordReq {

    private long driverId;

    private byte[] hashedPassword;

    private byte[] salt;

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
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
}
