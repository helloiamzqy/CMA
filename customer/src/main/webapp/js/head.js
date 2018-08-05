$(function () {
    let username = sessionStorage.getItem('user');
    let custoemrId = sessionStorage.getItem('customerId');
    if (custoemrId==null||custoemrId==""){
        // alert("未登陆")
        $("#userInfo").html('')
        // let userLi = '<li></li>'
        // <li><a href="#">我的收货信息</a></li>
        let login = '<li><a href="/customer/html/login.html">' + '你还未登录,请登陆' + '</a></li>';
        let register = '<li><a href="/customer/html/regist.html">' + '注册' + '</a></li>';
        // userLi.append(login)
        $("#userInfo").append(login)
        $("#userInfo").append(register)
    }
$("#logout").on('click',(e)=>{
    // $1.clearUserCart(userId, shopId);
    // $1.clearCart(shopId);
    sessionStorage.clear();
    window.location.href="/customer/html/login.html";
})

})
