package com.yang.freight.domain.driver.model.req;

/**
 * @description: 资格认证信息状态更新
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
public class AuthenticationStatusUpdateReq {

    private long driverId;

    private int beforeStatus;

    private int afterStatus;

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public int getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(int beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public int getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(int afterStatus) {
        this.afterStatus = afterStatus;
    }
}
