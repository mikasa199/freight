package com.yang.freight.controller;

import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.cargo.model.req.ReleaseCargoInfoReq;
import com.yang.freight.domain.cargo.service.ICargoService;
import com.yang.freight.domain.cargo.model.vo.CargoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/10
 * @Copyright：
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cargo")
public class CargoController {

    private Logger logger = LoggerFactory.getLogger(CargoController.class);

    @Resource
    private ICargoService cargoService;

    @PostMapping("/add")
    public Return<String> addCargo(@RequestBody ReleaseCargoInfoReq req) {
        logger.info(req.toString());
        logger.info("开始发布货物信息：{}",req.toString());
        boolean b = cargoService.releaseCargoInfo(req);
        if (b) {
            return Return.success("发布货物信息成功，等待司机接单");
        }else {
            return Return.error("发布货物信息失败，请稍后重试");
        }
    }

    @GetMapping("/list")
    public Return<Page<CargoVO>> queryCargoPages(long page, long pageSize, String cargoName) {

        logger.info("page:{},pageSize:{},cargoName:{}",page,pageSize,cargoName);
        Page<CargoVO> cargoVOPage = new Page<>();
        cargoVOPage.setCurrent(page);
        cargoVOPage.setSize(pageSize);
        long cargoCount = cargoService.cargoCount(cargoName);
        cargoVOPage.setTotal(cargoCount);
        Return<Page<CargoVO>> pageReturn = cargoService.queryCargoPages(cargoVOPage, cargoName);
        return pageReturn;
    }

    @GetMapping("/list/sort")
    public Return<Page<CargoVO>> queryCargoPagesSort(long page, long pageSize, int code) {
        logger.info("page:{},pageSize:{},code:{}",page,pageSize,code);
        Page<CargoVO> cargoVOPage = new Page<>();
        cargoVOPage.setCurrent(page);
        cargoVOPage.setSize(pageSize);
        long cargoCount = cargoService.cargoCount("");
        cargoVOPage.setTotal(cargoCount);
        Return<Page<CargoVO>> pageReturn = cargoService.queryCargoPagesSort(cargoVOPage, code);
        return pageReturn;
    }

    @GetMapping("/list/bossId")
    public Return<Page<CargoVO>> queryCargoPagesByBossId(long page, long pageSize, long bossId) {
        logger.info("page:{},pageSize:{},bossId:{}",page,pageSize,bossId);
        Page<CargoVO> cargoVOPage = new Page<>();
        cargoVOPage.setCurrent(page);
        cargoVOPage.setSize(pageSize);
        long cargoCount = cargoService.countByBossId(bossId);
        cargoVOPage.setTotal(cargoCount);
        Return<Page<CargoVO>> pageReturn = cargoService.queryCargoPagesByBossId(cargoVOPage, bossId);
        return pageReturn;
    }

    @GetMapping("/cargoId")
    public Return<CargoVO> queryCargoByCargoId(long cargoId) {
        logger.info("cargoId:{}",cargoId);
        CargoVO cargoVO = cargoService.queryById(cargoId);
        if (null == cargoVO) {
            logger.info("无对应货物信息 cargoId:{}",cargoId);
            return Return.error("无对应货物信息");
        }
        logger.info(cargoVO.toString());
        return Return.success(cargoVO);
    }
//    @PostMapping("/order")
//    public Return<String> orderCargo(@RequestBody SubmitOrderReq req) {
//
//        logger.info("开始下单:{}",req.toString());
//        boolean b = driverDeploy.submitOrder(req);
//
//        if (b) {
//            return Return.success("下单成功");
//        }else {
//            return Return.error("下单失败");
//        }
//
//    }
}
