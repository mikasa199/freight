package com.yang.freight.infrastructure.repository;

import com.yang.freight.common.Constants;
import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.driver.model.req.SubCargoReq;
import com.yang.freight.domain.driver.model.req.SubmitOrderReq;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.driver.model.vo.DriverVO;
import com.yang.freight.domain.driver.repository.IDriverRepository;
import com.yang.freight.domain.order.model.req.StateTransferReq;
import com.yang.freight.domain.order.model.vo.OrderVO;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.domain.support.location.LocationUtils;
import com.yang.freight.infrastructure.dao.ICargoDao;
import com.yang.freight.infrastructure.dao.IDriverDao;
import com.yang.freight.infrastructure.dao.IOrderDao;
import com.yang.freight.infrastructure.po.Cargo;
import com.yang.freight.infrastructure.po.Driver;
import com.yang.freight.infrastructure.po.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class DriverRepository implements IDriverRepository {

    @Resource
    private IDriverDao driverDao;

    @Resource
    private ICargoDao cargoDao;

    @Resource
    private IOrderDao orderDao;

    @Resource
    private Map<Constants.Ids,IIdGenerator> idGeneratorMap;

    private Logger logger = LoggerFactory.getLogger(IDriverRepository.class);

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

        if (null != driver) {
            DriverVO driverVO = new DriverVO();
            driverVO.setDriverId(driver.getDriverId());
            driverVO.setDriverName(driver.getDriverName());
            driverVO.setHashedPassword(driver.getHashedPassword());
            driverVO.setSalt(driver.getSalt());
            driverVO.setPhone(phone);
            return driverVO;
        }else {
            return null;
        }
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
            //坐标转换为位置信息
            cargoVO.setBeginLocation(LocationUtils.coordinateToAddress(cargo.getBeginLocation()));
            cargoVO.setEndLocation(LocationUtils.coordinateToAddress(cargo.getEndLocation()));
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
    public OrderVO createOrder(SubmitOrderReq req) {


        Cargo cargo = cargoDao.queryById(req.getCargoId());

        Order order = new Order();
        IIdGenerator iIdGenerator = idGeneratorMap.get(Constants.Ids.ShortCode);
        order.setOrderId(iIdGenerator.nextId());
        order.setDriverId(req.getDriverId());
        order.setCargoId(req.getCargoId());
        order.setBossId(cargo.getBossId());
        order.setCargoWeight(req.getSubStock());
        // 货物重量 * 货物单价
        order.setValue(req.getSubStock().multiply(cargo.getValue()));
        order.setState(-1);

        orderDao.insert(order);

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return orderVO;
    }

    @Override
    public boolean subStock(SubmitOrderReq req, long orderId) {
        Cargo cargo = cargoDao.queryById(req.getCargoId());

        int i1 = cargo.getStock().compareTo(req.getSubStock());
        if (i1 < 0) {
            logger.info("货物库存不足，无法扣减库存。");
            return false;
        }else {
            SubCargoReq subCargoReq = new SubCargoReq();
            subCargoReq.setCargoId(req.getCargoId());
            subCargoReq.setSubStock(req.getSubStock());
            int i = cargoDao.subStock(subCargoReq);
            if (i == 0) {
                return false;
            }else {
                StateTransferReq stateTransferReq = new StateTransferReq();
                stateTransferReq.setOrderId(orderId);
                stateTransferReq.setBeforeState(-1);
                stateTransferReq.setAfterState(0);
                orderDao.updateState(stateTransferReq);
                return true;
            }
        }
    }
}
