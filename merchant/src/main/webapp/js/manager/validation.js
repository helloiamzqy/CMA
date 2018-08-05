$(function () {
    validation();
})
function validation() {
    let merchant=sessionStorage.getItem("mId");
    if(merchant==null){
        alert("您还没有登录！");
        window.location.href="login.html";
    }
    init();
    function init() {
        $("#out").on("click",function () {
            sessionStorage.clear();
            window.location.href="login.html";
        })
    }
}