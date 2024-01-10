package com.yang.freight.infrastructure.dao;

import com.yang.freight.domain.cargo.model.req.SubCargoReq;
import com.yang.freight.infrastructure.po.Cargo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/12
 * @Copyright：
 */
@Mapper
public interface ICargoDao {

    /**
     * 新增货物信息
     * @param cargo
     */
    int insert(Cargo cargo);

    /**
     * 根据货物id 扣减对应的重量
     * @param req
     * @return
     */
    int subStock(SubCargoReq req);

    /**
     * 根据页数和页大小查询货物信息
     * @param page
     * @param pageSize
     * @param cargoName
     * @return
     */
    List<Cargo> queryList(@Param("page") long page, @Param("pageSize") long pageSize, @Param("cargoName") String cargoName);

    // TODO 分页查询中，数据类型为 decimal 无法一块排序查询，需要单独写sql语句
    /**
     * 按照条件升序查询
     * @param page
     * @param pageSize
     * @return
     */
    List<Cargo> queryListSortUp(@Param("page") long page, @Param("pageSize") long pageSize, @Param("code") int code);

    // TODO 分页查询中，数据类型为 decimal 无法一块排序查询，需要单独写sql语句
    /**
     * 按照条件降序查询
     * @param page
     * @param pageSize
     * @return
     */
    List<Cargo> queryListSortDown(@Param("page") long page, @Param("pageSize") long pageSize, @Param("code") int code);



    /**
     * 根据Id查询货物信息
     * @param cargoId
     * @return
     */
    Cargo queryById(@Param("cargoId") long cargoId);

    /**
     * 根据司机id查询货物信息
     *
     * @param page
     * @param pageSize
     * @param bossId
     * @return
     */
    List<Cargo> queryByBossId(@Param("page") long page, @Param("pageSize") long pageSize, @Param("bossId") long bossId);

    /**
     * 查询货物信息数量
     * @param cargoName
     * @return
     */
    int cargoCount(@Param("cargoName") String cargoName);

    int countByBossId(@Param("bossId") long bossId);
}
