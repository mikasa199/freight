package com.yang.infrastruture.po;

import java.util.Arrays;
import java.util.Date;

/**
 * @description: 司机信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
public class Driver {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 司机电话号码
     */
    private String phone;

    /**
     * hash变换后的密码
     */
    private byte[] hashedPassword;

    /**
     * 加密盐
     */
    private byte[] salt;

    /**
     * 注册日期
     */
    private Date registerDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", phone='" + phone + '\'' +
                ", hashedPassword=" + Arrays.toString(hashedPassword) +
                ", salt=" + Arrays.toString(salt) +
                ", registerDate=" + registerDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
