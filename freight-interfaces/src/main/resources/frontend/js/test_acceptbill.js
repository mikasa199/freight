// 导入配置文件

import config from './config.js';

// 导入返回函数
import { goBack }  from './utilityFunction.js'


// 绑定返回元素点击事件
document.querySelector('.top-container .return').addEventListener('click', goBack)




// 显示蒙层的函数
function showOverlay(message) {
    const overlay = document.getElementById('overlay');
    overlay.style.display = 'flex';
    overlay.querySelector('.overlay-content').textContent = message;

    // 可以添加关闭蒙层的逻辑
    setTimeout(() => { overlay.style.display = 'none'; }, 3000); // 3秒后自动关闭
}



// 从localstorage读取货单数据

document.addEventListener('DOMContentLoaded', function() {
    try {
        const cargoInfo = JSON.parse(localStorage.getItem('cargoInfo'));
        console.log(cargoInfo);
        
        // 检查 cargoInfo 是否存在
        if (cargoInfo) {
            // 填充表单数据
            document.querySelector('.cargo-input [name="cargo-name"]').value = cargoInfo.cargo_kind;
            // 如果有重量信息，应该更新对应的输入框
            document.querySelector('.cargo-input [name="cargo-stock"]').value = cargoInfo.cargo_stock; 
            document.querySelector('.cargo-input [name="address-start"]').value = cargoInfo.start_address.trim();
            document.querySelector('.cargo-input [name="address-end"]').value = cargoInfo.end_address.trim();
            document.querySelector('.cargo-input [name="date-start"]').value = cargoInfo.start_date;
            document.querySelector('.cargo-input [name="date-end"]').value = cargoInfo.end_date;
             
        }
    } catch (error) {
        console.error('解析货物信息时发生错误', error);
    }
});


// 初始化日期和时间选择器
$('input[name="driver-cargo-date-start"]').pickadate({
    format: 'yyyy-mm-dd', // 或您想要的任何格式
    onSet: updateAcceptButtonStatus // 当日期改变时调用检查函数
});

$('input[name="driver-cargo-time-start"]').pickatime({
    format: 'HH:i', // 24小时制
    onSet: updateAcceptButtonStatus // 当时间改变时调用检查函数
});

// 为承接重量输入框添加事件监听器
document.querySelector('input[name="driver-cargo-weight"]').addEventListener('input', updateAcceptButtonStatus);

// 检查输入并更新按钮状态的函数
function updateAcceptButtonStatus() {
    const weightInput = document.querySelector('input[name="driver-cargo-weight"]').value;
    const startDate = $('input[name="driver-cargo-date-start"]').val();
    const startTime = $('input[name="driver-cargo-time-start"]').val();
    const acceptButton = document.querySelector('.confirm-container .accept');

    // 检查是否所有字段都已填写
    if (weightInput && startDate && startTime) {
        acceptButton.disabled = false;
        acceptButton.classList.add('active');
    } else {
        acceptButton.disabled = true;
        acceptButton.classList.remove('active');
    }
}






const cargoInfo = JSON.parse(localStorage.getItem('cargoInfo'));
const userId = JSON.parse(localStorage.getItem('userInfo')).userId;


document.querySelector('.confirm-container .accept').addEventListener('click', (event) => {
    event.preventDefault();

    const subStock = document.querySelector('.driver-input .items input[name="driver-cargo-weight"]').value 
    const beginDate = document.querySelector('.driver-input input[name="driver-cargo-date-start"]').value 
    const beginTime = document.querySelector('.driver-input input[name="driver-cargo-time-start"]').value 

    const beginDateTime = beginDate + 'T' + beginTime + ':00';
    console.log(beginDateTime);
    console.log(userId);
    console.log(subStock);
    axios({
        url: config.acceptBillApi,
        method:'POST',
        data: {
            cargoId:cargoInfo.cargoId,
            driverId:userId,
            subStock:subStock,   //用户自己填的载货重量
            beginTime:beginDateTime
        }
    }).then(result => {
        console.log(result);
                if (result.data.code === 1) {
                    showOverlay(result.data.data);
                    window.location.href = './test_freightbill_v1.html';
                } else{
                    showOverlay(result.data.data);
                }
                
        
    }).catch(error => {
        console.log(error);
        showOverlay(error.message)
    });
    
})



// document.querySelector('.confirm-container .accept').addEventListener('click', () => {
//     // const phone = document.querySelector('.user-input .user-phone').value
//     // const pwd = document.querySelector('.user-input .user-password').value
    
//     axios({
//         url: 'http://192.168.10.101:9999/driver/order',
//         method: 'POST',
//         data: {
            
//             cargoId: 1232341,
//             driverId: 12333123,
//             subStock: 160,
//         }
//     }).then(result => {
//         console.log(result);
//     })

// })