package com.yang.freight.domain.driver.model.vo;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@Data
public class DriverVO {

    private Long driverId;
    private String driverName;
    private String phone;
    private byte[] hashedPassword;
    private byte[] salt;

}
