function OrderComponent($view,url,operation,customerId) {
    let order_model=new Object();
    let order_temp=new Object();
    let curPage="1";
    let pageSize="20";
    let order_url="?curPage="+curPage+"&pageSize="+pageSize;
    init();

    function init() {
        initNav();
        initModal();
        getOrder();
    }

//////////////////////////Modal Event//////////////////////////
    function initCancelModal() {
        $("#cancel").on("click",function () {
            let cancelUrl=url+"orders/cancel";
            myAjax(cancelUrl,"PUT",order_temp,(result)=>{
                $('#cancelModal').modal('hide');
                if(result) {
                    let index=order_model.indexOf(order_temp);
                    order_model.splice(index,1);
                    alert("取消成功");
                    renderOrder();
                }else{alert("取消失败");}
            })
        })
    }
    function initSuccessModal() {
        $("#success").on("click",function () {
            let successUrl=url+"orders/success";
            myAjax(successUrl,"PUT",order_temp,(result)=>{
                $('#successModal').modal('hide');
                if(result) {
                    let index=order_model.indexOf(order_temp);
                    order_model.splice(index,1);
                    alert("确认成功");
                    renderOrder();
                }else{alert("确认失败");}
            })
        })
    }
    function initCommentModal() {
        $("#comment").on("click",function () {
            if(order_temp.status!="6"){
                let commentUrl=url+"comment/"+order_temp.id;
                let data=new Object();
                data.content=$("#content").val();
                data.rank=$("#rank").val();
                alert(data.rank);
                myAjax(commentUrl,"POST",data,(comment)=>{
                    $('#commentModal').modal('hide');
                    if(comment){
                        alert("评论成功");
                        order_temp.status="6";
                        renderOrder();
                    }else {alert("您已经评论过啦！");}
                    $("#content").val("");
                    $("#rank").val("5");
                })
            }else{
                alert("您已经评论过啦！");
                $('#commentModal').modal('hide');
            }
        })
    }
    function initComplainModal() {
        $("#complain").on("click",function () {
            if(order_temp.formatStatus!="已投诉"){
                let complainUrl=url+"complain/";
                let data=new Object();
                data.reason=$("#reason").val();
                data.merchantName=order_temp.merchant.name;
                data.merchantId=order_temp.merchant.id;
                data.orderId=order_temp.id;
                alert(order_temp.formatStatus)
                myAjax(complainUrl,"POST",data,(complain)=>{
                    $('#complainModal').modal('hide');
                    alert(JSON.stringify(complain))
                    if(complain){
                        order_temp.formatStatus="已投诉";
                        alert("投诉成功");
                        renderOrder();
                    }else{alert("投诉失败");}
                    $("#reason").val("");
                })
            }else{
                $('#complainModal').modal('hide');
                alert("您已经投诉过啦！");
            }
        })
    }
    function initModal() {
        initCancelModal();
        initSuccessModal();
        initCommentModal();
        initComplainModal();
    }
    function showModal(order,element) {
        order_temp=order;
        element.modal("show");
    }
///////////////////////////order/////////////////////////////////
    function orderDetail(order){
        sessionStorage.setItem("order",JSON.stringify(order));
        window.location.href="./order_detail.html";

    }
    function getOrder() {
        let api=url+"orders/customer/"+customerId+operation+order_url;
        alert(api)
        myAjax(api,"GET",null,(orders)=>{
            alert(JSON.stringify(orders))
            order_model=orders.list;
            renderOrder();
        })
    }
    function renderOrder(){
        let $tbody=$view.find("#orderList tbody");
        $tbody.empty();
        order_model.forEach((order)=>{
            format(order);
            let $tr=$("<tr>");
            $tr//all,finished,unreceive,doing
                .append($("<td>").text(order.id))
                .append($("<td>").text(order.merchant.name))
                .append($("<td>").text(order.formatCreateTime))
                .append($("<td>").text(order.totalPrice))
                .append($("<td>").text(order.formatStatus))
                .append($("<td>").append($("<button>").text("查看详情").on("click",()=>{orderDetail(order)})))
            if(operation=="/finished"){
                $tr.append($("<td>").append($("<button>").text("评论订单").on("click",()=>{showModal(order,$("#commentModal"))})))
                   .append($("<td>").append($("<button>").text("投诉订单").on("click",()=>{showModal(order,$("#complainModal"))})))
            }
            if(operation=="/unreceive"){
                $tr.append($("<td>").append($("<button>").text("取消订单").on("click",()=>{showModal(order,$("#cancelModal"))})))
            }
            if(operation=="/doing"){
                $tr.append($("<td>").append($("<button>").text("确认完成").on("click",()=>{showModal(order,$("#successModal"))})))
            }
            $tr.appendTo($tbody);
        })
    }
}

////////////////////////// nav ////////////////////////////////
function initNav() {
    //all,finished,unreceive,doing
    $("#allOrder").on("click",function () {
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
///////////////////////// format //////////////////////////////
function format(order) {
    if(order.formatStatus==null){
        order.formatStatus=codeToStatus(order);
        order.formatCreateTime=getDateTime(order.createTime);
    }
}

///////////////////////// start ///////////////////////////////
$(function () {
    let url="/customer/";
    let customerId=sessionStorage.getItem("customerId");
    alert(customerId);
    if(customerId==null){
        customerId="8a5e9d3d6507ea7b016507ea81930000";
    }
    let operation=sessionStorage.getItem("operation");
    if(operation==null){
        operation="/all";//all,finished,unreceive,doing
    }
    OrderComponent($("#orderPage"),url,operation,customerId);
})