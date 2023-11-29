package com.yang.freight.common;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页查询类
 * @author：杨超
 * @date: 2023/11/27
 * @Copyright：
 */
public class Page<T> implements Serializable {

    protected List<T> records;

    protected long total;

    protected long size;

    protected long current;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Page{" +
                "records=" + records +
                ", total=" + total +
                ", size=" + size +
                ", current=" + current +
                '}';
    }
}
