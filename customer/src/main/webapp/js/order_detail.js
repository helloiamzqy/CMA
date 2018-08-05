function itemComponent($view,url) {
    let items=null;
    let order=JSON.parse(sessionStorage.getItem("order"));
    init();
//////////////////////////////////////////
    function init() {
        initEvent();
        renderOrder();
        getItems();
    }


////////////////////////// nav ////////////////////////////////
    function initEvent() {
        $("#back").on("click",function () {
            window.open("./order_page.html");
            window.close();
        })

        //all,finished,unreceive,doing
        $("#allOrder2").on("click",function () {
            sessionStorage.setItem("operation","/all");
            window.location.href="/customer/html/order_page.html";
        })
        $("#finishedOrder2").on("click",function () {
            sessionStorage.setItem("operation","/finished");
            window.location.href="/customer/html/order_page.html";
        })
        $("#unreceiveOrder2").on("click",function () {
            sessionStorage.setItem("operation","/unreceive");
            window.location.href="/customer/html/order_page.html";
        })
        $("#doing2").on("click",function () {
            sessionStorage.setItem("operation","/doing");
            window.location.href="/customer/html/order_page.html";
        })
    }
///////////////////////////////////////////////////////////
    function getItems() {
        let orderId=order.id;
        let api=url+"orderItems/"+orderId;
        myAjax(api,"GET",null,(data)=>{
            items=data;
            renderDetail();
        })
    }
    function renderDetail(){
        let $tbody=$view.find("#itemList tbody");
        $tbody.empty();
        items.forEach((item)=>{
            $("<tr>")
                .append($("<td>").text(item.food.foodName))
                .append($("<td>").append($("<img>").attr("src",item.food.picture).attr("width","180px").attr("height","100px")))
                .append($("<td>").text(item.food.price+" 元"))
                .append($("<td>").text(item.foodNum+" 份"))
                .append($("<td>").text(item.totalPrice+" 元"))
                .appendTo($tbody);
        })
    }
    function renderOrder() {
        format(order);
        $("#orderId").text(order.id);
        $("#orderStatus").text(order.formatStatus);
        $("#createTime").text(order.formatCreateTime);
        if(order.formatFinishTime=="1970-01-01 08:00:00"){order.formatFinishTime="订单还未完成"}
        $("#finishTime").text(order.formatFinishTime);
        $("#totalPrice").text(order.totalPrice+" 元");
        $("#customerName").text(order.customer.name);
        $("#customerPhone").text(order.phone);
        $("#customerAddr").text(order.address);
        $("#merchantName").text(order.merchant.name);
    }

    function format(order) {
        order.formatStatus=codeToStatus(order);
        order.formatCreateTime=getDateTime(order.createTime);
        order.formatFinishTime=getDateTime(order.finishTime);
    }

}
$(function () {
    let customerId=sessionStorage.getItem("customerId");
    if(customerId!=null){
        let url="/customer/";
        itemComponent($("#itemPage"),url);
    }else{
        alert("请登录后再尝试！")
        window.location.href="/customer/html/merchant.html";
    }
})