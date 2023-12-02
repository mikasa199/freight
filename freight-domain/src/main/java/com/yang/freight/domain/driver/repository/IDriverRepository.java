package com.yang.freight.domain.driver.repository;

import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.driver.model.req.SubmitOrderReq;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.driver.model.vo.DriverVO;
import com.yang.freight.domain.order.model.vo.OrderVO;

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

    /**
     * 查询货物信息
     * @param page
     * @param cargoName
     * @return
     */
    public Return<Page<CargoVO>> queryCargoList(Page<CargoVO> page, String cargoName);

    /**
     * 查询货物数量
     * @param cargoName
     * @return
     */
    public long cargoCount(String cargoName);


    /**
     * 创建订单
     * @param req
     * @return
     */
    public OrderVO  createOrder(SubmitOrderReq req);

    /**
     * 扣减库存
     * @param req
     * @param orderId
     * @return 是否扣减成功
     */
    public boolean subStock(SubmitOrderReq req, long orderId);

}
