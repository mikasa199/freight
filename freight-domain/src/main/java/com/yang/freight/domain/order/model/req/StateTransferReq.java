package com.yang.freight.domain.order.model.req;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public class StateTransferReq {

    private long orderId;

    private int beforeState;

    private int afterState;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getBeforeState() {
        return beforeState;
    }

    public void setBeforeState(int beforeState) {
        this.beforeState = beforeState;
    }

    public int getAfterState() {
        return afterState;
    }

    public void setAfterState(int afterState) {
        this.afterState = afterState;
    }
}
