package com.yang.freight.domain.boss.model.req;

import lombok.Data;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/7
 * @Copyright：
 */
@Data
public class UpdatePasswordReq {

    private long bossId;

    /**
     * 旧密码
     */
    private String beforePassword;

    /**
     * 新密码
     */
    private String afterPassword;

}
