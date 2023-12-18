// 导入配置文件
import config from './config.js';

function getCargoIdFromUrl() {
    const queryParams = new URLSearchParams(window.location.search);
    return queryParams.get('cargoId');
}

// 转换时间函数
function formatDateTime(isoDateTime) {
    const date = new Date(isoDateTime);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份是从 0 开始的
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
}
       


function axiosCargoDetail(cargoId) {
    
    axios({
        url:config.my_cargo_boss_searchDetailsApi,
        method: 'GET',
        params: {
            cargoId:cargoId
        }
    }).then(result =>{
        console.log(result);
        const data = result.data.data;
        // 更新页面元素
        document.querySelector('input[name="cargo-name"]').value = data.cargoName;
        document.querySelector('input[name="cargo-weight"]').value = data.cargoWeight;
        document.querySelector('textarea[name="address-start"]').value = data.beginLocation;
        document.querySelector('textarea[name="address-end"]').value = data.endLocation;
        document.querySelector('textarea[name="date-start"]').value = formatDateTime(data.beginTime);
        document.querySelector('textarea[name="date-end"]').value = formatDateTime(data.endTime);
        document.querySelector('textarea[name="date-stock"]').value = data.stock;

    }).catch(error => {
        console.log(error);
    })
}


document.addEventListener('DOMContentLoaded', function () {
    const cargoId = getCargoIdFromUrl();
    console.log(cargoId);
    if (cargoId) {
        axiosCargoDetail(cargoId);
    }
});
