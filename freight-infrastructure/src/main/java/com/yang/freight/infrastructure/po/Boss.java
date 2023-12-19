package com.yang.freight.infrastructure.po;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * @description: 老板信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
@Data
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
    private LocalDateTime registerDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

}
