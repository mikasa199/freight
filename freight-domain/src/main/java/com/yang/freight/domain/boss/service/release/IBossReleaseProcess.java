package com.yang.freight.domain.boss.service.release;

import com.yang.freight.domain.boss.model.req.BossUpdatePasswordReq;
import com.yang.freight.domain.boss.model.req.InitBossReq;
import com.yang.freight.domain.boss.model.req.ReleaseCargoInfoReq;
import com.yang.freight.domain.boss.model.req.UpdateBossReq;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public interface IBossReleaseProcess {


    /**
     * 创建 Boss
     * @param req
     */
    void createBoss(InitBossReq req);

    /**
     * 更新 Boss信息
     * @param req
     */
    void updateBoss(UpdateBossReq req);

    /**
     * 更新密码
     * @param req
     */
    void updatePassword(BossUpdatePasswordReq req);


    /**
     * 发布货物信息
     * @param req 发布获取请求请求类
     */
    void releaseCargoInfo(ReleaseCargoInfoReq req);



}
