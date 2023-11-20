package com.yang.freight.infrastructure.dao;

import com.yang.freight.domain.driver.model.req.DriverUpdatePasswordReq;
import com.yang.freight.infrastructure.po.Driver;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/12
 * @Copyright：
 */
@Mapper
public interface IDriverDao {
    /**
     * 新增司机信息
     * @param driver
     */
    void insert(Driver driver);

    /**
     * 更新密码
     * @param req
     * @return
     */
    int updateHashedPasswordInt(DriverUpdatePasswordReq req);

    /**
     * 根据id查询对应的司机
     * @param driverId
     * @return
     */
    Driver queryById(long driverId);
}
