function itemComponent($view,url) {
    let items=null;
    let order=JSON.parse(sessionStorage.getItem("order"));
    init();
//////////////////////////////////////////
    function init() {
        $("#back").on("click",function () {
            window.open("./order_page.html");
            window.close();
        })
        initNav()
        renderOrder();
        getItems();
    }


////////////////////////// nav ////////////////////////////////
    function initNav() {
        alert("initNav");
        //all,finished,unreceive,doing
        $("#allOrder").on("click",function () {
            alert(1111);
            sessionStorage.setItem("operation","/all");
            window.location.href="/customer/html/order_page.html";
        })
        $("#finishedOrder").on("click",function () {
            sessionStorage.setItem("operation","/finished");
            window.location.href="/customer/html/order_page.html";
        })
        $("#unreceiveOrder").on("click",function () {
            sessionStorage.setItem("operation","/unreceive");
            window.location.href="/customer/html/order_page.html";
        })
        $("#doing").on("click",function () {
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
                .append($("<td>").text(item.food.picture))
                .append($("<td>").text(item.food.price))
                .append($("<td>").text(item.foodNum))
                .append($("<td>").text(item.totalPrice))
                .appendTo($tbody);
        })
    }
    function renderOrder() {
        format(order);
        $("#orderId").text(order.id);
        $("#orderStatus").text(order.formatStatus);
        $("#createTime").text(order.formatCreateTime);
        $("#finishTime").text(order.formatFinishTime);
        $("#totalPrice").text(order.totalPrice);
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
    let url="http://10.222.29.190:8090/customer/";
    itemComponent($("#itemPage"),url);
})