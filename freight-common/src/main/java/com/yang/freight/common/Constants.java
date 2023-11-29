package com.yang.freight.common;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
@SuppressWarnings({"all"})
public class Constants {

    public enum Ids {
        /** 雪花算法*/
        SnowFlake,
        /** 日期算法*/
        ShortCode,
        /** 随机算法*/
        RandomNumeric;
    }

    public enum OrderState {
        RECEIVE(0,"订单已接收"),
        PAY(1,"雇主已付款"),
        RUNNING(2,"司机正在运货"),
        ARRIVE(3,"货物已送达目的地"),
        GAIN(4,"司机获取订单的佣金"),
        END(5,"订单完成"),
        CANCEL(-1,"订单取消");

        private int code;
        private String info;

        OrderState(int code, String info) {
            this.code = code;
            this.info = info;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
