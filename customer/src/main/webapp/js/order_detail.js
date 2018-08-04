function itemComponent($view,url) {
    let items=null;
    let order=JSON.parse(sessionStorage.getItem("order"));
    alert(JSON.stringify(order));
    init();
//////////////////////////////////////////
    function init() {
        $("#back").on("click",function () {
            window.open("./order_page.html");
            window.close();
        })
        renderOrder();
        getItems();
    }

    function getItems() {
        let orderId=order.id;
        let api=url+"orderItems/"+orderId;
        alert(api);
        myAjax(api,"GET",null,(data)=>{
            items=data;
            renderDetail();
        })
    }
    function renderDetail(){
        let $tbody=$view.find("#itemList tbody");
        $tbody.empty();
        items.forEach((item)=>{
            alert(JSON.stringify(item));
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
        $("#orderId").text(order.id);
        $("#orderStatus").text(order.status);
        $("#customerName").text(order.customer.name);
        // $("#customerPhone").text(order.phone);
        // $("#customerAddr").text(order.address);
        $("#merchantName").text(order.merchant.name);
    }
}

$(function () {
    let url="http://10.222.29.190:8090/customer/";
    itemComponent($("#itemPage"),url);
})