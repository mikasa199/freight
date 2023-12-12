package com.yang.freight.domain.cargo.service.impl;

import com.yang.freight.common.Constants;
import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.cargo.model.req.ReleaseCargoInfoReq;
import com.yang.freight.domain.cargo.repository.ICargoRepository;
import com.yang.freight.domain.cargo.service.ICargoService;
import com.yang.freight.domain.cargo.model.vo.CargoVO;
import com.yang.freight.domain.support.ids.IIdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
@Service
public class CargoServiceImpl implements ICargoService {

    @Resource
    private ICargoRepository cargoRepository;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public boolean releaseCargoInfo(ReleaseCargoInfoReq req) {
        CargoVO cargoVO = new CargoVO();
        BeanUtils.copyProperties(req,cargoVO);
        cargoVO.setCargoId(idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        cargoVO.setStock(req.getCargoWeight());
        boolean result = cargoRepository.addCargo(cargoVO);
        return result;
    }

    @Override
    public Return<Page<CargoVO>> queryCargoPages(Page<CargoVO> page, String cargoName) {
        return cargoRepository.queryCargoList(page,cargoName);
    }

    @Override
    public Return<Page<CargoVO>> queryCargoPagesSort(Page<CargoVO> page, int code) {
        return cargoRepository.queryCargoListSort(page,code);
    }

    @Override
    public Return<Page<CargoVO>> queryCargoPagesByBossId(Page<CargoVO> page, long bossId) {
        Return<Page<CargoVO>> pageReturn = cargoRepository.queryCargoListSort(page, bossId);
        return pageReturn;
    }

    @Override
    public CargoVO queryById(long cargoId) {
        return cargoRepository.queryById(cargoId);
    }

    @Override
    public long cargoCount(String cargoName) {
        return cargoRepository.cargoCount(cargoName);
    }

    @Override
    public long countByBossId(long bossId) {
        return cargoRepository.countByBossId(bossId);
    }
}
