
// 导入配置文件

import config from './config.js';

import { goBack } from './utilityFunction.js';

// 绑定返回元素点击事件
document.querySelector('.top-container .return').addEventListener('click', goBack)
// 通用变量

let identity = null; // 将 identity 声明在更广泛的作用域


// 设置蒙层提示信息逻辑

function showModal(message, autoClose = true) {
    const modalOverlay = document.getElementById('modal-overlay');
    const modalMessage = document.getElementById('modal-message');

    modalMessage.textContent = message;
    modalOverlay.style.display = 'flex';

    if (autoClose) {
        setTimeout(() => {
            console.log('自动隐藏蒙层');
            modalOverlay.style.display = 'none';
        }, 2500);
    } else {
        console.log('蒙层将保持显示状态');
    }
}


function hideModal() {
    document.getElementById('modal-overlay').style.display = 'none';
}



// 判断是否为11位手机号
function isValidPhone(phone) {
    const phoneRegex = /^\d{11}$/;
    return phoneRegex.test(phone);
}

// 判断用户所有输入是否为空

function isInputValid() {
    const phone = document.querySelector('.user-input .user-phone').value;
    const code = document.querySelector('.user-input .verification-code').value;
    const pwd = document.querySelector('.user-input .user-password').value;

    return phone.trim().length > 0 && code.trim().length > 0 && pwd.trim().length > 0;
}


// 监听用户的输入变化并更新注册按钮的状态

document.querySelector('.user-input .user-phone').addEventListener('input', updateRegisterButtonState);
document.querySelector('.user-input .verification-code').addEventListener('input', updateRegisterButtonState);
document.querySelector('.user-input .user-password').addEventListener('input', updateRegisterButtonState);

function updateRegisterButtonState() {
    const registerButton = document.querySelector('.confirm-container .user-confirm');
    console.log(isInputValid());
    if (isInputValid()) {
        registerButton.disabled = false;
        registerButton.style.opacity = 1;
    } else {
        registerButton.disabled = true;
        registerButton.style.opacity = 0.3;
    }

   
}




    
document.querySelector('.user-input .get-code').addEventListener('click', (event) => {
    event.preventDefault();
    
    // 在点击事件中获取输入框的值
    const phone = document.querySelector('.user-input .user-phone').value;
    const getCodeBtn = document.querySelector('.user-input .get-code')

    console.log(phone);

       // 验证手机号
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


// 获取用户的身份信息

function getIdentity(callback) {
    const choice = document.querySelector('.choice-container');
    
    choice.addEventListener('click', (e) => {
        if (e.target.tagName === 'BUTTON') {
            choice.querySelectorAll('button').forEach(btn => {
                btn.classList.remove('active');
            });
            e.target.classList.add('active');

            let userChoice = e.target.textContent;
            let tempIdentity;

            if (userChoice === '我是老板') {
                tempIdentity = 'boss';
            } else if(userChoice === '我是司机'){
                tempIdentity = 'driver';
            }

            if (callback && typeof callback === 'function') {
                callback(tempIdentity);
            }
        }
    });
}

// 使用函数
getIdentity((selectedIdentity) => {
    identity = selectedIdentity; // 更新 identity 变量
    console.log(identity); // 这里会在用户做出选择后打印结果
});


// 为注册按钮添加事件监听器
document.querySelector('.confirm-container .user-confirm').addEventListener('click', () => {
    const phone = document.querySelector('.user-input .user-phone').value;  // 获取手机号
    const code = document.querySelector('.user-input .verification-code').value; // 获取验证码
    const pwd = document.querySelector('.user-input .user-password').value; // 获取密码
    const pwdRegex = /^[A-Za-z\d@$!%*?&]{8,12}$/;

    // 密码格式验证
    if (!pwdRegex.test(pwd)) {
        showModal('密码格式不正确，请确保密码是8-12位字符，可以包含数字、大小写字母和特殊符号。');
        return;
    }

    console.log('密码验证通过，准备发起注册请求');
    console.log('选择的身份:', identity);
    console.log('手机号:', phone);
    console.log('验证码:', code);
    console.log('密码:', pwd);

    // 显示蒙层提示注册中
    console.log('准备显示蒙层');
    showModal('注册中，请稍候...', false);
    console.log('蒙层显示命令已执行');
    

    // 根据选择的身份发送注册请求

    const url = identity === 'boss' ? config.register_BossApi : config.register_DriverApi;


    if (identity === null) {
        showModal('请选择身份后再进行注册！');
        return;
    }

    axios({
        url: url,
        method: 'POST',
        data: {
            phone: phone,
            code: code,
            password: pwd,
        }
    }).then(result => {

        console.log('请求成功，响应数据:', result);
        // 隐藏蒙层
        hideModal();

       

        if (result.data.code) {
            // 显示注册成功的提示信息
            showModal('注册成功！正在跳转...');
            
            // 延迟跳转到其他页面
            setTimeout(() => {

                window.location.href = './test_login_pwd.html'; // 替换为实际的页面URL

            }, 2500);
        } else {
            // 显示注册失败的提示信息
            showModal('注册失败：' + result.data.msg);
        }
    }).catch(error => {
        console.error('请求失败，错误信息:', error);
        hideModal();
        showModal('注册失败：网络错误或服务器异常');
    });
});

