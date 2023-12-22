// 导入配置文件

import config from './config.js';

// 导入返回函数
import { goBack } from './utilityFunction.js'

// 绑定返回元素点击事件
document.querySelector('.top-container .return').addEventListener('click', goBack)



document.addEventListener('DOMContentLoaded', function () {
    const newUserNameInput = document.querySelector('input[name="adjust-name"]');
    const submitButton = document.querySelector('.submit');
    const overlay = document.getElementById('overlay');
    const overlayContent = overlay.querySelector('.overlay-content');
    const UserNameInput  = document.querySelector('input[name="name"]');
    // 仅在有输入时启用提交按钮
    newUserNameInput.addEventListener('input', function () {
        if (newUserNameInput.value.trim()) {
            // 如果输入框有内容，启用按钮并应用 .active 样式
            submitButton.disabled = false;
            submitButton.classList.add('active');
        } else {
            // 如果输入框为空，禁用按钮并移除 .active 样式
            submitButton.disabled = true;
            submitButton.classList.remove('active');
        }
    });

    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
        if (!userInfo) {
            showOverlay('未找到用户信息');
            return;
        }

    const { userId, userIdentity, userName } = userInfo;
    UserNameInput.value = userName

    const newUserName = newUserNameInput.value.trim();
    // 提交按钮点击事件
submitButton.addEventListener('click', function (event) {
        event.preventDefault(); // 阻止表单默认提交行为

        // 根据用户身份构建要发送的数据
    let dataForName;
    let updateApi;
    if (userIdentity === 'driver') {
        dataForName = {
            driverId: userId,
            driverName: newUserName
        };
        updateApi = config.account_driver_update_nameApi;
    } else {
        dataForName = {
            bossId: userId,
            bossName: newUserName
        };
        updateApi = config.account_boss_update_nameApi;
    }

    // 使用 axios 发送请求
    axios({
        url: updateApi,
        method: 'POST',
        data: dataForName
    }).then(response => {
        showOverlay('用户名更新成功');
    }).catch(error => {
        showOverlay('更新用户名出错: ' + error.message);
    });
});

    // 显示带消息的蒙层函数
    function showOverlay(message) {
        overlayContent.textContent = message;
        overlay.style.display = 'flex';
        setTimeout(() => {
            overlay.style.display = 'none';
        }, 3000); // 3秒后隐藏蒙层
    }
});


  

    // // 提交按钮事件处理
    // submitButton.addEventListener('click', function (event) {
        
    //     event.preventDefault(); // 阻止默认行为

    //     // // 清空反馈信息数组
    //     // const feedbackMessages = [];


    //     // 获取userId
    //     const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    //     const userId = userInfo ? userInfo.userId : null;
    //     console.log(userId);

    //     // 获取身份信息
    //     const userIdentity = userInfo ? userInfo.userIdentity : null;

    //     // 根据用户身份选择接口
    //     const nameUpdateApi = userIdentity === 'boss' ? config.account_boss_update_nameApi : config.account_driver_update_nameApi;
    //     const passwordUpdateApi = userIdentity === 'boss' ? config.account_boss_update_passwordApi : config.account_driver_update_passwordApi;

    //     // 检查用户名是否被更改
    //     if (!newUserNameInput.readOnly) {
    //         const newUserName = newUserNameInput.value;
    //         // 依据身份构建提交用户名的数据

           
    //     const dataForName = userIdentity === 'driver' ? {
    //         driverId: userId,
    //         driverName: newUserName
    //     } : {
    //         bossId: userId,
    //         bossName: newUserName
    //     }
    //         // 提交新用户名
    //         axios({
    //             url: nameUpdateApi,
    //             method: 'POST',
    //             data: dataForName
    //         }).then(result => {
    //             console.log(result);
    //             userNameUpdateSuccess = true;
    //             checkUpdatesAndShowMessage();
    //         }).catch(error => {
    //             console.log(error);
    //             userNameUpdateSuccess = false;
    //             checkUpdatesAndShowMessage();
    //         });
    //     }

    //     // 检查密码是否被更改
    //     if (!originalPasswordInput.readOnly) {
    //         const beforePassword = originalPasswordInput.value;
    //         const afterPassword = newPasswordInput.value;
    //         const confirmPassword = confirmPasswordInput.value;

    //         if(afterPassword === confirmPassword) {
    //             // 依据身份判定构建提交密码的数据

    //             const dataForPassword =  userIdentity === 'driver' ? {
    //                 driverId: userId,
    //                 beforePassword: beforePassword,
    //                 afterPassword: afterPassword
    //             } : {
    //                 bossId: userId,
    //                 beforePassword: beforePassword,
    //                 afterPassword: afterPassword
    //             };
    //             // 提交新密码
    //             axios({
    //                 url: passwordUpdateApi,
    //                 method: 'POST',
    //                 data: dataForPassword
    //             }).then(result => {

    //                 console.log(result);
    //                 passwordUpdateSuccess = true;
    //                 checkUpdatesAndShowMessage();


    //             }).catch(error => {
    //                 console.log(error);
    //                 passwordUpdateSuccess = false;
    //                 checkUpdatesAndShowMessage();
    //             });
    //         } else {
    //             alert("新密码和确认密码不匹配！");
    //         }
    //     }

    //     function checkUpdatesAndShowMessage() {
    //         if (userNameUpdateSuccess && passwordUpdateSuccess) {
    //             showOverlay('用户名和密码更新成功');
    //         } else if (userNameUpdateSuccess || passwordUpdateSuccess) {
    //             showOverlay('部分更新成功: ' + (userNameUpdateSuccess ? '用户名' : '密码'));
    //         } else {
    //             // 这里你可以决定是否在两次更新都失败时显示消息
    //             showOverlay('用户名和密码更新失败');
    //         }
    //     }
        
    //     function showOverlay(message) {
    //         overlayContent.textContent = message;
    //         overlay.style.display = 'flex';
        
    //         setTimeout(function() {
    //             overlay.style.display = 'none';
    //         }, 2500); // 2500毫秒 = 2.5秒
    //     }


    // });
// });
