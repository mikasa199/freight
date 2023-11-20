package com.yang.freight.domain.driver.repository;

import com.yang.freight.domain.driver.model.vo.DriverVO;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public interface IDriverRepository {
    /**
     * 新增司机
     * @param driverVO
     */
    public void addDriver(DriverVO driverVO);

}
