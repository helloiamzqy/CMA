function OrderComponent($view,url,operation,customerId) {
    let order_model=new Object();
    init();

    function init() {
        getOrder();
    }

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
            $("<tr>")//all,finished,unreceive,doing
                .append($("<td>").text(order.id))
                .append($("<td>").text(order.merchant.name))
                .append($("<td>").text(order.formatCreateTime))
                .append($("<td>").text(order.totalPrice))
                .append($("<td>").text(order.formatStatus))
                .append($("<td>").append($("<button>").text("查看详情").on("click",()=>{orderDetail(order)})))
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
    let customerId="8a5e9d3d65037c800165037c85d50000";
    let operation="/all";
    OrderComponent($("#allOrder"),url,operation,customerId);
})