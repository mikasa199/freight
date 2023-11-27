package com.yang.freight.domain.driver.service.deploy.impl;

import com.yang.freight.common.Constants;
import com.yang.freight.common.HashedPassword;
import com.yang.freight.common.Return;
import com.yang.freight.domain.driver.model.req.CargoInfoLimitPageReq;
import com.yang.freight.domain.driver.model.req.DriverUpdatePasswordReq;
import com.yang.freight.domain.driver.model.req.InitDriverReq;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.driver.model.vo.DriverVO;
import com.yang.freight.domain.driver.repository.IDriverRepository;
import com.yang.freight.domain.driver.service.deploy.IDriverDeploy;
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
    public boolean createDriver(InitDriverReq req) {
        DriverVO driverVO = new DriverVO();
        IIdGenerator snowFlake = idGeneratorMap.get(Constants.Ids.SnowFlake);
        long driverId = snowFlake.nextId();
        driverVO.setDriverId(driverId);
        driverVO.setPhone(req.getPhone());
        driverVO.setDriverName(req.getDriverName());

        try {
            HashedPassword hashedPassword = encryption.encryptPassword(req.getPassword());
            driverVO.setHashedPassword(hashedPassword.getHashedPassword());
            driverVO.setSalt(hashedPassword.getSalt());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        Boolean result = driverRepository.addDriver(driverVO);
        return result;
    }

    @Override
    public void updatePassword(DriverUpdatePasswordReq req) {

    }

    @Override
    public List<CargoVO> queryCargoInfoLimitPage(CargoInfoLimitPageReq req) {
        return null;
    }

    @Override
    public Return<String> driverLogin(InitDriverReq req) {

        DriverVO driverVO = driverRepository.queryByPhone(req.getPhone());

        if (null == driverVO) {
            return Return.error("该手机号还未注册！");
        }

        HashedPassword hashedPassword = new HashedPassword(driverVO.getHashedPassword(),driverVO.getSalt());

        try {
            boolean result = encryption.verifyPassword(req.getPassword(), hashedPassword);
            if (result) {
                return Return.success("密码正确，登录成功");
            }else {
                return Return.error("密码错误，请重新输入");
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
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
}
