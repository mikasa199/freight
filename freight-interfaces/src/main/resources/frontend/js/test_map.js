layui.use('form', function(){
    var form = layui.form;
    // 其他代码...
});

// 百度地图API功能
var map = new BMap.Map("map");
map.centerAndZoom(new BMap.Point(106.991761,26.460632), 15);
// 启用滚轮放大缩小
map.enableScrollWheelZoom(true);
// 向地图中添加缩放控件
var ctrlNav = new window.BMap.NavigationControl({
    anchor: BMAP_ANCHOR_TOP_LEFT,
    type: BMAP_NAVIGATION_CONTROL_LARGE
});
map.addControl(ctrlNav);
// 向地图中添加比例尺控件
var ctrlSca = new window.BMap.ScaleControl({
    anchor: BMAP_ANCHOR_TOP_LEFT
});
map.addControl(ctrlSca);
function showInfo(e){// 点击获取定位信息
    $("input[name='lon']").val(e.point.lng);
    $("input[name='lat']").val(e.point.lat);
    map.clearOverlays();// 清空上一次的标注信息
    var new_point = new BMap.Point(e.point.lng,e.point.lat);
    var marker = new BMap.Marker(new_point);  // 创建标注
    map.addOverlay(marker); // 将标注添加到地图中
    map.panTo(new_point);
}
map.addEventListener("click", showInfo);
var localSearch = new BMap.LocalSearch(map);
localSearch.enableAutoViewport(); //允许自动调节窗体大小


/* 准备按钮事件 */
function prepareBtn() {
    $("#mapmoveBtn").on("click", function () {
        var keyword = $("input[name='keyword']").val();
        if(keyword==''){
            return layer.msg("地址不能为空");
        }
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            //获取经度和纬度，将结果显示在文本框中
            $("input[name='lon']").val(poi.point.lng);
            $("input[name='lat']").val(poi.point.lat);
            map.clearOverlays();//清空原来的标注
            map.centerAndZoom(poi.point, 15);
            var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地址对应的经纬度
            map.addOverlay(marker);
        });
        localSearch.search(keyword);
    });
}
