

// 配置服务器接口




const url = 'http://localhost:9999'



const config = {
    
    cargoListApi: `${url}/cargo/list`,    //货单展示接口   方法：GET  params:long page, long pageSize, String cargoName
    cargoListSortApi:`${url}/cargo/list/sort`,  // 货单排序展示接口   方法：GET    params: page, pageSize,code
    acceptBillApi: `${url}/order`,         //司机接单接口， 方法:POST  data: cargoId,driverId,subStock    
    login_pwd_BossApi: `${url}/boss/logon`,       //密码登录接口， 方法：POST data: phone, password
    login_pwd_DriverApi: `${url}/driver/logon`, 
    login_code_BossApi: `${url}/boss/logon/code`,    // 验证码登录
    login_code_DriverApi: `${url}/driver/logon/code`,
    
    postBIllApi: `${url}/cargo/add`,   //老板发布订单， 方法：POST  data:cargoName, cargoWeight, beginTime, endTime, 
                                            //                              beginLocation, endLocation, value,info,bossId
    sendMessageApi: `${url}/driver/sendMsg`,     //发送验证码  方法：POST  data: phone                            
    register_BossApi: `${url}/boss/login` ,      // 注册        方法: POST  data: phone,code,password
    register_DriverApi: `${url}/driver/login`,
    account_driver_update_nameApi: `${url}/driver/update/name`,    //修改司机用户名
    account_driver_update_passwordApi: `${url}/driver/update/password`,   //修改司机密码
    account_boss_update_nameApi: `${url}/boss/update/name`,    //修改老板用户名
    account_boss_update_passwordApi: `${url}/boss/update/password`,   //修改老板密码
    
    my_order_driver_searchApi:`${url}/order/list/driverId`,    // 司机查看自己接的订单     params:{driverId, page , pageSize}
    my_cargo_boss_searchApi: `${url}/cargo/list/bossId`,    // 老板查看自己订单
    cargo_list_driverInfo: `${url}/order/list/cargoId`,   //老板查看司机的接单订单   params:{cargoId,page,pageSize}
    my_cargo_boss_searchDetailsApi: `${url}/cargo/cargoId`,    // 老板查看详细货物订单
    
    bossAccount: `${url}/boss/bossId`,   // 老板端mypage页面的加载   参数：bossId
    bossAccount: `${url}/driver/driverId`,   // 司机端mypage页面的加载   参数：driverId
    


}

// config.js
export default config;