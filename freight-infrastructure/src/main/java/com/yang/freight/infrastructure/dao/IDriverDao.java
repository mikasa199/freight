package com.yang.freight.infrastructure.dao;

import com.yang.freight.infrastructure.po.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    int insert(Driver driver);

    /**
     * 更新密码
     * @param driver
     * @return
     */
    int updateHashedPassword(Driver driver);

    /**
     * 更新用户信息（手机号和用户名）
     * @param driver
     * @return
     */
    int updateDriver(Driver driver);

    /**
     * 更新用户名
     * @param driver
     * @return
     */
    int updateDriverName(Driver driver);

    /**
     * 更新手机号
     * @param driver
     * @return
     */
    int updatePhone(Driver driver);

    /**
     * 根据id查询对应的司机
     * @param driverId
     * @return
     */
    Driver queryById(long driverId);

    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    Driver queryByPhone(String phone);
}
