package com.yang.freight.infrastructure.repository;

import com.yang.freight.domain.driver.model.vo.DriverVO;
import com.yang.freight.domain.driver.repository.IDriverRepository;
import com.yang.freight.infrastructure.dao.IDriverDao;
import com.yang.freight.infrastructure.po.Driver;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/23
 * @Copyright：
 */
@Repository
public class DriverRepository implements IDriverRepository {

    @Resource
    private IDriverDao driverDao;

    @Override
    public Boolean addDriver(DriverVO driverVO) {
        Driver driver = new Driver();
        BeanUtils.copyProperties(driverVO,driver);
        int result = driverDao.insert(driver);
        return result == 1;
    }

    @Override
    public DriverVO queryByPhone(String phone) {
        Driver driver = driverDao.queryByPhone(phone);
        DriverVO driverVO = new DriverVO();
        driverVO.setDriverId(driver.getDriverId());
        driverVO.setDriverName(driver.getDriverName());
        driverVO.setHashedPassword(driver.getHashedPassword());
        driverVO.setSalt(driver.getSalt());
        driverVO.setPhone(phone);
        return driverVO;
    }
}
