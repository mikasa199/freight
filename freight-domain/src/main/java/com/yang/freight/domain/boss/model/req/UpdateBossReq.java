package com.yang.freight.domain.boss.model.req;

import lombok.Data;

/**
 * @description: 信息更新请求类
 * @author：杨超
 * @date: 2023/11/21
 * @Copyright：
 */
@Data
public class UpdateBossReq {

    private Long bossId;

    private String bossName;

    private String phone;

}
