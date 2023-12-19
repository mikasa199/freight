package com.yang.freight.domain.boss.model.vo;

import java.util.Arrays;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/1
 * @Copyright：
 */
public class BossVO {

    private Long bossId;

    private String bossName;

    private String phone;

    private byte[] hashedPassword;

    private byte[] salt;


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

    @Override
    public String toString() {
        return "BossVO{" +
                "bossId=" + bossId +
                ", bossName='" + bossName + '\'' +
                ", phone='" + phone + '\'' +
                ", hashedPassword=" + Arrays.toString(hashedPassword) +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}
