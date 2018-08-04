function OrderComponent($view,url,customerId) {
    let order_model=new Object();
    let operation="/all";
    let order_temp=new Object();
    init();

    function init() {
        initModal();
        getOrder();
    }

//////////////////////////Modal Event//////////////////////////
    function initModal() {
        $("#cancel").on("click",function () {
            let cancelUrl=url+"orders/cancel";
            myAjax(cancelUrl,"PUT",order_temp,(result)=>{
                $('#cancelModal').modal('hide');
                if(result) {
                    let index=order_model.indexOf(order_temp);
                    order_temp.status="5";
                    order_temp.formatStatus="已取消";
                    order_model.splice(index,1);
                    order_model.push(order_temp);
                    alert("取消成功");
                    renderOrder();
                }else{alert("取消失败");}
            })
        })
        $("#success").on("click",function () {
            let successUrl=url+"orders/success";
            myAjax(successUrl,"PUT",order_temp,(result)=>{
                $('#successModal').modal('hide');
                if(result) {
                    let index=order_model.indexOf(order_temp);
                    order_temp.status="3";
                    order_temp.formatStatus="已完成";
                    order_model.splice(index,1);
                    order_model.push(order_temp);
                    alert("确认成功");
                }else{alert("确认失败");}
            })
        })
        $("#comment").on("click",function () {
            let commentUrl=url+"comment/"+order_temp.id;
            let data=new Object();
            data.content=$("#content").val();
            data.rank=$("#rank").val();
            myAjax(commentUrl,"POST",data,(comment)=>{
                $('#commentModal').modal('hide');
                if(comment.id){alert("评论成功");}else {alert("评论失败");}
                $("#content").val("");
                $("#rank").val("5");
            })
        })
        $("#complain").on("click",function () {
            let complainUrl=url+"complain/";
            let data=new Object();
            data.reason=$("#reason").val();
            data.merchantName=order_temp.merchant.name;
            data.merchantId=order_temp.merchant.id;
            data.orderId=order_temp.id;
            myAjax(complainUrl,"POST",data,(complain)=>{
                $('#complainModal').modal('hide');
                if(complain){alert("投诉成功");}else{alert("投诉失败");}
                $("#reason").val("");
            })
        })
    }
    function showModal(order,element) {
        order_temp=order;
        element.modal("show");
    }
///////////////////////////order/////////////////////////////////
    function orderDetail(order){
        sessionStorage.setItem("orderId",order.id);
        window.location.href="./order_detail.html";

    }
    function getOrder() {
        let api=url+"orders/customer/"+customerId+operation+"?curPage=1&pageSize=100";
        myAjax(api,"GET",null,(orders)=>{
            order_model=orders.list;
            renderOrder();
        })
    }
    function renderOrder(){
        let $tbody=$view.find("#orderList tbody");
        $tbody.empty();
        order_model.forEach((order)=>{
            format(order);
            $("<tr>")
                .append($("<td>").text(order.id))
                .append($("<td>").text(order.merchant.name))
                .append($("<td>").text(order.formatCreateTime))
                .append($("<td>").text(order.totalPrice))
                .append($("<td>").text(order.formatStatus))
                .append($("<td>").append($("<button>").text("查看详情").on("click",()=>{orderDetail(order)})))
                .append($("<td>").append($("<button>").text("取消订单").on("click",()=>{showModal(order,$("#cancelModal"))})))
                .append($("<td>").append($("<button>").text("确认完成").on("click",()=>{showModal(order,$("#successModal"))})))
                .append($("<td>").append($("<button>").text("评论订单").on("click",()=>{showModal(order,$("#commentModal"))})))
                .append($("<td>").append($("<button>").text("投诉订单").on("click",()=>{showModal(order,$("#complainModal"))})))
                .appendTo($tbody);
        })
    }
}
///////////////////////// format //////////////////////////////
function format(order) {
    if(order.status==0)order.formatStatus="未接单";
    else if(order.status==1)order.formatStatus="已接单";
    else if(order.status==2)order.formatStatus="配送中";
    else if(order.status==3)order.formatStatus="已完成";
    else if(order.status==4)order.formatStatus="被拒接";
    else if(order.status==5)order.formatStatus="已取消";
    order.formatCreateTime=getMyDate(order.createTime);
}

function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+
            getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

///////////////////////// start ///////////////////////////////
$(function () {
    let url="http://10.222.29.190:8090/customer/";
    OrderComponent($("#orderPage"),url,"8a5e9d3d65037c800165037c85d50000");
})