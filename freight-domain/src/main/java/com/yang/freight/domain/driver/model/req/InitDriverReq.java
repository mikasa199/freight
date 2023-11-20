package com.yang.freight.domain.driver.model.req;

/**
 * @description: 初始化司机用户请求
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public class InitDriverReq {


    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
