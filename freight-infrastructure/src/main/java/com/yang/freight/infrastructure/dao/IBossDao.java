package com.yang.freight.infrastructure.dao;

import com.yang.freight.domain.boss.model.req.BossUpdatePasswordReq;
import com.yang.freight.infrastructure.po.Boss;
import org.apache.ibatis.annotations.Mapper;
import sun.security.util.Password;

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
     * 更新密码
     * @param req
     * @return
     */
    int updatePassword(BossUpdatePasswordReq req);

    /**
     * 根据boosId删除信息
     * @param boosId
     * @return
     */
    int deleteById(long boosId);

    /**
     * 根据id查询对应boss信息
     * @param bossId
     * @return
     */
    Boss queryById(long bossId);
}
