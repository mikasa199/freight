// 点击提交按钮，提交个人数据

document.addEventListener('DOMContentLoaded', function() {
    const submitBtn = document.querySelector('.submit');
    const form = document.querySelector('.user-input');

    submitBtn.addEventListener('click', function(e) {
        e.preventDefault(); // 阻止表单默认提交行为

        // 使用 form-serialize 库序列化表单数据
        const formData = serialize(form, { hash: true });

        // 将 formData 对象附加到要发送的数据对象中
        const dataToSend = {
            ...formData,
            driverId: "1234523", // 示例，可以根据实际需要修改
        };

        // 发送 axios 请求
        axios({
            url: 'http://192.168.10.101:9999/driver/order',
            method: 'POST',
            data: dataToSend
        }).then(result => {
            console.log(result);
        }).catch(error => {
            console.error('请求失败:', error);
        });
    });
});
