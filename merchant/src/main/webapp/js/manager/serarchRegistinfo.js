function FoodComponent($view, url) {
    init();
    function init() {
        myAjax(url,"GET",null,(data)=>{
            $("#address").val(data.address);
            $("#corporateName").val(data.corporateName);
            $("#creditCode").val(data.creditCode);
            $("#idCard").val(data.idCard);
            $("#phone").val(data.phone);
            $("#shopName").val(data.shopName);
            $("#picSrc").attr("src",data.picture);
            $("#aHiddenPicture").val(data.picture);
        });
    }
}

$(function () {
    // let mid = sessionStorage.getItem("mId");
    let mid = sessionStorage.getItem("mId");
    let url='http://localhost:8081/'+mid+'/registinfo';
    FoodComponent($("#indexApp"),url);
})