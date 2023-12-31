// 导入配置文件

import config from './config.js';

// 导入返回函数
import goBack from './utilityFunction.js'


// 绑定返回元素点击事件
document.querySelector('.top-container .return').addEventListener('click', goBack)

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
            document.querySelector('.cargo-input [name="cargo-weight"]').value = cargoInfo.cargo_weight; 

            document.querySelector('.cargo-input [name="address-start"]').value = cargoInfo.start_address;
            document.querySelector('.cargo-input [name="address-end"]').value = cargoInfo.end_address;
            document.querySelector('.cargo-input [name="date-start"]').value = cargoInfo.start_date;
            // date-end 的值需要您提供，这里只是示例
            // document.querySelector('.cargo-input [name="date-end"]').value = cargoInfo.end_date; 
        }
    } catch (error) {
        console.error('解析货物信息时发生错误', error);
    }
});

const cargoInfo = JSON.parse(localStorage.getItem('cargoInfo'));
const userId = JSON.parse(localStorage.getItem('userInfo')).userId;


document.querySelector('.confirm-container .accept').addEventListener('click', (event) => {
    event.preventDefault();

    const subStock = document.querySelector('.driver-input .items input[name="driver-cargo-weight"]').value 
    console.log(userId);
    console.log(subStock);
    axios({
        url: config.acceptBillApi,
        method:'POST',
        data: {
            cargoId:cargoInfo.cargoId,
            driverId:userId,
            subStock:subStock   //用户自己填的载货重量
        }
    }).then(result => {
        console.log(result);
    })
    
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