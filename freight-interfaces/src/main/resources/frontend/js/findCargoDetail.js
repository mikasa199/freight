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
            document.querySelector('.cargo-input [name="cargo-price"]').value = cargoInfo.cargo_price;
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