package com.yang.freight.infrastructure.po;

import java.util.Date;

/**
 * @description: 货物信息
 * @author：杨超
 * @date: 2023/11/11
 * @Copyright：
 */
public class Cargo {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 货物 id
     */
    private Long cargoId;

    /**
     * 发布该货物信息的 老板id
     */
    private Long bossId;

    /**
     * 货物名称
     */
    private String cargoName;

    /**
     * 货物总重量
     */
    private  Long cargoWeight;

    /**
     * 货物运输起点
     */
    private String beginLocation;

    /**
     * 货物运输终点
     */
    private String endLocation;

    /**
     * 运费总价
     */
    private Long value;

    /**
     * 货物运输开始时间
     */
    private Date beginTime;

    /**
     * 货物运输结束时间
     */
    private Date endTime;

    /**
     * 剩余货物库存
     */
    private Long stock;

    /**
     * 货物备注信息
     */
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", cargoId=" + cargoId +
                ", bossId=" + bossId +
                ", cargoName='" + cargoName + '\'' +
                ", cargoWeight=" + cargoWeight +
                ", beginLocation='" + beginLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", value=" + value +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", stock=" + stock +
                ", info='" + info + '\'' +
                '}';
    }
}
