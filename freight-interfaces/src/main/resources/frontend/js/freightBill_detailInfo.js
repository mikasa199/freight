// 导入配置文件
import config from './config.js';

// 导入时间转换函数
import { goBack,dateFormat } from './utilityFunction.js';


// 绑定返回元素点击事件
document.querySelector('.top-container .return').addEventListener('click', goBack)



function getCargoIdFromUrl() {
    const queryParams = new URLSearchParams(window.location.search);
    return queryParams.get('cargoId');
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
        document.querySelector('textarea[name="date-start"]').value = dateFormat(data.beginTime);
        document.querySelector('textarea[name="date-end"]').value = dateFormat(data.endTime);
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
