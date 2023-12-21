// 导入配置文件
import config from './config.js';

// 导入返回函数
import { goBack } from './utilityFunction.js'

document.querySelector('.top-container .return').addEventListener('click', goBack);


// 高亮显示：新函数用于更新发布按钮状态
function updateSubmitButtonState() {
    var allFilled = true;
    $('.user-input input[type="text"]').each(function() {
        if ($(this).val() === '' && $(this).attr('name') !== 'cargo-Info') {
            allFilled = false;
        }
    });

    if (allFilled) {
        $('.post').prop('disabled', false).addClass('active');
        console.log('可以点击');
    } else {
        $('.post').prop('disabled', true).removeClass('active');
        console.log('无法点击');
    }
}



$(document).ready(function() {
    // 初始化日期和时间选择器
    $('input[name="date-start"], input[name="date-end"]').pickadate({
        format: 'yyyy-mm-dd', // 或您想要的任何格式
        onSet: function(context) {
            updateSubmitButtonState(); // 高亮显示：监听日期选择器的变化
        }
    });

    $('input[name="time-start"], input[name="time-end"]').pickatime({
        format: 'HH:i', // 24小时制
        onSet: function(context) {
            updateSubmitButtonState(); // 高亮显示：监听时间选择器的变化
        }
    });

    // 监听必填 input 字段
    $('.user-input input[type="text"]').on('input', function() {
        updateSubmitButtonState(); // 高亮显示：监听输入字段的变化

    });




    // 发布按钮的事件监听器
    document.querySelector('.post').addEventListener('click', function(e) {
        e.preventDefault(); // 阻止按钮的默认行为

        // 获取表单数据
        var cargoName = document.querySelector('input[name="cargo-name"]').value;
        var cargoWeight = document.querySelector('input[name="cargo-weight"]').value;
        var cargoValue = document.querySelector('input[name="cargo-value"]').value;
        
        var startDate = document.querySelector('input[name="date-start"]').value;
        var startTime = document.querySelector('input[name="time-start"]').value;
        var endDate = document.querySelector('input[name="date-end"]').value;
        var endTime = document.querySelector('input[name="time-end"]').value;

        var beginDateTime = startDate + ' ' + startTime;
        var endDateTime = endDate + ' ' + endTime;

        var cargoInfo = document.querySelector('textarea[name="cargo-Info"]').value;
        
        var startAddress = document.querySelector('input[name="address-start"]').value;
        var endAddress = document.querySelector('input[name="address-end"]').value;

        const bossId = JSON.parse(localStorage.getItem('userInfo')).userId;
        
        // 定义一个函数用于发送数据到服务器
        function sendData(startPoint, endPoint) {
            console.log(startPoint);
            console.log(endPoint);
            axios({
                url: config.postBIllApi,
                method: 'POST',
                data: {
                    cargoName: cargoName,
                    cargoWeight: cargoWeight,
                    beginTime: beginDateTime,
                    endTime: endDateTime,
                    beginLocation: startPoint,
                    endLocation: endPoint,
                    value: cargoValue,
                    info: cargoInfo,
                    bossId: bossId
                }
            }).then(result => {
                console.log(result);
            }).catch(error => {
                console.log(error);
            });
        }

        // 调用函数并发送数据
        geocodeAddress(startAddress, function(lng, lat) {
            var startPoint = `${lng}, ${lat}`;
            geocodeAddress(endAddress, function(lng, lat) {
                var endPoint = `${lng}, ${lat}`;
                sendData(startPoint, endPoint);
            });
        });
    });
});

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
