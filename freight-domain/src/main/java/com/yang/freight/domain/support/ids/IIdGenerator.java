package com.yang.freight.domain.support.ids;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/3
 * @Copyright：
 */
public interface IIdGenerator {
    /**
     * 获取ID
     * 1. 雪花算法，用于生成订单
     * 2. 日期算法，用于生成活动编号类，特性是生成数字串较短，但是指定时间内不能生成太多
     * 3. 随机算法，用于生成策略ID
     *
     * @return
     */
    long nextId();
}
