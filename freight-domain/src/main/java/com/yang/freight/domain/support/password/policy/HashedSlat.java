package com.yang.freight.domain.support.password.policy;

import com.yang.freight.common.HashedPassword;
import com.yang.freight.domain.support.password.IEncryption;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @description: 加盐哈希 加密
 * @author：杨超
 * @date: 2023/11/19
 * @Copyright：
 */
@Component
public class HashedSlat implements IEncryption {

    @Override
    public HashedPassword encryptPassword(String password) throws NoSuchAlgorithmException {
        HashedPassword hashedPassword = new HashedPassword();
        byte[] salt = generateSalt(16);
        byte[] password1 = hashPassword(password, salt);
        hashedPassword.setHashedPassword(password1);
        hashedPassword.setSalt(salt);
        return hashedPassword;
    }

    @Override
    public boolean verifyPassword(String password, HashedPassword hashedPassword) throws NoSuchAlgorithmException {

        byte[] hashToVerify = hashPassword(password, hashedPassword.getSalt());

        return Arrays.equals(hashToVerify, hashedPassword.getHashedPassword());
    }

    private byte[] generateSalt(int length) {
        byte[] salt = new byte[length];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    private byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        byte[] passwordBytes = password.getBytes();
        byte[] saltedPassword = new byte[passwordBytes.length + salt.length];
        System.arraycopy(passwordBytes,0,saltedPassword,0,passwordBytes.length);
        System.arraycopy(salt,0,saltedPassword,passwordBytes.length,salt.length);

        // 执行哈希运算
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = digest.digest(saltedPassword);

        return hashedPassword;
    }
}
