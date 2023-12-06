package com.yang.freight.domain.driver.service.deploy.impl;

import com.yang.freight.common.Constants;
import com.yang.freight.common.HashedPassword;
import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.driver.model.req.CargoInfoLimitPageReq;
import com.yang.freight.domain.driver.model.req.DriverUpdatePasswordReq;
import com.yang.freight.domain.driver.model.req.InitDriverReq;
import com.yang.freight.domain.driver.model.req.SubmitOrderReq;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.driver.model.vo.DriverVO;
import com.yang.freight.domain.driver.repository.IDriverRepository;
import com.yang.freight.domain.driver.service.deploy.IDriverDeploy;
import com.yang.freight.domain.order.model.vo.OrderVO;
import com.yang.freight.domain.support.code.SMSUtils;
import com.yang.freight.domain.support.code.ValidateCodeUtils;
import com.yang.freight.domain.support.ids.IIdGenerator;
import com.yang.freight.domain.support.password.IEncryption;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
@Service
public class DriverDeployImpl implements IDriverDeploy {

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Resource
    private IEncryption encryption;

    @Resource
    private IDriverRepository driverRepository;

    @Override
    public DriverVO createDriver(InitDriverReq req) {
        DriverVO driverVO = new DriverVO();
        IIdGenerator snowFlake = idGeneratorMap.get(Constants.Ids.SnowFlake);
        long driverId = snowFlake.nextId();
        driverVO.setDriverId(driverId);
        driverVO.setPhone(req.getPhone());
        //driverVO.setDriverName(req.getDriverName());

        try {
            HashedPassword hashedPassword = encryption.encryptPassword(req.getPassword());
            driverVO.setHashedPassword(hashedPassword.getHashedPassword());
            driverVO.setSalt(hashedPassword.getSalt());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        Boolean result = driverRepository.addDriver(driverVO);
        return result ? driverVO : null;
    }

    @Override
    public DriverVO queryByPhone(String phone) {
        return driverRepository.queryByPhone(phone);
    }

    @Override
    public void updatePassword(DriverUpdatePasswordReq req) {

    }

    @Override
    public List<CargoVO> queryCargoInfoLimitPage(CargoInfoLimitPageReq req) {
        return null;
    }

    @Override
    public Return<DriverVO> driverLogon(InitDriverReq req) {

        DriverVO driverVO = driverRepository.queryByPhone(req.getPhone());

        if (null == driverVO) {
            return Return.error("用户未注册！");
        }

        HashedPassword hashedPassword = new HashedPassword(driverVO.getHashedPassword(),driverVO.getSalt());

        try {
            boolean result = encryption.verifyPassword(req.getPassword(), hashedPassword);
            if (result) {
                return Return.success(driverVO);
            }else {
                return Return.error("密码错误，请重新输入！");
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Return.error("登录失败，未知错误，请重试~");
    }

    @Override
    public String sendMsg(String phone) throws Exception {
        //1. 生成验证码
        String code = ValidateCodeUtils.generateValidateCode(6).toString();
        SMSUtils.sendMsg(phone,code);
        return code;
    }

    @Override
    public DriverVO checkAndInit(String phone) {

        DriverVO driverVO = driverRepository.queryByPhone(phone);

        if (driverVO != null) {
            return driverVO;
        }

        DriverVO driverVO1 = new DriverVO();
        driverVO1.setPhone(phone);
        driverVO1.setDriverId(idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        Boolean aBoolean = driverRepository.addDriver(driverVO1);
        return driverVO1;
    }

    @Override
    public Return<Page<CargoVO>> queryCargoPages(Page<CargoVO> page, String cargoName) {
        return driverRepository.queryCargoList(page,cargoName);
    }

    @Override
    public Return<Page<CargoVO>> queryCargoPagesSort(Page<CargoVO> page, int code) {
        return driverRepository.queryCargoListSort(page,code);
    }

    @Override
    public Return<Page<CargoVO>> queryPagesSortByMethod(Page<CargoVO> page, Constants.Method method) {
        return null;
    }

    @Override
    public long cargoCount(String cargoName) {
        return driverRepository.cargoCount(cargoName);
    }

    @Override
    public boolean submitOrder(SubmitOrderReq req) {


        //1. 生成订单并设置订单状态
        OrderVO order = driverRepository.createOrder(req);

        //2. 扣减库存
        boolean b = driverRepository.subStock(req, order.getOrderId());

        return b;
    }
}
