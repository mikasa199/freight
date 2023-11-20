package com.yang.freight.domain.support.password;

import com.yang.freight.common.HashedPassword;

import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/19
 * @Copyright：
 */
public interface IEncryption {

    /**
     * 将密码加密
     * @param password
     * @return
     */
    HashedPassword encryptPassword(String password) throws NoSuchAlgorithmException;

    /**
     * 验证密码是否正确
     * @param password
     * @param hashedPassword
     * @return
     */
    boolean verifyPassword(String password, HashedPassword hashedPassword) throws NoSuchAlgorithmException;
}
