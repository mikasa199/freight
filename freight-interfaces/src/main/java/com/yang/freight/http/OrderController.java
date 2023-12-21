package com.yang.freight.http;

import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.order.model.req.SubmitOrderReq;
import com.yang.freight.domain.order.model.res.OrderRes;
import com.yang.freight.domain.order.service.IOrderService;
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
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public Return<String> orderCargo(@RequestBody SubmitOrderReq req) {

        logger.info("开始下单:{}",req.toString());
        boolean b = orderService.submitOrder(req);

        if (b) {
            return Return.success("下单成功");
        }else {
            return Return.error("下单失败");
        }
    }

    @GetMapping("/list/cargoId")
    public Return<Page<OrderRes>> queryByCargoId(long page, long pageSize, long cargoId) {
        logger.info("page:{},pageSize:{},cargoId:{}",page,pageSize,cargoId);
        Page<OrderRes> pageRes = new Page<>();
        pageRes.setCurrent(page);
        pageRes.setSize(pageSize);
        Return<Page<OrderRes>> pageReturn = orderService.queryByCargoId(pageRes, cargoId);
        return pageReturn;
    }

    @GetMapping("/list/driverId")
    public Return<Page<OrderRes>> queryByDriverId(long page, long pageSize, long driverId) {
        logger.info("page:{},pageSize:{},driverId:{}",page,pageSize,driverId);
        Page<OrderRes> pageRes = new Page<>();
        pageRes.setCurrent(page);
        pageRes.setSize(pageSize);
        Return<Page<OrderRes>> pageReturn = orderService.queryByDriverId(pageRes, driverId);
        return pageReturn;
    }

}
