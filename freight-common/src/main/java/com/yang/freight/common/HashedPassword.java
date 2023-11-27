package com.yang.freight.common;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/17
 * @Copyright：
 */
public class HashedPassword {

    private byte[] hashedPassword;
    private byte[] salt;

    public HashedPassword() {
    }

    public HashedPassword(byte[] hashedPassword, byte[] salt) {
        this.hashedPassword = hashedPassword;
        this.salt = salt;
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
