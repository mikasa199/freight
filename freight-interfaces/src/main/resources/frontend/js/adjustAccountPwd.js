// 导入配置文件

import config from './config.js';

// 导入返回函数
import { goBack } from './utilityFunction.js'


// 绑定返回元素点击事件
document.querySelector('.top-container .return').addEventListener('click', goBack)


document.addEventListener('DOMContentLoaded', function () {
    const originalPasswordInput = document.querySelector('input[name="password"]');
    const newPasswordInput = document.querySelector('input[name="adjust-password"]');
    const confirmPasswordInput = document.querySelector('input[name="confirm-adjust-password"]');
    const submitButton = document.querySelector('.submit');
    const overlay = document.getElementById('overlay');
    const overlayContent = overlay.querySelector('.overlay-content');

    // 判断密码逻辑
    function validatePassword(password) {
        return /^[A-Za-z\d\W_]{8,12}$/.test(password);
    }
    

    function checkInputs() {
        // console.log("checkInputs called"); // 确认函数被调用
        const originalPassword = originalPasswordInput.value.trim();
        const newPassword = newPasswordInput.value.trim();
        const confirmPassword = confirmPasswordInput.value.trim();

        // console.log("Original:", originalPassword, "New:", newPassword, "Confirm:", confirmPassword); // 输出当前值
        if (originalPassword && newPassword && confirmPassword && validatePassword(newPassword)) {
            // console.log("All conditions met, enabling submit button"); // 确认所有条件满足
            submitButton.disabled = false;
            submitButton.classList.add('active');
        } else {
            // console.log("Conditions not met, disabling submit button"); // 条件不满足
            submitButton.disabled = true;
            submitButton.classList.remove('active');
        }
    }

    originalPasswordInput.addEventListener('input', checkInputs);
    newPasswordInput.addEventListener('input', checkInputs);
    confirmPasswordInput.addEventListener('input', checkInputs);

    submitButton.addEventListener('click', function (event) {
        event.preventDefault();

        const newPassword = newPasswordInput.value.trim();
        const confirmPassword = confirmPasswordInput.value.trim();

        if (newPassword !== confirmPassword) {
            showOverlay('新密码和确认密码不匹配！');
            return;
        }

        const userInfo = JSON.parse(localStorage.getItem('userInfo'));
        if (!userInfo) {
            showOverlay('未找到用户信息');
            return;
        }

        const { userId, userIdentity } = userInfo;
        const passwordUpdateApi = userIdentity === 'boss' ? config.account_boss_update_passwordApi : config.account_driver_update_passwordApi;
        const dataForPassword = userIdentity === 'driver' ? {
            driverId: userId,
            beforePassword: originalPasswordInput.value,
            afterPassword: newPassword
        } : {
            bossId: userId,
            beforePassword: originalPasswordInput.value,
            afterPassword: newPassword
        };

        axios({
            url: passwordUpdateApi,
            method: 'POST',
            data: dataForPassword
        }).then(result => {
            if (result.data.code === 1) {
                showOverlay(result.data.data)
                window.location.href = './test_mypage_v1.html'
            } else {
                showOverlay(result.data.msg);
            }
        }).catch(error => {
            console.log(error.message);
            showOverlay('更新密码出错: ' + error.message);
        });
    });

    function showOverlay(message) {
        overlayContent.textContent = message;
        overlay.style.display = 'flex';
        setTimeout(() => {
            overlay.style.display = 'none';
        }, 3000); // 3秒后隐藏蒙层
    }
});
