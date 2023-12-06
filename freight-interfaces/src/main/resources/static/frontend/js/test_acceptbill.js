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
document.querySelector('.confirm-container .accept').addEventListener('click', (event) => {
    event.preventDefault();
    axios({
        url: 'http://192.168.10.101:9999/driver/order',
        method:'POST',
        data: {
            cargoId:cargoInfo.cargoId,
            driverId:"1234523",
            subStock:cargoInfo.cargo_weight
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