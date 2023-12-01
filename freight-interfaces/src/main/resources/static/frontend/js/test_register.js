// 获取验证码函数


let identity = null; // 将 identity 声明在更广泛的作用域

    
document.querySelector('.user-input .get-code').addEventListener('click', (event) => {
    event.preventDefault();
    
    // 在点击事件中获取输入框的值
    const phone = document.querySelector('.user-input .user-phone').value;
    console.log(phone);

    axios({
        url: 'http://192.168.10.102:9999/driver/sendMsg',
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

    if (identity === 'boss') {
        axios({
            url: 'http://192.168.10.102:9999/boss/login',
            method: 'POST',
            data: {
                phone: phone,
                code: code,
                password: pwd,
            }
        }).then(result => {
            console.log(result);
        });
    } else if (identity === 'driver') {
        axios({
            url: 'http://192.168.10.102:9999/driver/login',
            method: 'POST',
            data: {
                phone: phone,
                code: code,
                password: pwd,
            }
        }).then(result => {
            console.log(result);
        });
    }
});



// document.querySelector('.confirm-container .user-code').addEventListener('click', () => {
//     const phone = document.querySelector('.container .user-input .user-phone').value
//     console.log(phone);
//     axios({
//         url: 'http://192.168.10.101:9999/driver/sendMsg',
//         method: 'GET',
//         params: {
//             phone,
//         }

//     }).then(result => {
//         console.log(result);
//     })
// })