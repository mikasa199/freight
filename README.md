# 货运订单发布/接单平台
## 功能介绍
### 老板端
1. 发布货运订单，
2. 订单内容包含：起点，终点，运费报酬，货物种类，货物重量，时间范围/期限，司机的评价，备注信息
3. 用户认证（公司认证）

### 司机端

1. 抢单（根据起点和终点进行筛选货送订单）
2. 用户认证（实际运营车辆认证，驾驶证认证）--->包含个体司机认证和车老板认证（车老板拥有多辆货车）
3. 订单系统（提供查看订单的详情）
4. 我的钱包
5. 我的车辆
6. 我的车老板
7. 处理中心
8. 消息中心
9. 评价中心
10. 软件意见反馈

### 管理端

1. 资格审查（司机的身份证、行驶证、从业资格证）

## 库表分析

![image-20231110131835871](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202311101318278.png)

<img src="https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202311101324736.png" alt="image-20231110132411686" style="zoom: 67%;" />

<img src="https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202311101324938.png" alt="image-20231110132428895" style="zoom:50%;" />

# 界面与功能介绍

## 登录注册界面
1. 选择司机或老板身份进行注册或登录【已完成】
2. 可使用密码登录和验证码登录两种方案【已完成】

## 司机界面
1. 分页查询货物信息（可按照货物名称查询）【已完成】
2. 分页查询货物信息（按照起点和终点筛选）【已完成】
3. 下单，司机选择自己要运输的货物重量并下单【已完成】
4. 查询下单的订单信息和订单状态【已完成】
5. 更新订单状态功能【未完成】
6. 更新绑定的手机号、更新姓名、更改密码【已完成】
7. 司机根据自己的位置信息，获取附近的订单信息【未完成】

## 老板界面
1. 发布货物信息页面 【已完成】
2. 查看司机接单状态，以及更新对应的订单状态【未完成】  
3. 查看其它老板发布的货物信息，不提供接单功能【已完成】
4. 更新绑定的手机号、更新姓名、更改密码【已完成】
5. 老板根据自己的位置，可以要求附近的司机接单【未完成】
6. 调整布局，首页应该显示发布货物信息【未完成】

## 管理员界面
1. 实名信息、运营资格、驾驶资格验证【未完成】【需要对接第三方付费接口，暂时未实现】



# 付费API汇总

## 1. 短信服务

1. [阿里云](https://cn.aliyun.com/product/sms?from_alibabacloud=&spm=5176.21213303.J_qCOwPWspKEuWcmp8qiZNQ.2.4f1a2f3dyBaApT&scm=20140722.S_card@@%E4%BA%A7%E5%93%81@@125575.S_card0.ID_card@@%E4%BA%A7%E5%93%81@@125575-RL_%E7%9F%AD%E4%BF%A1-LOC_search~UND~card~UND~item-OR_ser-V_3-P0_0)
    ![image-20240110090551176](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202401100905297.png)

2. [腾讯云](https://buy.cloud.tencent.com/sms)

    ![image-20240110093144282](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202401100931483.png)

3. [京东云](https://sms-console.jdcloud.com/open)

    ![img](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202401100935667.png)

## 2. 位置信息服务

1. [高德地图API](https://console.amap.com/dev/user/permission)

    ![image-20240110094251838](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202401100942896.png)

2. [百度地图](https://lbsyun.baidu.com/cashier/quota#/home)

    ![image-20240110094637112](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202401100946158.png)

## 3. 实名认证

1. [阿里云云市场](https://market.aliyun.com/apimarket/detail/cmapi00037894?spm=5176.730005.result.2.43483524OCEKze)

    ![image-20240110105743669](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202401101057727.png)

2. [网易易盾](https://dun.163.com/price/verification-name)

    ![image-20240110104417205](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202401101044282.png)

3. [数脉API](https://www.shumaiapi.com/productDetail/13?bd_vid=11098131091365716239)

    ![image-20240110105533056](https://raw.githubusercontent.com/yangchao19/PicGo/master/typora/202401101055111.png)

## 4. 驾驶证认证

1. [阿里云云市场](https://market.aliyun.com/products/?k=%E9%A9%BE%E9%A9%B6%E8%AF%81%E8%AE%A4%E8%AF%81&scene=market&page=1)

    0.01/次

