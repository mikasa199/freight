package com.yang.freight.infrastructure.dao;

import com.yang.freight.infrastructure.po.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/12
 * @Copyright：
 */
@Mapper
public interface IOrderDao {

    void insert(Order order);
}
