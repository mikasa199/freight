package com.yang.infrastructure.dao;

import com.yang.infrastructure.po.Authentication;
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
    void insert(Authentication authentication);

    Authentication queryById(Long driverId);


}
