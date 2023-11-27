package com.yang.freight.domain.boss.model.req;

import java.util.Date;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
public class ReleaseCargoInfoReq {

    private Long bossId;

    private String cargoName;

    private Long cargoWeight;

    private String beginLocation;

    private String endLocation;

    private Long value;

    private Date beginTime;

    private Date endTime;

    private String info;

    public Long getBossId() {
        return bossId;
    }

    public void setBossId(Long bossId) {
        this.bossId = bossId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public Long getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Long cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public String getBeginLocation() {
        return beginLocation;
    }

    public void setBeginLocation(String beginLocation) {
        this.beginLocation = beginLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ReleaseCargoInfoReq{" +
                "bossId=" + bossId +
                ", cargoName='" + cargoName + '\'' +
                ", cargoWeight=" + cargoWeight +
                ", beginLocation='" + beginLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", value=" + value +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", info='" + info + '\'' +
                '}';
    }
}
