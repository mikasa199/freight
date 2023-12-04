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

    protected List<T> records;

    protected long total;

    protected long size;

    protected long current;

}
