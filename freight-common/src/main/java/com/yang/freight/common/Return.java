package com.yang.freight.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/24
 * @Copyright：
 */
public class Return<T> {

    /**
     * 编码：1 成功 0和其他数字为失败
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 动态数据
     */
    private Map map = new HashMap();

    public static <T> Return<T> success(T object) {
        Return<T> r = new Return<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> Return<T> error(String msg) {
        Return<T> r = new Return<>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public Return<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
