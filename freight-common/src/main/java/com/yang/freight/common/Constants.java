package com.yang.freight.common;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
@SuppressWarnings({"all"})
public class Constants {

    public enum Method {
        BEGIN_TIME_UP(1,"以开始时间升序排序"),
        BEGIN_TIME_DOWN(-1,"以开始时间降序排序"),
        END_TIME_UP(2,"以结束时间升序排序"),
        END_TIME_DOWN(-2,"以结束时间降序排序"),
        VALUE_UP(3,"以单价升序排序"),
        VALUE_DOWN(-3,"以单价降序排序"),
        STOCK_UP(4,"以库存量升序排序"),
        STOCK_DOWN(-4,"以库存量降序排序")
        ;

        private int code;
        private String info;

        Method(int code, String info) {
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

    public enum Ids {
        /** 雪花算法*/
        SnowFlake,
        /** 日期算法*/
        ShortCode,
        /** 随机算法*/
        RandomNumeric;
    }

    public enum OrderState {
        //-1:库存未扣减 0:订单已接收 1:雇主已付款 2:司机正在运货 3:货物已送达目的地 4:司机获取订单的佣金 5:订单完成 6:订单取消
        SUBSTOCK(-1,"未扣减库存"),
        RECEIVE(0,"订单已接收"),
        PAY(1,"雇主已付款"),
        RUNNING(2,"司机正在运货"),
        ARRIVE(3,"货物已送达目的地"),
        GAIN(4,"司机获取订单的佣金"),
        END(5,"订单完成"),
        CANCEL(6,"订单取消");

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
