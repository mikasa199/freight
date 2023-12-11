// 导入配置文件

import config from './config.js';


// 配置修改逻辑
document.addEventListener('DOMContentLoaded', function() {
    const originalUserNameInput = document.querySelector('.user-input input[name="name"]');
    const newUserNameInput = document.querySelector('.user-input input[name="adjust-name"]');
    const originalPasswordInput = document.querySelector('.user-input input[name="password"]');
    const newPasswordInput = document.querySelector('.user-input input[name="adjust-password"]');
    const confirmPasswordInput = document.querySelector('.user-input input[name="confirm-adjust-password"]');
    const userNameButton = document.querySelector('.adjust-btn.name');
    const passwordButton = document.querySelector('.adjust-btn.pwd');
    const submitButton = document.querySelector('.confirm-container .submit');

    // 获取原账户名
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    originalUserNameInput.value = userInfo.userName

    // 获取蒙层元素
    const overlay = document.getElementById('overlay');
    const overlayContent = document.querySelector('.overlay-content');

     // 显示蒙层及信息，并在2.5秒后自动关闭
     function showOverlay(message) {
        overlayContent.textContent = message;
        overlay.style.display = 'flex';

        // 设置定时器，2.5秒后关闭蒙层
        setTimeout(function() {
            overlay.style.display = 'none';
        }, 2500); // 2500毫秒 = 2.5秒
    }


    // 关闭蒙层
    function hideOverlay() {
        overlay.style.display = 'none';
    }


    let userNameUpdateSuccess = false;
    let passwordUpdateSuccess = false;

    // 点击蒙层任意位置关闭
    overlay.addEventListener('click', function() {
        hideOverlay();
    });



    // 使新用户名输入框可编辑
    userNameButton.addEventListener('click', function (event) {
        event.preventDefault(); // 阻止默认行为
        console.log("点击了修改用户名按钮");
        newUserNameInput.readOnly = false;
        newUserNameInput.classList.remove('readonly');
        console.log("是否只读：", newUserNameInput.readOnly);
    });

    // 使密码输入框可编辑
    passwordButton.addEventListener('click', function (event) {
        event.preventDefault(); // 阻止默认行为
        
        [originalPasswordInput, newPasswordInput, confirmPasswordInput].forEach(input => {
            input.readOnly = false;
            input.classList.remove('readonly');
        });
    });

    // 提交按钮事件处理
    submitButton.addEventListener('click', function (event) {
        
        event.preventDefault(); // 阻止默认行为

        // // 清空反馈信息数组
        // const feedbackMessages = [];


        // 获取userId
        const userInfo = JSON.parse(localStorage.getItem('userInfo'));
        const userId = userInfo ? userInfo.userId : null;
        console.log(userId);

        // 获取身份信息
        const userIdentity = userInfo ? userInfo.userIdentity : null;

        // 根据用户身份选择接口
        const nameUpdateApi = userIdentity === 'boss' ? config.account_boss_update_nameApi : config.account_driver_update_nameApi;
        const passwordUpdateApi = userIdentity === 'boss' ? config.account_boss_update_passwordApi : config.account_driver_update_passwordApi;

        // 检查用户名是否被更改
        if (!newUserNameInput.readOnly) {
            const newUserName = newUserNameInput.value;
            // 依据身份构建提交用户名的数据

           
        const dataForName = userIdentity === 'driver' ? {
            driverId: userId,
            driverName: newUserName
        } : {
            bossId: userId,
            bossName: newUserName
        }
            // 提交新用户名
            axios({
                url: nameUpdateApi,
                method: 'POST',
                data: dataForName
            }).then(result => {
                console.log(result);
                userNameUpdateSuccess = true;
                checkUpdatesAndShowMessage();
            }).catch(error => {
                console.log(error);
                userNameUpdateSuccess = false;
                checkUpdatesAndShowMessage();
            });
        }

        // 检查密码是否被更改
        if (!originalPasswordInput.readOnly) {
            const beforePassword = originalPasswordInput.value;
            const afterPassword = newPasswordInput.value;
            const confirmPassword = confirmPasswordInput.value;

            if(afterPassword === confirmPassword) {
                // 依据身份判定构建提交密码的数据

                const dataForPassword =  userIdentity === 'driver' ? {
                    driverId: userId,
                    beforePassword: beforePassword,
                    afterPassword: afterPassword
                } : {
                    bossId: userId,
                    beforePassword: beforePassword,
                    afterPassword: afterPassword
                };
                // 提交新密码
                axios({
                    url: passwordUpdateApi,
                    method: 'POST',
                    data: dataForPassword
                }).then(result => {

                    console.log(result);
                    passwordUpdateSuccess = true;
                    checkUpdatesAndShowMessage();


                }).catch(error => {
                    console.log(error);
                    passwordUpdateSuccess = false;
                    checkUpdatesAndShowMessage();
                });
            } else {
                alert("新密码和确认密码不匹配！");
            }
        }

        function checkUpdatesAndShowMessage() {
            if (userNameUpdateSuccess && passwordUpdateSuccess) {
                showOverlay('用户名和密码更新成功');
            } else if (userNameUpdateSuccess || passwordUpdateSuccess) {
                showOverlay('部分更新成功: ' + (userNameUpdateSuccess ? '用户名' : '密码'));
            } else {
                // 这里你可以决定是否在两次更新都失败时显示消息
                showOverlay('用户名和密码更新失败');
            }
        }
        
        function showOverlay(message) {
            overlayContent.textContent = message;
            overlay.style.display = 'flex';
        
            setTimeout(function() {
                overlay.style.display = 'none';
            }, 2500); // 2500毫秒 = 2.5秒
        }


    });
});
