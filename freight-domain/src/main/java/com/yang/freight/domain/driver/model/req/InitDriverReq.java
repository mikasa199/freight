package com.yang.freight.domain.driver.model.req;

import lombok.Data;

/**
 * @description: 初始化司机用户请求
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@Data
public class InitDriverReq {

    private String phone;
    private String driverName;
    private String password;

}
