function OrderComponent($view,url,customerId) {
    let order_model=null;
    let operation="/all";

    init();

    function init() {
        initAllModalEvent();
        getOrder();
    }

//////////////////////////Modal Event//////////////////////////
    function initAllModalEvent() {
        alert(1);
    }

    function orderDetail(){
        alert(1);

    }
    function cancelModal(order) {
        $("#cancel").on("click",function () {
            let cancelUrl=url+"orders";
            myAjax(cancelUrl,"DELETE",order,()=>{
                $('#cancelModal').modal('hide');
                alert("取消成功");
            })
        })
    }
    function successModal(order) {
        $("#success").on("click",function () {
            let successUrl=url+"orders/";
            myAjax(successUrl,"GET",order,()=>{
                $('#successModal').modal('hide');
                alert("确认成功");
            })
        })
    }
    function commentModal(order) {
        $("#comment").on("click",function () {
            let commentUrl=url+"comment/"+order.id;
            let data=new Object();
            data.content=$("#content").val();
            data.rank=$("#rank").val();
            myAjax(commentUrl,"POST",data,(comment)=>{
                $('#commentModal').modal('hide');
                $("#content").val("");
                $("#rank").val("5");
                alert("评论成功");
            })
        })
    }
    function complainModal(order) {
        $("#complain").on("click",function () {
            let complainUrl=url+"complain/";
            let data=new Object();
            data.reason=$("#reason").val();
            data.merchantId=order.merchant.id;
            data.orderId=order.id;
            alert(JSON.stringify(data));
            // myAjax(complainUrl,"POST",data,(complain)=>{
            //     $('#complainModal').modal('hide');
            //     alert("投诉成功");
            // })
            $('#complainModal').modal('hide');
        })
    }

//////////////////////////////////////////////////////////////
    function getOrder() {
        let api=url+"orders/customer/"+customerId+operation+"?curPage=1&pageSize=10";
        myAjax(api,"GET",null,(orders)=>{
            order_model=orders.list;
            renderOrder();
        })
    }

    function renderOrder(){
        let $tbody=$view.find("#orderList tbody");
        $tbody.empty();
        order_model.forEach((order)=>{
            $("<tr>")
                .append($("<td>").text(order.id))
                .append($("<td>").text(order.merchant.name))
                .append($("<td>").text(order.createTime))
                .append($("<td>").text(order.totalPrice))
                .append($("<td>").text(order.status))
                .append($("<td>").append($("<button>").text("查看详情").on("click",()=>{orderDetail(order)})))
                .append($("<td>").append($("<button data-toggle='modal' data-target='#cancelModal'>").text("取消订单").on("click",()=>{cancelModal(order)})))
                .append($("<td>").append($("<button data-toggle='modal' data-target='#successModal'>").text("确认送达")))
                .append($("<td>").append($("<button data-toggle='modal' data-target='#commentModal'>").text("评论订单")))
                .append($("<td>").append($("<button data-toggle='modal' data-target='#complainModal'>").text("投诉订单").on("click",()=>{complainModal(order)})))
                .appendTo($tbody);
        })
    }
}

$(function () {
    let url="http://10.222.29.190:8090/customer/";
    OrderComponent($("#orderPage"),url,"8a5e9d3d64feef560164feef5b510000");
})