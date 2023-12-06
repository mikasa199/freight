package com.yang.freight.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页查询类
 * @author：杨超
 * @date: 2023/11/27
 * @Copyright：
 */
@Data
public class Page<T> implements Serializable {

    /**
     * 结果
     */
    protected List<T> records;

    /**
     * 总页数
     */
    protected long total;

    /**
     * 页内大小
     */
    protected long size;

    /**
     * 当前页数
     */
    protected long current;

}
