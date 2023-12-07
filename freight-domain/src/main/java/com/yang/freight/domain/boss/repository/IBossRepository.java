package com.yang.freight.domain.boss.repository;

import com.yang.freight.domain.boss.model.req.InitBossReq;
import com.yang.freight.domain.boss.model.vo.BossVO;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.order.model.vo.OrderVO;

/**
 * @description: 老板仓储服务
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
public interface IBossRepository {

    /**
     *新增老板
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

    /**
     * 新增货物信息
     * @param cargoVO
     * @return
     */
    boolean addCargo(CargoVO cargoVO);
}
