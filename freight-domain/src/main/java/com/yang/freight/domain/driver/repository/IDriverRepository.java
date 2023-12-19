package com.yang.freight.domain.driver.repository;

import com.yang.freight.domain.driver.model.vo.AuthenticationVO;
import com.yang.freight.domain.driver.model.vo.DriverVO;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public interface IDriverRepository {
    /**
     * 新增司机
     * @param driverVO
     * @return
     */
    public Boolean addDriver(DriverVO driverVO);

    /**
     * 根据电话号码查询司机
     * @param phone
     * @return
     */
    public DriverVO queryByPhone(String phone);

    public DriverVO queryById(long driverId);

//    /**
//     * 查询货物信息
//     * @param page
//     * @param cargoName
//     * @return
//     */
//    public Return<Page<CargoVO>> queryCargoList(Page<CargoVO> page, String cargoName);
//
//    /**
//     * 按照条件排序查询
//     * @param page
//     * @param code
//     * @return
//     */
//    public Return<Page<CargoVO>> queryCargoListSort(Page<CargoVO> page, int code);

//    /**
//     * 查询货物数量
//     * @param cargoName
//     * @return
//     */
//    public long cargoCount(String cargoName);


//    /**
//     * 创建订单
//     * @param req
//     * @return
//     */
//    public OrderVO  createOrder(SubmitOrderReq req);
//
//    /**
//     * 扣减库存
//     * @param req
//     * @param orderId
//     * @return 是否扣减成功
//     */
//    public boolean subStock(SubmitOrderReq req, long orderId);

    /**
     * 新增身份认证信息
     * @param authenticationVO
     * @return
     */
    public boolean addAuthentication(AuthenticationVO authenticationVO);

    /**
     * 更新司机姓名
     * @param driverVO
     * @return
     */
    public boolean updateDriverName(DriverVO driverVO);

    public boolean updatePassword(DriverVO driverVO);

    public boolean updatePhone(DriverVO driverVO);

}
