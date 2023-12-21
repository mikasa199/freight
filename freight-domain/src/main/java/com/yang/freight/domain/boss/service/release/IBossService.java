package com.yang.freight.domain.boss.service.release;

import com.yang.freight.common.Return;
import com.yang.freight.domain.boss.model.req.*;
import com.yang.freight.domain.boss.model.vo.BossVO;

import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public interface IBossService {


    /**
     * 创建 Boss
     * @param req
     * @return
     */
    BossVO createBoss(InitBossReq req) throws NoSuchAlgorithmException;

    /**
     * 更新 Boss信息
     * @param req
     */
    void updateBoss(UpdateBossReq req);

    /**
     * 更新密码
     * @param req
     * @return
     */
    boolean updatePassword(UpdatePasswordReq req);

    /**
     * 更新名字
     * @param req
     * @return
     */
    boolean updateName(UpdateNameReq req);

    /**
     * 更新手机号
     * @param req
     * @return
     */
    boolean updatePhone(UpdatePhoneReq req);


//    /**
//     * 发布货物信息
//     * @param req 发布获取请求请求类
//     * @return
//     */
//    boolean releaseCargoInfo(ReleaseCargoInfoReq req);

    /**
     * 根据手机号查询老板信息
     * @param phone
     * @return
     */
    BossVO queryByPhone(String phone);

    /**
     * 检查并初始化
     * @param phone
     * @return
     */
    BossVO checkAndInit(String phone);


    /**
     * 老板登录
     * @param req
     * @return
     */
    Return<BossVO> bossLogon(InitBossReq req);


    /**
     * 根据老板id获取对应的信息
     * @param bossId
     * @return
     */
    Return<BossVO> queryById(long bossId);

}
