package com.yang.freight.infrastructure.dao;

import com.yang.freight.domain.driver.model.req.AuthenticationStatusUpdateReq;
import com.yang.freight.infrastructure.po.Authentication;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/12
 * @Copyright：
 */
@Mapper
public interface IAuthenticationDao {
    /**
     * 向数据库插入资格验证信息
     * @param authentication
     */
    int insert(Authentication authentication);

    /**
     * 根据司机id查询信息
     * @param driverId
     * @return
     */
    Authentication queryById(Long driverId);

    /**
     * 更新对应司机id的信息审核状态
     * @return
     */
    int updateStatus(Authentication authentication);


}
