package com.yang.freight.domain.boss.model.req;

/**
 * @description: 信息更新请求类
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
public class UpdateBossReq {

    private Long bossId;

    private String bossName;

    private String phone;

    public Long getBossId() {
        return bossId;
    }

    public void setBossId(Long bossId) {
        this.bossId = bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UpdateBossReq{" +
                "bossId=" + bossId +
                ", bossName='" + bossName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
