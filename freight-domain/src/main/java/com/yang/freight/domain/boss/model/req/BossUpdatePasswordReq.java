package com.yang.freight.domain.boss.model.req;

/**
 * @description: 更新密码请求类
 * @author：杨超
 * @date: 2023/11/19
 * @Copyright：
 */
public class BossUpdatePasswordReq {
    private long bossId;

    private byte[] hashedPassword;

    private byte[] salt;

    public long getBossId() {
        return bossId;
    }

    public void setBossId(long bossId) {
        this.bossId = bossId;
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
