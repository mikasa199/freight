


// 测试登录


document.querySelector('.confirm-container .user-confirm').addEventListener('click', () => {
    const phone = document.querySelector('.user-input .user-phone').value
    const pwd = document.querySelector('.user-input .user-password').value
    
    axios({
        url: 'http://192.168.10.102:9999/boss/logon',
        method: 'POST',
        data: {
            phone: phone,
            password:pwd
        }
    }).then(result => {
        console.log(result);
    })

})