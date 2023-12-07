package com.yang.freight.domain.boss.model.req;

import lombok.Data;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/7
 * @Copyright：
 */
@Data
public class UpdateNameReq {

    private long bossId;

    private String bossName;
}
