package com.yang.freight.domain.boss.model.req;

/**
 * @description: boss 初试化请求类
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
public class InitBossReq {

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

    @Override
    public String toString() {
        return "InitBossReq{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
