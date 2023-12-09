// 导入配置文件

import config from './config.js';




// 控制弹出菜单逻辑开始
const temp_conditionList = document.querySelector('.condition-list')
const temp_conditionList_ul = document.querySelector('.condition-list ul')
const temp_conditionName = document.querySelector('.list-items .list-title .text')

document.querySelector('.top-container .list-items .list-title').addEventListener('click', () => {
    temp_conditionList.style.display = 'block'
})

temp_conditionList_ul.addEventListener('click', function (event) {
    if (event.target.tagName === 'LI') {
        temp_conditionName.innerText = event.target.innerText
        // 选择完排序类型后关闭
        temp_conditionList.style.display = 'none'
    }
})

// 控制弹出菜单结束





// 从服务器读取数据加载货物信息进行页面渲染
function GetCargoInfo() {
    axios({
        url: config.cargoListApi,
        method:'GET',
         params: {
             page: 1,
             pageSize:10,
         }
    }).then(result => {
        console.log(result);
        const CargoInfoList = result.data.data.records
        console.log(CargoInfoList);

        // 渲染数据
        const htmlStr = CargoInfoList.map(items => {
            return `
            <li>
                    <div class="information-container">
                        <!-- 地址信息 -->
                        <div class="address">
                            <i class="iconfont icon-xiangshang"></i>
                            <div class="text_start">
                                ${items.beginLocation}
                            </div>
                           
                            <i class="iconfont icon-xiangxia"></i>
                            <div class="text_end">
                                 ${items.endLocation}
                            </div> 
                        </div>

                        <!-- 货物信息 -->
                        <div class="goods">
                            <div class="date">
                                <i class="iconfont icon-shijian"></i>
                                <span>发货: </span>
                                <div class="date_text">${items.beginTime}</div>
                            </div>
                            <div class="goods-kind">
                                <i class="iconfont icon-huowudui"></i>
                                <div class="goods-info">
                                    <div class="kind">${items.cargoName}</div>
                                    <div class="price">${items.value}元/吨</div>
                                    <div class="weight">${items.cargoWeight}</div>
                                </div>
                            </div>
                        </div>
                        <!-- 距离信息 -->
                        <div class="distance">
                            <i class="iconfont icon-ditu_dingwei_o"></i>
                            <div class="distance-info"></div>
                        </div>



                        <!-- 接单链接 -->
                        <a href="javascript:void(0)" class="accept-bill">接单</a>

                        <!-- 司机ID和货物ID -->
                        <div class="id-Info">
                            <div class="driverId">${items.bossId}</div>
                            <div class="cargoId">${items.cargoId}</div>
                        </div>
                    </div>
                </li>
            `
        }).join('')

        console.log(htmlStr);
        document.querySelector('.content-container ul').innerHTML = htmlStr
        console.log(document.querySelector('.content-container ul').innerHTML);
    })
}


// GetCargoInfo()



// 调用百度地图API，计算起点和终点距离
// 确保DOM完全加载后执行
// document.addEventListener('DOMContentLoaded', function () {
//     calculateAndDisplayDistance();
// });

// function calculateAndDisplayDistance() {
//     var map = new BMap.Map("l-map");  // 创建Map实例，虽然这里不显示地图，但可能需要初始化

//     // 获取起始地址和目的地址
//     const start_address = document.querySelector('.information-container .address .text_start').innerText;
//     const end_address = document.querySelector('.information-container .address .text_end').innerText;

//     // 创建地址解析器实例
//     var geoCoder = new BMap.Geocoder();

//     // 解析起始地址
//     geoCoder.getPoint(start_address, function(startPoint){
//         if (startPoint) {
//             // 解析目的地址
//             geoCoder.getPoint(end_address, function(endPoint){
//                 if (endPoint) {
//                     // 计算起始地址和目的地址之间的距离
//                     var distance = (map.getDistance(startPoint, endPoint) / 1000).toFixed(2); // 转换为公里并保留两位小数

//                     // 显示距离
//                     displayDistance(distance);
//                 } else {
//                     alert("目的地址没有解析到结果!");
//                 }
//             });
//         } else {
//             alert("起始地址没有解析到结果!");
//         }
//     });
// }

// function displayDistance(distance) {
//     var distanceInfo = document.querySelector('.information-container .distance .distance-info');
//     if (distanceInfo) {
//         distanceInfo.innerText = `总里程${distance}公里`;
//     }
// }


// 页面顶部搜索框逻辑

// 获取搜索框的引用
const searchInput = document.querySelector('.search-items .text input[type="text"]');

// 为搜索框添加键盘事件监听器
searchInput.addEventListener('keyup', function(event) {
    if (event.keyCode === 13) {
        // 用户按下Enter键，执行搜索
        const cargoName = searchInput.value.trim(); // 获取用户输入并去除前后空格
        fetchCargoData(cargoName, 1, 10); // 调用函数执行搜索，假设每页10条数据，从第1页开始
    }
});

// 获取数据的函数
function fetchCargoData(cargoName, page, pageSize) {
    axios({
        url: config.cargoListSortApi, 
        method: 'GET',
        params: {
            cargoName: cargoName, // 用户输入的货物名称
            page: page,
            pageSize: pageSize
        }
    }).then(response => {
        console.log(response);
        const cargoInfoList = response.data.data.records;
        // 可以在这里调用渲染函数来显示搜索结果
        renderCargoInfo(cargoInfoList);
    }).catch(error => {
        console.error(error);
        // 处理错误情况
    });
}








// 实现点击的订单数据保存到localstorage，然后跳转的页面进行读取

// 绑定点击事件到包含所有 li 的 ul 元素
document.querySelector('.content-container ul').addEventListener('click', function(event) {
    // 检查被点击的元素是否是接单按钮或其子元素
    if (event.target.closest('.accept-bill')) {
        // 清空LocalStorage中之前的cargoInfo
        localStorage.removeItem('cargoInfo');

        // 获取当前按钮所在的 li 元素
        const listItem = event.target.closest('li');

        const cargoInfo = {
            driverId: listItem.querySelector('.driverId').textContent,
            cargoId: listItem.querySelector('.cargoId').textContent,
            start_address: listItem.querySelector('.address .text_start').innerText,
            end_address: listItem.querySelector('.address .text_end').innerText,
            start_date: listItem.querySelector('.goods .date .date_text').textContent,
            cargo_kind: listItem.querySelector('.goods-kind .kind').textContent,
            cargo_price: listItem.querySelector('.goods-kind .price').textContent,
            cargo_weight: listItem.querySelector('.goods-kind .weight').textContent
        };

        // 存储数据到 LocalStorage
        localStorage.setItem('cargoInfo', JSON.stringify(cargoInfo));

        // 数据存储后进行页面跳转
        window.location.href = './test_acceptbill.html';
    }
});




// 跟踪当前页码和当前排序代码
let currentPage = 1;
let currentSortCode = ''; // 初始排序代码为空
let maxPage = 0; // 最大页数初始化

// 检测是否正在加载数据
let isLoading = false;

// 给 condition-list 添加点击事件监听（事件委托）
document.querySelector('.condition-list').addEventListener('click', function(event) {
    if (event.target.tagName === 'LI') {
        currentSortCode = event.target.dataset.code; // 更新当前排序代码
        currentPage = 1; // 重置为第一页
        fetchSortedCargoInfo(currentSortCode);      // 请求排序后的数据
    }
});

// 请求排序后的数据
function fetchSortedCargoInfo(sortCode) {
    isLoading = true; // 开始加载数据

    axios({
        url:config.cargoListSortApi,
        method: 'GET',
        params: {
            page: currentPage,
            pageSize: 10,
            code: +sortCode // 使用排序代码
        }
    }).then(response => {
        console.log(response);
        const cargoInfoList = response.data.data.records;
        maxPage = response.data.data.total; // 更新最大页数


        if (currentPage === 1) {
            renderCargoInfo(cargoInfoList); // 渲染新数据
        } else {
            appendDataToPage(cargoInfoList); // 添加到现有数据
        }
        isLoading = false; // 完成数据加载
    }).catch(error => {
        console.error(error);
        isLoading = false;
    });
}

// 页面加载时主动调用一次 fetchSortedCargoInfo 函数
document.addEventListener('DOMContentLoaded', function() {
    fetchSortedCargoInfo(''); // 空字符串代表综合排序
});



// 将数据渲染到页面上
function renderCargoInfo(cargoList) {
    const htmlStr = cargoList.map(items => {
        return `
        <li>
        <div class="information-container">
            <!-- 地址信息 -->
            <div class="address">
                <i class="iconfont icon-xiangshang"></i>
                <div class="text_start">
                    ${items.beginLocation}
                </div>
               
                <i class="iconfont icon-xiangxia"></i>
                <div class="text_end">
                     ${items.endLocation}
                </div> 
            </div>

            <!-- 货物信息 -->
            <div class="goods">
                <div class="date">
                    <i class="iconfont icon-shijian"></i>
                    <span>发货: </span>
                    <div class="date_text">${items.beginTime}</div>
                </div>
                <div class="goods-kind">
                    <i class="iconfont icon-huowudui"></i>
                    <div class="goods-info">
                        <div class="kind">${items.cargoName}</div>
                        <div class="price">${items.value}元/吨</div>
                        
                    </div>
                </div>
            </div>
            <!-- 距离信息 -->
            <div class="distance">
                <i class="iconfont icon-ditu_dingwei_o"></i>
                <div class="distance-info">总里程${items.distance}公里</div>
            </div>



            <!-- 接单链接 -->
            <a href="javascript:void(0)" class="accept-bill">接单</a>

            <!-- 司机ID和货物ID -->
            <div class="id-Info">
                <div class="driverId">${items.bossId}</div>
                <div class="cargoId">${items.cargoId}</div>
            </div>
        </div>
    </li>
        `;
    }).join('');

    document.querySelector('.content-container ul').innerHTML = htmlStr;
    
    // 滚动到页面顶部
    window.scrollTo(0, 0);
}

// 添加新的数据到页面
function appendDataToPage(data) {
    const htmlStr = data.map(items => {
        return `
        <li>
        <div class="information-container">
            <!-- 地址信息 -->
            <div class="address">
                <i class="iconfont icon-xiangshang"></i>
                <div class="text_start">
                    ${items.beginLocation}
                </div>
               
                <i class="iconfont icon-xiangxia"></i>
                <div class="text_end">
                     ${items.endLocation}
                </div> 
            </div>

            <!-- 货物信息 -->
            <div class="goods">
                <div class="date">
                    <i class="iconfont icon-shijian"></i>
                    <span>发货: </span>
                    <div class="date_text">${items.beginTime}</div>
                </div>
                <div class="goods-kind">
                    <i class="iconfont icon-huowudui"></i>
                    <div class="goods-info">
                        <div class="kind">${items.cargoName}</div>
                        <div class="price">${items.value}元/吨</div>
                        
                    </div>
                </div>
            </div>
            <!-- 距离信息 -->
            <div class="distance">
                <i class="iconfont icon-ditu_dingwei_o"></i>
                <div class="distance-info">总里程${items.distance}公里</div>
            </div>



            <!-- 接单链接 -->
            <a href="javascript:void(0)" class="accept-bill">接单</a>

            <!-- 司机ID和货物ID -->
            <div class="id-Info">
                <div class="driverId">${items.bossId}</div>
                <div class="cargoId">${items.cargoId}</div>
            </div>
        </div>
    </li>
        `;
    }).join('');
    document.querySelector('.content-container ul').innerHTML += htmlStr;
}

// 监听滚动事件以加载更多数据
window.addEventListener('scroll', () => {

    // 检查是否已到达最大页数
    if (currentPage >= maxPage) {
        return; // 如果达到最大页数，不再请求数据
    }

    if (window.innerHeight + window.scrollY >= document.body.offsetHeight && !isLoading) {
        isLoading = true;
        currentPage++;
        fetchSortedCargoInfo(currentSortCode); // 请求更多数据，使用当前的排序代码
    }
});





// // 测试分页查询用模拟数据

// function generateMockData(page, pageSize) {
//     const mockData = [];
//     for (let i = 1; i <= pageSize; i++) {
//         mockData.push({
//             beginLocation: `起始位置 ${page}-${i}`,
//             endLocation: `目的地 ${page}-${i}`,
//             beginTime: `2023-12-0${page} 08:00:00`,
//             cargoName: `货物 ${page}-${i}`,
//             value: `${100 + i} 元/吨`,
//             cargoWeight: `${1000 + i * 10} 吨`,
//             bossId: `司机ID ${1000 + page * 10 + i}`,
//             cargoId: `货物ID ${2000 + page * 10 + i}`
//         });
//     }
//     return mockData;
// }

// // 模拟服务器获取数据
// function GetCargoInfo(page = 1, pageSize = 10) {
//     // 模拟从服务器获取数据
//     const result = {
//         data: {
//             data: {
//                 records: generateMockData(page, pageSize)
//             }
//         }
//     };

//     // 使用模拟数据渲染页面
//     const CargoInfoList = result.data.data.records;

//     const htmlStr = CargoInfoList.map(items => {
//         // 转换items为HTML字符串（您之前的函数）
//         return `
//         <li>
//         <div class="information-container">
//             <!-- 地址信息 -->
//             <div class="address">
//                 <i class="iconfont icon-xiangshang"></i>
//                 <div class="text_start">
//                     ${items.beginLocation}
//                 </div>
               
//                 <i class="iconfont icon-xiangxia"></i>
//                 <div class="text_end">
//                      ${items.endLocation}
//                 </div> 
//             </div>

//             <!-- 货物信息 -->
//             <div class="goods">
//                 <div class="date">
//                     <i class="iconfont icon-shijian"></i>
//                     <span>发货: </span>
//                     <div class="date_text">${items.beginTime}</div>
//                 </div>
//                 <div class="goods-kind">
//                     <i class="iconfont icon-huowudui"></i>
//                     <div class="goods-info">
//                         <div class="kind">${items.cargoName}</div>
//                         <div class="price">${items.value}元/吨</div>
//                         <div class="weight">${items.cargoWeight}</div>
//                     </div>
//                 </div>
//             </div>
//             <!-- 距离信息 -->
//             <div class="distance">
//                 <i class="iconfont icon-ditu_dingwei_o"></i>
//                 <div class="distance-info"></div>
//             </div>



//             <!-- 接单链接 -->
//             <a href="javascript:void(0)" class="accept-bill">接单</a>

//             <!-- 司机ID和货物ID -->
//             <div class="id-Info">
//                 <div class="driverId">${items.bossId}</div>
//                 <div class="cargoId">${items.cargoId}</div>
//             </div>
//         </div>
//     </li>
//         `;
//     }).join('');

//     document.querySelector('.content-container ul').innerHTML += htmlStr;
// }

// // 页面加载时调用一次以加载第一页数据
// GetCargoInfo();

// // 滚动到底部时加载更多数据
// window.addEventListener('scroll', () => {
//     if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
//         // 增加当前页码
//         currentPage++;
//         // 加载下一页数据
//         GetCargoInfo(currentPage);
//     }
// });
