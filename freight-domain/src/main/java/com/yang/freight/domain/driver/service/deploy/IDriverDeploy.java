package com.yang.freight.domain.driver.service.deploy;

import com.yang.freight.domain.driver.model.req.CargoInfoLimitPageReq;
import com.yang.freight.domain.driver.model.req.DriverUpdatePasswordReq;
import com.yang.freight.domain.driver.model.req.InitDriverReq;
import com.yang.freight.domain.driver.model.vo.CargoVO;

import java.sql.Driver;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public interface IDriverDeploy {

    /**
     * 新增司机
     * @param req
     */
    void createDriver(InitDriverReq req);

    /**
     * 更新密码
     * @param req
     */
    void updatePassword(DriverUpdatePasswordReq req);

    /**
     * 分页查询货物信息
     * @param req
     * @return
     */
    List<CargoVO> queryCargoInfoLimitPage(CargoInfoLimitPageReq req);

}
