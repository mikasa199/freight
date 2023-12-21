// 导入配置文件
import config from './config.js';

// 页面加载完毕时执行
document.addEventListener('DOMContentLoaded', () => {
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));

    if (!userInfo) {
        console.log("用户信息不存在，请重新登录");
        // 重定向到登录页面
        // location.href = './test_login_pwd.html';
        return;
    }

    const preUserId = userInfo.userId;
    const preUserIdentity = userInfo.userIdentity;
    // const userPhone = userInfo.userPhone;
    // const userName = userInfo.userName;

    

    // 根据用户身份显示或隐藏发布订单按钮
    const userConfirmButton = document.querySelector('.confirm-container .user-confirm');
    userConfirmButton.style.display = preUserIdentity === 'boss' ? 'block' : 'none';

    // 定义要使用的API URL
    const url = preUserIdentity === 'boss' ? config.bossAccount: config.driverAccount;
    console.log(preUserIdentity);
    console.log(url);
    const data = preUserIdentity === 'boss' ? {bossId:preUserId} : {driverId:preUserId}
    // 发送axios请求
    axios({
        url: url,
        method: 'GET',
        params: data,
    }).then(result => {
        console.log(result);
        // 在这里处理返回的结果

        // 清除旧的用户ID（如果存在）
        localStorage.removeItem('userInfo');
        
        // 使用 BigInt 处理可能的超长整数
        if (preUserIdentity === 'boss') {
           const newUserInfo = {
                // userId: BigInt(result.data.data.bossId).toString(), // 将 BigInt 转换为字符串存储
                userId: preUserId,
                userName: result.data.data.bossName,
                userPhone: result.data.data.phone,
                userIdentity: preUserIdentity // 保留之前的身份信息
            }
            localStorage.setItem('userInfo', JSON.stringify(newUserInfo)); // 存取新用户资料
        } else if(preUserIdentity === 'driver'){
            const newUserInfo = {
                // userId: BigInt(result.data.data.driverId).toString(), // 将 BigInt 转换为字符串存储
                userId: preUserId,
                userName: result.data.data.driverName,
                userPhone: result.data.data.phone,
                userIdentity: preUserIdentity // 保留之前的身份信息
            }
            localStorage.setItem('userInfo', JSON.stringify(newUserInfo)); // 存取新用户资料

        
        }

        // 更新页面上的用户信息
        const newUserInfo = JSON.parse(localStorage.getItem('userInfo'));
        document.querySelector('.user-info .name').textContent = `用户名：${newUserInfo.userName}`;
        console.log(newUserInfo.userName);
        document.querySelector('.user-info .phone').textContent = `手机号：${newUserInfo.userPhone}`;
        console.log(newUserInfo.userPhone);


    }).catch(error => {
        console.log(error);
        // 处理错误
    });
});


document.querySelector('.confirm-container .user-confirm').addEventListener('click', () => {
    location.href = './test_postbill.html';
});

// 绑定退出登录事件
document.querySelector('.confirm-container .log-out').addEventListener('click', () => {
    // 弹出确认对话框
    const isConfirmed = confirm('您确定要退出登录吗？');
    if (isConfirmed) {
        // 清除localStorage中的用户信息
        localStorage.removeItem('userInfo');
        
        // 页面跳转到登录界面
        location.href = './test_login_pwd.html';
    }
});
