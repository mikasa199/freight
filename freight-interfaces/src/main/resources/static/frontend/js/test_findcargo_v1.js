

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



function AddCargoInfo() {
    const start_address = document.querySelector('.information-container .address .text_start').innerText
    const end_address = document.querySelector('.information-container .address .text_end ').innerText
    const time = document.querySelector('.information-container .goods .date span').innerText

    const goods_info = document.querySelector('.information-container .goods .goods-kind .goods-info').innerText

    console.log(start_address);
    console.log(end_address);

    // 使用正则表达式匹配日期和时间
    let dateTimeRegex = /\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}/;
    let dateTimeMatch = time.match(dateTimeRegex);
    console.log(dateTimeMatch[0]);

    // 使用正则表达式提取货物类型和价格
    let GoodsInforegex = /(\D+)(\d+)元\/吨/;

// 使用正则表达式匹配文本
    let GoodsInfomatches = goods_info.match(GoodsInforegex);
    console.log(GoodsInfomatches[1]);
    console.log(GoodsInfomatches[2]);

    // 提交数据
    axios({
        url: "192.168.10.101:",
        data: {
            beginLocation: start_address,
            endLocation: end_address,
            beginTime: dateTimeMatch[0],
            cargoName: GoodsInfomatches[1],
            value: GoodsInfomatches[2]
        }
    
    }).then(result => {
        console.log(result);
    }).catch(error => {
        console.log(error);
    })

    

}

// 加载页面就进行上传
// AddCargoInfo()


// 从服务器读取数据加载货物信息进行页面渲染
function GetCargoInfo() {
    axios({
        url: 'http://192.168.10.101:9999/driver/cargo/list',
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
                            <span>
                                <i class="iconfont icon-xiangshang"></i>
                                <div class="text_start">
                                    ${items.beginLocation}
                                </div>
                            </span>
                            <span>
                                <i class="iconfont icon-xiangxia"></i>
                                <div class="text_end">
                                ${items.endLocation}
                                </div>

                            </span>
                        </div>
                        <!-- 货物信息 -->
                        <div class="goods">
                            <div class="date">
                                <i class="iconfont icon-shijian"></i>
                                <span>发货: 2023-11-06 16:00:48</span>
                            </div>
                            <div class="goods-kind">
                                <i class="iconfont icon-huowudui"></i>
                                <div class="goods-info">${items.cargoName}${items.value}元/吨</div>
                            </div>
                        </div>
                        <!-- 距离信息 -->
                        <div class="distance">
                            <i class="iconfont icon-ditu_dingwei_o"></i>
                            <div class="time-info">总里程185.9公里，距离装车地36.0公里</div>
                        </div>

                        <!-- 二维码 -->
                        <a href="#" class="info-logo iconfont icon-erweima"></a>
                    </div>
                </li>
            `
        }).join('')

        console.log(htmlStr);
        document.querySelector('.content-container ul').innerHTML = htmlStr
    })
}


GetCargoInfo() 