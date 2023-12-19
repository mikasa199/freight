package com.yang.freight.infrastructure.po;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * @description: 司机信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
@Data
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
    private LocalDateTime registerDate;

    /**
     * 更新日期
     */
    private LocalDateTime updateDate;

}
