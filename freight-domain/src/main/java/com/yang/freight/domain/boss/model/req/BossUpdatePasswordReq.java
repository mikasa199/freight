package com.yang.freight.domain.boss.model.req;

import lombok.Data;

/**
 * @description: 更新密码请求类
 * @author：杨超
 * @date: 2023/11/19
 * @Copyright：
 */
@Data
public class BossUpdatePasswordReq {
    private long bossId;

    private byte[] hashedPassword;

    private byte[] salt;
}
