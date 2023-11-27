package com.yang.freight.domain.driver.model.aggregates;

import com.yang.freight.domain.driver.model.vo.CargoVO;

import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public class CargoInfoLimitPageRich {

    private long count;
    private List<CargoVO> cargoVOList;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<CargoVO> getCargoVOList() {
        return cargoVOList;
    }

    public void setCargoVOList(List<CargoVO> cargoVOList) {
        this.cargoVOList = cargoVOList;
    }
}
