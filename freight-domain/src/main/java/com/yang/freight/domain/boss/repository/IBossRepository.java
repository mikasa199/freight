package com.yang.freight.domain.boss.repository;

import com.yang.freight.domain.boss.model.req.InitBossReq;
import com.yang.freight.domain.boss.model.vo.BossVO;

/**
 * @description: 老板仓储服务
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
public interface IBossRepository {

    /**
     *
     * @param bossVO
     * @return
     */
    boolean addBoss(BossVO bossVO);

    /**
     * 根据手机号查信息
     * @param phone
     * @return
     */
    BossVO queryByPhone(String phone);
}
