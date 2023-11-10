package com.yang.freight.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/10
 * @Copyright：
 */
public class PasswordUtil {
    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return hashedPassword;
    }

    public static boolean verifyPassword(String password, byte[] storedHash, byte[] storedSalt) throws NoSuchAlgorithmException {
        byte[] calculatedHash = hashPassword(password, storedSalt);
        return Arrays.equals(calculatedHash, storedHash);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 用户注册时的流程
        String password = "userPassword";
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(password, salt);
        // 将 hashedPassword 和 salt 存储到数据库中

        // 用户登录验证的流程
        String loginPasswordAttempt = "userPassword";  // 用户输入的登录密码
        if (verifyPassword(loginPasswordAttempt, hashedPassword, salt)) {
            System.out.println("密码正确，登录成功！");
        } else {
            System.out.println("密码错误，登录失败！");
        }
    }
}
