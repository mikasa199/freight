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
     * @return
     */
    public Boolean addDriver(DriverVO driverVO);

    /**
     * 根据电话号码查询司机
     * @param phone
     * @return
     */
    public DriverVO queryByPhone(String phone);

}
