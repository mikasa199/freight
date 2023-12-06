// 页面加载完毕时执行
document.addEventListener('DOMContentLoaded', () => {
    const userId = localStorage.getItem('userId');
    if (userId) {
        document.querySelector('.number .Id').textContent = userId;
    } else {
        // 处理用户未登录的情况
        console.log("用户未登录");
    }
});



document.querySelector('.confirm-container .user-confirm').addEventListener('click', () => {
    location.href = './test_postbill.html'
})
