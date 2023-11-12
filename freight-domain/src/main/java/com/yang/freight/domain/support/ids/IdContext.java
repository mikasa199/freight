package com.yang.freight.domain.support.ids;


import com.yang.freight.common.Constants;
import com.yang.freight.domain.support.ids.policy.RandomNumeric;
import com.yang.freight.domain.support.ids.policy.ShortCode;
import com.yang.freight.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/3
 * @Copyright：
 */
@Configuration
public class IdContext {
    /**
     * 创建 ID 生成策略对象，属于策略设计模式的使用方式
     *
     * @param snowFlake 雪花算法，长码，大量 用于生产单号
     * @param shortCode 日期算法，短码，少量，全局唯一需要自己保证，用于生成活动编号
     * @param randomNumeric 随机算法，短码，大量，全局唯一需要自己保证，用于生成策略ID
     * @return IIdGenerator 实现类
     */
    @Bean
    public Map<Constants.Ids,IIdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric randomNumeric) {
        Map<Constants.Ids,IIdGenerator> idGeneratorMap = new HashMap<>();
        idGeneratorMap.put(Constants.Ids.SnowFlake,snowFlake);
        idGeneratorMap.put(Constants.Ids.ShortCode,shortCode);
        idGeneratorMap.put(Constants.Ids.RandomNumeric,randomNumeric);
        return idGeneratorMap;
    }
}
