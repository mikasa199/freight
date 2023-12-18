import config from './config.js';

let selectedIdentity = "";

// 判断是否为11位手机号
function isValidPhone(phone) {
    const phoneRegex = /^\d{11}$/;
    return phoneRegex.test(phone);
}

// 为身份选择按钮添加事件监听器
document.querySelectorAll('.identity-container button').forEach(button => {
    button.addEventListener('click', function() {
        // 更新身份选择状态
        document.querySelectorAll('.identity-container button').forEach(btn => btn.classList.remove('active'));
        this.classList.add('active');
        selectedIdentity = this.textContent;

        // 更新登录按钮状态
        updateLoginButtonState();

        // 存储用户身份到 localStorage
        const userInfo = JSON.parse(localStorage.getItem('userInfo')) || {};
        userInfo.userIdentity = selectedIdentity === '我是老板' ? 'boss' : 'driver';
        localStorage.setItem('userInfo', JSON.stringify(userInfo));
    });
});




// 更新登录按钮状态的函数
function updateLoginButtonState() {
    const phone = document.querySelector('.user-input .user-phone').value;
    const code = document.querySelector('.user-input .verification-code').value;
    const loginButton = document.querySelector('.confirm-container .user-confirm');

    // 检查是否已选择身份，手机号和验证码不为空
    loginButton.disabled = !(selectedIdentity && isValidPhone(phone) && code);
    loginButton.style.opacity = loginButton.disabled ? 0.3 : 1;
}

// 身份选择逻辑
document.querySelectorAll('.identity-container button').forEach(button => {
    button.addEventListener('click', function() {
        document.querySelectorAll('.identity-container button').forEach(btn => btn.classList.remove('active'));
        this.classList.add('active');
        selectedIdentity = this.textContent;
        updateLoginButtonState();
    });
});

// 输入框事件监听
document.querySelector('.user-input .user-phone').addEventListener('input', updateLoginButtonState);
document.querySelector('.user-input .verification-code').addEventListener('input', updateLoginButtonState);

// 获取验证码按钮事件
document.querySelector('.get-code').addEventListener('click', () => {

    const phone = document.querySelector('.user-input .user-phone').value;
    const getCodeBtn = document.querySelector('.user-input .get-code')
    console.log(phone);


    if (!isValidPhone(phone)) {
        showModal('请输入有效的11位数字手机号!');
        return; // 如果手机号无效，则不执行后续代码
    }

    // 设置点击获取验证码后，60秒后再允许重新发送
    getCodeBtn.disabled = true
    getCodeBtn.style.opacity = 0.3
    let count = 60
    let intervalId = setInterval(() => {
        count--
        getCodeBtn.innerText = `重新发送(${count})`
        if (count === 0) {
            clearInterval(intervalId)
            getCodeBtn.innerText = '获取验证码'
            getCodeBtn.disabled = false
        }
    },1000)
    
    axios({
        url: config.sendMessageApi,
        method: 'GET',
        params: {
            phone: phone,
        }
    }).then(result => {
        console.log(result);
    });

});

// 登录按钮点击事件
document.querySelector('.confirm-container .user-confirm').addEventListener('click', () => {
    const phone = document.querySelector('.user-input .user-phone').value;
    const code = document.querySelector('.user-input .verification-code').value;

    // 根据选择的身份确定登录 URL
    const url = selectedIdentity === '我是老板' ? config.login_code_BossApi : config.login_code_DriverApi;
    console.log(url);


    // 从 localStorage 获取先前保存的用户身份信息
    const userInfo = JSON.parse(localStorage.getItem('userInfo')) || {};
    const previousIdentity = userInfo.userIdentity;

    axios({
        url: url,
        method: 'POST',
        data: {
            phone: phone,
            code: code
        }
    }).then(result => {
        console.log(result);

        // 清除旧的用户ID（如果存在）
        localStorage.removeItem('userInfo');
        
        // 使用 BigInt 处理可能的超长整数
        

        if (selectedIdentity === '我是老板') {
           const newUserInfo = {
                userId: BigInt(result.data.data.bossId).toString(), // 将 BigInt 转换为字符串存储
                userName: result.data.data.bossName,
                userPhone: result.data.data.phone,
                userIdentity: previousIdentity // 保留之前的身份信息
            }
            localStorage.setItem('userInfo', JSON.stringify(newUserInfo)); // 登录时存取用户资料
        } else if(selectedIdentity === '我是司机'){
           

            const newUserInfo = {
                userId: BigInt(result.data.data.driverId).toString(), // 将 BigInt 转换为字符串存储
                userName: result.data.data.driverName,
                userPhone: result.data.data.phone,
                userIdentity: previousIdentity // 保留之前的身份信息
            }

            localStorage.setItem('userInfo', JSON.stringify(newUserInfo)); // 登录时存取用户资料
        }
        
        showOverlay("登录成功");

        // 登录成功后跳转到 mypage 页面
        window.location.href = './test_mypage_v1.html';
        
        
    }).catch(error => {
        // 登录失败的处理逻辑
        console.log(error);
        showOverlay("登录失败：" + error.message);
    });
});

// 显示蒙层的函数
function showOverlay(message) {
    const overlay = document.getElementById('overlay');
    overlay.style.display = 'flex';
    overlay.querySelector('.overlay-content').textContent = message;
    setTimeout(() => { overlay.style.display = 'none'; }, 3000); // 3秒后自动关闭
}
