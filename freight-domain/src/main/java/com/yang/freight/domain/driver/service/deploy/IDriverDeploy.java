package com.yang.freight.domain.driver.service.deploy;

import com.yang.freight.common.Constants;
import com.yang.freight.common.Page;
import com.yang.freight.common.Return;
import com.yang.freight.domain.driver.model.req.*;
import com.yang.freight.domain.driver.model.vo.CargoVO;
import com.yang.freight.domain.driver.model.vo.DriverVO;

import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/20
 * @Copyright：
 */
public interface IDriverDeploy {

    /**
     * 新增司机
     * @param req
     * @return
     */
    DriverVO createDriver(InitDriverReq req);

    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    DriverVO queryByPhone(String phone);

    /**
     * 更新密码
     * @param req
     * @return
     */
    boolean updatePassword(UpdatePasswordReq req);

    /**
     * 分页查询货物信息
     * @param req
     * @return
     */
    List<CargoVO> queryCargoInfoLimitPage(CargoInfoLimitPageReq req);

    /**
     * 司机用户登录
     * @param req 用户登录输入的信息
     * @return 返回用户是否正确
     */
    Return<DriverVO> driverLogon(InitDriverReq req);

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    String sendMsg(String phone) throws Exception;

    /**
     * 检验并初始化，如果手机号已被注册，则返回对应的用户，否则返回新创建的用户
     * @param phone
     * @return
     */
    DriverVO checkAndInit(String phone);

    /**
     * 根据货物名和页数、页面大小 获取货物数据
     * @param page
     * @param cargoName
     * @return
     */
    Return<Page<CargoVO>> queryCargoPages(Page<CargoVO> page, String cargoName);

    /**
     * 按照条件排序查询
     * @param page
     * @param code
     * @return
     */
    Return<Page<CargoVO>> queryCargoPagesSort(Page<CargoVO> page, int code);

    /**
     * 根据条件进行排序查询
     * @param page
     * @param method
     * @return
     */
    Return<Page<CargoVO>> queryPagesSortByMethod(Page<CargoVO> page, Constants.Method method);

    /**
     * 货物数量统计
     * @return
     * @param cargoName
     */
    long cargoCount(String cargoName);

    /**
     * 提交扣减库存的订单
     * @param req
     * @return
     */
    boolean submitOrder(SubmitOrderReq req);

    /**
     * 新增身份认证
     * @param req
     * @return
     */
    boolean addAuthentication(AddAuthenticationReq req);

}
