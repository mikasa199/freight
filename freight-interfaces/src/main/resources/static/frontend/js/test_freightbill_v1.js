// 导入配置文件
import config from './config.js';

document.addEventListener('DOMContentLoaded', function () {
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    const userIdentity = userInfo && userInfo.userIdentity;
    // let userIdentity = 'driver'
    if (userIdentity === 'boss') {
        // 执行boss相关的逻辑
        loadPageForBoss();

        // 渲染订单数据
function renderOrders(ordersData) {
    const container = document.querySelector('.cargo-information');


    const htmlStr = ordersData.map(items => {
        return `
        <li>
        <!-- 老板端视角元素 -->
        <div class="boss-perspective-information-container">
            <div class="ex-container">
                <div class="info">
                    <div class="cargoName">${items.cargoName}</div>
                    <div class="subStock">剩余货量:${items.stock}</div>
                </div>
                
                <div class="btn-area">
                    <button class="btn-cargo" data-cargoid="${BigInt(items.cargoId).toString()}">详细货单信息</button>
                    <button class="btn-driver" data-cargoid="${BigInt(items.cargoId).toString()}">接单司机信息</button>
                </div>
            </div>

        </div>
        
        </li>`
    }).join('')
        console.log(htmlStr);
        document.querySelector('.content-container ul').innerHTML = htmlStr

}


// 设置事件委托实现页面跳转，显示详细信息

document.querySelector('.cargo-information').addEventListener('click', function (event) {
    if (event.target && event.target.matches('.btn-cargo')) {
        const cargoId = event.target.getAttribute('data-cargoid');
        // 执行跳转或发起请求
        // window.location.href = `./freightBill_detailInfo.html?cargoId=${cargoId}`;
        window.location.href = `./freightBill_detailInfo.html?cargoId=${cargoId}`;
        
    }
});

        
// 点击查看详细司机接单订单情况
document.querySelector('.cargo-information').addEventListener('click', function (event) {
    if (event.target && event.target.matches('.btn-driver')) {
        const cargoId = event.target.getAttribute('data-cargoid');
        axios({
            url: config.cargo_list_driverInfo,
            method: 'GET',
            params: {
                cargoId: cargoId,
                page: 1,
                pageSize:10,
            }
        }).then(result => {
            console.log(result);
        }).catch(error => {
            console.log(error);
        })
        
    }
});



// 页面滚动加载更多数据
let currentPage = 1;
let isLoading = false; // 添加一个标志来检查是否正在加载数据
let maxPage = 0; // 最大页数初始化


// 加载更多数据的函数
function loadMoreData(page) {
    // if (isLoading) return; // 如果正在加载，则直接返回，避免重复加载
    // isLoading = true; // 开始加载数据，设置标志为 true


    // 获取用户信息
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    const userIdentity = userInfo.userIdentity;
    console.log(userIdentity);
    const userId = userInfo.userId;
    console.log(userId);

    // 设置 API URL 和参数
    let apiUrl;
    let params;
    if (userIdentity === 'boss') {
        apiUrl = config.my_cargo_boss_searchApi; // 假设这是 boss 的 API URL
        params = {
            bossId: userId,
            page: page,
            pageSize:10,
        };
    } else if(userIdentity === 'driver'){
        apiUrl = config.my_order_driver_searchApi; // 假设这是 driver 的 API URL
        params = {
            driverId: userId,
            page: page,
            pageSize: 10,
        };
    }

    axios.get(apiUrl, { params })
        .then(result => {
            console.log(result);
            // const orders = result.data.data.records;
            const ordersData = result.data.data.records
            console.log(ordersData);
            console.log(`BossId:${BigInt(ordersData[0].bossId).toString()}`);
            console.log(`CargoId:${BigInt(ordersData[0].cargoId).toString()}`);
            if (userIdentity === 'driver') {
                console.log(`DriverId:${BigInt(ordersData[0].driverId).toString()}`);
            }

            maxPage = result.data.data.total; // 更新最大页数
        
        // // 将订单数据转换为对象并存储
        // const ordersData = orders.map(order => ({
        //     orderId: order.orderId,
        //     cargoId: order.cargoId,
        //     cargoName: order.cargoName,
        //     beginLocation: order.beginLocation,
        //     endLocation: order.endLocation,
        //     distance: order.distance,
        //     beginTime: order.beginTime,
        //     endTime: order.endTime,
        //     stock: order.stock,
        //     info: order.info,
        //     bossId: order.bossId,
        //     driverId: order.driverId,
        //     cargoWeight: order.cargoWeight,
        //     value: order.value,
        //     state: order.state
        // }));
    
        // // 存储到 localStorage
        // localStorage.setItem('orders', JSON.stringify(ordersData));

        
            renderOrders(ordersData); // 渲染新订单
            isLoading = false; // 加载完成，设置标志为 false
        })


        .catch(error => {
            console.error('加载更多数据时出错:', error);
            isLoading = false; // 出错时也要设置标志为 false
        });
}

// 页面滚动加载更多数据
window.addEventListener('scroll', () => {



    // 检查是否已到达最大页数
    if (currentPage >= maxPage) {
        return; // 如果达到最大页数，不再请求数据
    }

    // 检查页面是否已滚动到底部
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        currentPage++; // 增加页码
        loadMoreData(currentPage); // 加载更多数据
    }
});

// 初始加载
loadMoreData(currentPage);



    } else if (userIdentity === 'driver') {
        // 执行driver相关的逻辑
        loadPageForDriver();
    }
});



function loadPageForBoss() {
    // 显示boss视角的元素，隐藏driver视角的元素
    document.querySelectorAll('.information-container').forEach(el => el.style.display = 'none');
    document.querySelectorAll('.boss-perspective-information-container').forEach(el => el.style.display = 'block');
    document.querySelector('.container').style.backgroundColor = '#fff';
}

function loadPageForDriver() {
    // 显示driver视角的元素，隐藏boss视角的元素
    document.querySelectorAll('.information-container').forEach(el => el.style.display = 'block');
    document.querySelectorAll('.boss-perspective-information-container').forEach(el => el.style.display = 'none');
    document.querySelector('.container').style.backgroundColor = 'lightgrey';
}











