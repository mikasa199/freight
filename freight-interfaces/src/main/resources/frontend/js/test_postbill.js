// 导入配置文件

import config from './config.js';

// 导入返回函数
import goBack from './utilityFunction.js'

// 绑定返回元素点击事件
document.querySelector('.top-container .return').addEventListener('click', goBack)


// 确保在引入百度地图API和Axios之后调用此脚本

// 函数：使用百度地图API解析地址
function geocodeAddress(address, callback) {
    var geocoder = new BMap.Geocoder();
    geocoder.getPoint(address, function(point) {
        if (point) {
            callback(point.lng, point.lat);
        } else {
            console.log("无法定位地址：" + address);
        }
    });
}

// 添加事件监听器到“发布”按钮
document.querySelector('.post').addEventListener('click', function(e) {
    e.preventDefault(); // 阻止按钮的默认行为

    // 获取表单数据
    var cargoName = document.querySelector('input[name="cargo-name"]').value;
    var cargoWeight = document.querySelector('input[name="cargo-weight"]').value;
    var cargoValue  = document.querySelector('input[name="cargo-value"]').value
    var startDate = document.querySelector('input[name="date-start"]').value;
    var endDate = document.querySelector('input[name="date-end"]').value;
    var cargoInfo = document.querySelector('textarea[name="cargo-Info"]').value
    // 获取用户输入的地址
    var startAddress = document.querySelector('input[name="address-start"]').value;
    var endAddress = document.querySelector('input[name="address-end"]').value;

    // 从localstorage获取老板ID
    const bossId = JSON.parse(localStorage.getItem('userInfo')).userId;
    // 定义一个函数用于发送数据到服务器
    function sendData(startPoint, endPoint) {
        axios({
            url: config.postBIllApi,
            method: 'POST',
            data: {
                cargoName: cargoName,
                cargoWeight: cargoWeight,
                beginTime: startDate,
                endTime: endDate,
                beginLocation: startPoint,
                endLocation: endPoint,
                value: cargoValue,
                info: cargoInfo,
                bossId:bossId
            }
        }).then(result => {
            console.log(result);
        }).catch(error => {
            console.log(error);
        });
    }

    // 调用函数并发送数据
    geocodeAddress(startAddress, function(lng, lat) {
        var startPoint = `${lng}, ${lat}`; // 直接创建经纬度数组
        geocodeAddress(endAddress, function(lng, lat) {
            var endPoint = `${lng}, ${lat}`; // 直接创建经纬度数组
            sendData(startPoint, endPoint);
        });
    });
});

// 显示地图模态窗口
function showMapModal(type) {
    document.getElementById('mapModal').style.display = 'flex';
    initMap(type); // 传递类型（起点或终点）
}

// 关闭地图模态窗口
function closeMapModal() {
    document.getElementById('mapModal').style.display = 'none';
}

// 初始化地图
function initMap(type) {
    var map = new BMap.Map("map-container");
    var centerPoint = new BMap.Point(116.404, 39.915);
    map.centerAndZoom(centerPoint, 15);

    map.addEventListener("click", function(e) {
        var point = new BMap.Point(e.point.lng, e.point.lat);

        var geoc = new BMap.Geocoder();
        geoc.getLocation(point, function(rs){
            var addComp = rs.addressComponents;
            var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;

            if (type === 'start') {
                document.querySelector('input[name="address-start"]').value = address;
                document.getElementById('start-point').value = e.point.lng + "," + e.point.lat;
            } else if (type === 'end') {
                document.querySelector('input[name="address-end"]').value = address;
                document.getElementById('end-point').value = e.point.lng + "," + e.point.lat;
            }

            closeMapModal();
        });
    });
}

// 绑定点击事件到起点和终点输入框
document.querySelector('input[name="address-start"]').addEventListener('click', function() {
    showMapModal('start');
});

document.querySelector('input[name="address-end"]').addEventListener('click', function() {
    showMapModal('end');
});
