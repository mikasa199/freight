package com.yang.freight.domain.driver.service.deploy.impl;

import com.yang.freight.domain.driver.model.req.CargoInfoLimitPageReq;
import com.yang.freight.domain.driver.model.req.DriverUpdatePasswordReq;
import com.yang.freight.domain.driver.model.req.InitDriverReq;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.driver.service.deploy.IDriverDeploy;

import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public class DriverDeploy implements IDriverDeploy {
    @Override
    public void createDriver(InitDriverReq req) {

    }

    @Override
    public void updatePassword(DriverUpdatePasswordReq req) {

    }

    @Override
    public List<CargoVO> queryCargoInfoLimitPage(CargoInfoLimitPageReq req) {
        return null;
    }
}
