package com.yang.infrastruture.po;

import java.util.Arrays;
import java.util.Date;

/**
 * @description: 老板信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
public class Boss {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 老板id
     */
    private Long bossId;

    /**
     * 老板姓名
     */
    private String bossName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 经过hash变换的密码
     */
    private byte[] hashedPassword;
    /**
     * 加密盐
     */
    private byte[] salt;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBossId() {
        return bossId;
    }

    public void setBossId(Long bossId) {
        this.bossId = bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
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
        return "Boss{" +
                "id=" + id +
                ", bossId=" + bossId +
                ", bossName='" + bossName + '\'' +
                ", phone='" + phone + '\'' +
                ", hashedPassword=" + Arrays.toString(hashedPassword) +
                ", salt=" + Arrays.toString(salt) +
                ", registerDate=" + registerDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
