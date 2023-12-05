


// 测试登录


// document.querySelector('.confirm-container .user-confirm').addEventListener('click', () => {
//     const phone = document.querySelector('.user-input .user-phone').value
//     const pwd = document.querySelector('.user-input .user-password').value
    
//     axios({
//         url: 'http://192.168.10.101:9999/boss/logon',
//         method: 'POST',
//         data: {
//             phone: phone,
//             password:pwd
//         }
//     }).then(result => {
//         console.log(result);
//     })

// })

// 身份选择逻辑判断

// 初始化选中的身份为空
let selectedIdentity = "";

// 更新登录按钮状态的函数
function updateLoginButtonState() {
    const phone = document.querySelector('.user-input .user-phone').value;
    const pwd = document.querySelector('.user-input .user-password').value;
    const loginButton = document.querySelector('.confirm-container .user-confirm');

    // 检查是否已选择身份，并且手机号和密码不为空
    if (selectedIdentity && phone && pwd) {
        loginButton.disabled = false;
        loginButton.style.opacity = 1;
    } else {
        loginButton.disabled = true;
        loginButton.style.opacity = 0.3;
    }
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
    });
});

// 为手机号和密码输入框添加事件监听器
document.querySelector('.user-input .user-phone').addEventListener('input', updateLoginButtonState);
document.querySelector('.user-input .user-password').addEventListener('input', updateLoginButtonState);

// 登录按钮点击事件处理
document.querySelector('.confirm-container .user-confirm').addEventListener('click', (event) => {
    // event.preventDefault(); // 阻止链接的默认跳转行为
    const phone = document.querySelector('.user-input .user-phone').value;
    const pwd = document.querySelector('.user-input .user-password').value;
    
    // 根据选择的身份确定登录 URL
    const url = selectedIdentity === '我是老板' ? 'http://192.168.10.102:9999/boss/logon' : 'http://192.168.10.102:9999/driver/logon';

    axios({
        url: url,
        method: 'POST',
        data: {
            phone: phone,
            password: pwd
        }
    }).then(result => {
        console.log(result);

        // 清除旧的用户ID（如果存在）
        localStorage.removeItem('userId');
        
        const userId = result.data.data.bossId; // 以实际响应结构为准
        localStorage.setItem('userId', userId); // 存储用户ID

        // 登录成功后跳转到 mypage 页面
        // window.location.href = './test_mypage_v1.html';
        // 登录成功的处理逻辑
    }).catch(error => {
        console.error(error);
        // 登录失败的处理逻辑
        showOverlay("登录失败：" + error.message); // 显示错误信息
    });
});

// 显示蒙层的函数
function showOverlay(message) {
    const overlay = document.getElementById('overlay');
    overlay.style.display = 'flex';
    overlay.querySelector('.overlay-content').textContent = message;

    // 可以添加关闭蒙层的逻辑
    setTimeout(() => { overlay.style.display = 'none'; }, 3000); // 3秒后自动关闭
}
