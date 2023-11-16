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

    /**
     * 新增货物信息
     * @param cargo
     */
    void insert(Cargo cargo);

    /**
     * 根据货物id，扣减货物库存（重量）
     * @param cargoId
     * @param subStock
     * @return
     */
    int subStock(long cargoId,int subStock);

}
