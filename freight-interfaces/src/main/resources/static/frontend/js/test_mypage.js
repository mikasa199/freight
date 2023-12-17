// 导入配置文件

import config from './config.js';




// 页面加载完毕时执行
document.addEventListener('DOMContentLoaded', () => {
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));

    const userId = userInfo.userId;
    const userIdentity = userInfo.userIdentity;
    const userPhone = userInfo.userPhone
    const userName = userInfo.userName

    document.querySelector('.user-info .name').textContent = `用户名：${userName}`;
    document.querySelector('.user-info .phone').textContent = `手机号：${userPhone}`;
    // 显示用户ID（如果存在）
    if (userId) {
        document.querySelector('.number .Id').textContent = `ID: ${userId}`;
    } else {
        console.log("用户未登录");
    }

    // 根据用户身份显示或隐藏发布订单按钮
    const userConfirmButton = document.querySelector('.confirm-container .user-confirm');
    if (userIdentity === 'boss') {
        userConfirmButton.style.display = 'block';
    } else {
        userConfirmButton.style.display = 'none';
    }
});


document.querySelector('.confirm-container .user-confirm').addEventListener('click', () => {
    location.href = './test_postbill.html'
})



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
