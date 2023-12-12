package com.yang.freight.domain.cargo.repository;

import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.cargo.model.vo.CargoVO;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
public interface ICargoRepository {

    boolean addCargo(CargoVO cargoVO);

    /**
     * 查询货物信息
     * @param page
     * @param cargoName
     * @return
     */
    public Return<Page<CargoVO>> queryCargoList(Page<CargoVO> page, String cargoName);

    /**
     * 按照条件排序查询
     * @param page
     * @param code
     * @return
     */
    public Return<Page<CargoVO>> queryCargoListSort(Page<CargoVO> page, int code);

    /**
     * 根据bossid查询对应的货物信息
     * @param page
     * @param bossId
     * @return
     */
    public Return<Page<CargoVO>> queryCargoListSort(Page<CargoVO> page, long  bossId);

    /**
     * 查询货物数量
     * @param cargoName
     * @return
     */
    public long cargoCount(String cargoName);

    /**
     * 根据bossId查询该老板发布的货物数量
     * @param bossId
     * @return
     */
    public long countByBossId(long bossId);

    /**
     * 根据货物id查询货物信息
     * @param cargoId
     * @return
     */
    public CargoVO queryById(long cargoId);
}
