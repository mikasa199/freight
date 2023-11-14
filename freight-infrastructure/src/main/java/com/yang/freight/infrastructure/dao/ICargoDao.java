package com.yang.freight.infrastructure.dao;

import com.yang.freight.infrastructure.po.Cargo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/12
 * @Copyright：
 */
@Mapper
public interface ICargoDao {

    void insert(Cargo cargo);

}
