package com.yang.freight.infrastructure.repository;

import com.yang.freight.common.Constants;
import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.driver.model.req.SubmitOrderReq;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.driver.model.vo.DriverVO;
import com.yang.freight.domain.driver.repository.IDriverRepository;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.infrastructure.dao.ICargoDao;
import com.yang.freight.infrastructure.dao.IDriverDao;
import com.yang.freight.infrastructure.dao.IOrderDao;
import com.yang.freight.infrastructure.po.Cargo;
import com.yang.freight.infrastructure.po.Driver;
import com.yang.freight.infrastructure.po.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ICargoDao cargoDao;

    @Resource
    private IOrderDao orderDao;

    @Resource
    private Map<Constants.Ids,IIdGenerator> idGeneratorMap;

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
        if (null != driver) {
            driverVO.setDriverId(driver.getDriverId());
            driverVO.setDriverName(driver.getDriverName());
            driverVO.setHashedPassword(driver.getHashedPassword());
            driverVO.setSalt(driver.getSalt());
            driverVO.setPhone(phone);
        }
        return driverVO;
    }

    @Override
    public Return<Page<CargoVO>> queryCargoList(Page<CargoVO> page, String cargoName) {
        long count =  cargoCount(cargoName);
        page.setTotal(count);

        long current = page.getCurrent();
        long size = page.getSize();

        List<Cargo> cargoList = cargoDao.queryList((current - 1) * size, size, cargoName);
        ArrayList<CargoVO> list = new ArrayList<>();
        for (Cargo cargo : cargoList) {
            CargoVO cargoVO = new CargoVO();
            BeanUtils.copyProperties(cargo,cargoVO);
            list.add(cargoVO);
        }
        page.setRecords(list);
        return Return.success(page);
    }

    @Override
    public long cargoCount(String cargoName) {
        return cargoDao.cargoCount(cargoName);
    }

    @Override
    public void createOrder(SubmitOrderReq req) {
        Order order = new Order();
        IIdGenerator iIdGenerator = idGeneratorMap.get(Constants.Ids.ShortCode);
        order.setOrderId(iIdGenerator.nextId());
        order.setDriverId(req.getDriverId());

        orderDao.insert(order);

    }

    @Override
    public boolean subStock(SubmitOrderReq req) {
        return false;
    }
}
