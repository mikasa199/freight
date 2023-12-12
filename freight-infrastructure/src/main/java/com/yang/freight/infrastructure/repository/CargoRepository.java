package com.yang.freight.infrastructure.repository;

import com.yang.freight.common.Location;
import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.cargo.repository.ICargoRepository;
import com.yang.freight.domain.cargo.model.vo.CargoVO;
import com.yang.freight.domain.support.location.LocationUtils;
import com.yang.freight.infrastructure.dao.ICargoDao;
import com.yang.freight.infrastructure.po.Cargo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
@Repository
public class CargoRepository implements ICargoRepository {

    private Logger logger = LoggerFactory.getLogger(CargoRepository.class);

    @Resource
    private ICargoDao cargoDao;

    @Override
    public boolean addCargo(CargoVO cargoVO) {

        Cargo cargo = new Cargo();
        BeanUtils.copyProperties(cargoVO,cargo);

        //计算起点与终点之间的距离
        String beginLocation = cargo.getBeginLocation();
        String endLocation = cargo.getEndLocation();
        String[] s1 = beginLocation.split(",");
        String[] s2 = endLocation.split(",");
        Double distance = 0.001 * LocationUtils.distance(new Location(Double.parseDouble(s1[0]), Double.parseDouble(s1[1])), new Location(Double.parseDouble(s2[0]), Double.parseDouble(s2[1])));
        cargo.setDistance(new BigDecimal(distance));

        int result = cargoDao.insert(cargo);
        return result == 1;
    }

    @Override
    public Return<Page<CargoVO>> queryCargoList(Page<CargoVO> page, String cargoName) {
        long count =  cargoCount(cargoName);

        long current = page.getCurrent();
        long size = page.getSize();

        if (count % size == 0) {
            page.setTotal(count / size);
        }else {
            page.setTotal(count / size + 1);
        }

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
    public Return<Page<CargoVO>> queryCargoListSort(Page<CargoVO> page, int code) {

        long count =  cargoCount("");
        // page.setTotal(count);

        long current = page.getCurrent();
        long size = page.getSize();

        // 计算总页数
        if (count % size == 0) {
            page.setTotal(count / size);
        }else {
            page.setTotal(count / size + 1);
        }

        List<Cargo> cargoList = null;
        if (code > 0) {
            cargoList = cargoDao.queryListSortUp((current - 1) * size, size, code);
        }else if (code < 0){
            cargoList = cargoDao.queryListSortDown((current - 1) * size, size, code);
        }else {
            cargoList = cargoDao.queryList((current - 1) * size, size,"");
        }

        ArrayList<CargoVO> list = new ArrayList<>();
        for (Cargo cargo : cargoList) {
            CargoVO cargoVO = new CargoVO();
            logger.info(cargoVO.toString());
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
    public Return<Page<CargoVO>> queryCargoListSort(Page<CargoVO> page, long bossId) {
        long count =  countByBossId(bossId);
        // page.setTotal(count);

        long current = page.getCurrent();
        long size = page.getSize();

        // 计算总页数
        if (count % size == 0) {
            page.setTotal(count / size);
        }else {
            page.setTotal(count / size + 1);
        }

        List<Cargo> cargoList = cargoDao.queryByBossId((current - 1) * size, size,bossId);

        ArrayList<CargoVO> list = new ArrayList<>();
        for (Cargo cargo : cargoList) {
            CargoVO cargoVO = new CargoVO();
            logger.info(cargoVO.toString());
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
    public long countByBossId(long bossId) {
        return cargoDao.countByBossId(bossId);
    }

    @Override
    public CargoVO queryById(long cargoId) {
        Cargo cargo = cargoDao.queryById(cargoId);
        CargoVO cargoVO = new CargoVO();
        BeanUtils.copyProperties(cargo,cargoVO);
        return cargoVO;
    }
}
