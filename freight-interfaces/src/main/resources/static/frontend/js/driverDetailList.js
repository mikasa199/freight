// 导入配置文件
import config from './config.js';


// 页面滚动加载更多数据
let currentPage = 1;
let isLoading = false; // 添加一个标志来检查是否正在加载数据
let maxPage = 0; // 最大页数初始化
let pageSize = 10;

function getCargoIdFromUrl() {
    const queryParams = new URLSearchParams(window.location.search);
    return queryParams.get('cargoId');
}


// 订单状态转换函数
function translateOrderState(stateCode) {
    const stateMap = {
        '-1': '库存未扣减',
        '0': '已接单',
        '1': '正在运输',
        '2': '运输结束',
        '3': '确认到货',
        '4': '已付款'
    };

    return stateMap[stateCode] || '未知状态';
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


function axiosDriverDetailList(cargoId,clearList = false) {
    if (isLoading) return; // 如果正在加载，则直接返回
    isLoading = true; // 设置加载标志为true


    const list = document.querySelector('.content-container ul');
    if (clearList) {
        list.innerHTML = ''; // 清空列表
    }

    axios({
        url: config.cargo_list_driverInfo,
        method: 'GET',
        params: {
            cargoId: cargoId,
            page: currentPage,
            pageSize: 10,
        }
    }).then(result => {
        console.log(result);
        const records = result.data.data.records;
        console.log(records);
        maxPage = Math.ceil(result.data.total / pageSize); // 计算最大页数
        
        const list = document.querySelector('.content-container ul');
        // 渲染records里的元素
        records.forEach(record => {
            const li = document.createElement('li');
            li.className = 'msg-container';
            li.innerHTML = `
                <div class="top-part">
                    <div class="driver-name">
                        <div class="title">司机名:</div>
                        <div class="text">${record.driverName}</div>
                    </div>
                    <div class="driver-phone">
                        <div class="title">手机号：</div>
                        <div class="text">${record.phone}</div>
                    </div>
                </div>
                <div class="middle-part">
                    <div class="createdTime">
                        <div class="title">订单创建时间:</div>
                        <div class="text">${formatDateTime(record.createdTime)}</div>
                    </div>
                    <div class="updatedTime">
                        <div class="title">订单更新时间：</div>
                        <div class="text">${formatDateTime(record.updatedTime)}</div>
                    </div>
                </div>
                <div class="bottom-part">
                    <div class="stock">
                        <div class="title">承接载货量：</div>
                        <div class="text">${record.stock}</div>
                    </div>
                    <div class="state">
                        <div class="title">订单状态</div>
                        <div class="text">${translateOrderState(record.state)}</div>
                    </div>
                </div>
                <div class="orderId">
                    #${record.orderId}
                </div>`;
            list.appendChild(li);
        });


        currentPage++; // 加载完成后，当前页码自增
        isLoading = false; // 重置加载标志

    }).catch(error => {
        console.log(error);
        isLoading = false; // 发生错误时也要重置加载标志
    });
}

// 初始化页面时加载第一页数据
axiosDriverDetailList(getCargoIdFromUrl(),true);

// 滚动事件监听器
window.addEventListener('scroll', () => {
    // 检查用户是否滚动到页面底部
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        // 检查是否已经加载了所有的页
        if (currentPage <= maxPage && !isLoading) {
            axiosDriverDetailList(getCargoIdFromUrl());
        }
    }
});
