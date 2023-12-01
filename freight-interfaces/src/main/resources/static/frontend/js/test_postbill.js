// 获取表单


function getInfo() {
    document.querySelector('.confirm-container .post').addEventListener('click', () => {
        
        const form = document.querySelector('.user-input')
        const Billdata = serialize(form, {hash: true, empty: true})
        console.log(Billdata);
        
    })


    axios({
        url: '',
        method: 'POST',
        data: {
            // 后端预测参数，从Billdata提取
        }
    }).then(result => {
        console.log(result);
    })
}

getInfo()