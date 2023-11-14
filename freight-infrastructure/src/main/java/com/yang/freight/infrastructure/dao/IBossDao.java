package com.yang.freight.infrastructure.dao;

import com.yang.freight.infrastructure.po.Boss;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/12
 * @Copyright：
 */
@Mapper
public interface IBossDao {

    /**
     * 插入老板信息
     * @param boss
     */
    void insert(Boss boss);

    /**
     * 根据bossId更新 boos信息
     * @param boss
     * @return
     */
    int updateBoos(Boss boss);

    /**
     * 根据boosId删除信息
     * @param boosId
     * @return
     */
    int deleteById(long boosId);

    Boss queryById(long bossId);
}
