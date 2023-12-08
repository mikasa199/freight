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
     * 根据id查询信息
     * @param bossId
     * @return
     */
    BossVO queryById(long bossId);

    /**
     * 新增货物信息
     * @param cargoVO
     * @return
     */
    boolean addCargo(CargoVO cargoVO);

    /**
     * 更新姓名
     * @param bossVO
     * @return
     */
    boolean updateName(BossVO bossVO);

    /**
     * 更新密码
     * @param bossVO
     * @return
     */
    boolean updatePassword(BossVO bossVO);
}
