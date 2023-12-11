package com.yang.freight.domain.cargo.service;

import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.cargo.model.req.ReleaseCargoInfoReq;
import com.yang.freight.domain.cargo.model.vo.CargoVO;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
public interface ICargoService {

    /**
     * 发布货物信息
     * @param req 发布获取请求请求类
     * @return
     */
    boolean releaseCargoInfo(ReleaseCargoInfoReq req);

    /**
     * 根据货物名和页数、页面大小 获取货物数据
     * @param page
     * @param cargoName
     * @return
     */
    Return<Page<CargoVO>> queryCargoPages(Page<CargoVO> page, String cargoName);

    /**
     * 按照条件排序查询
     * @param page
     * @param code
     * @return
     */
    Return<Page<CargoVO>> queryCargoPagesSort(Page<CargoVO> page, int code);

    /**
     * 根据id查询货物信息
     * @param cargoId
     * @return
     */
    CargoVO queryById(long cargoId);


    /**
     * 货物数量统计
     * @return
     * @param cargoName
     */
    long cargoCount(String cargoName);
}
