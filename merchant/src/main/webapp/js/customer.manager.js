function CustomerComponent($view,host,mId,status) {
    let model=null;
    let cur=null;
    let getUrl="/orders/merchant/8a5e9d3d64feef560164feef5b9f0001/";
    let page="?curPage=1&pageSize=10";
    init();
    function init() {
        $view.find("#down").on("change",function () {
            status=$("#down").val();
            myAjax(host+getUrl+status+page,"GET",null,(orders)=>{
                model=orders.list;
            render();
        });
        })
        $view.find("#addForm").on("submit",(e)=>{
            e.preventDefault();
        let obj=$(e.target).serializeObject();
        myAjax(url,"POST",obj,(nc)=>{
            model.push(nc);
        render();
    })
    });
        myAjax(host+getUrl+status+page,"GET",null,(orders)=>{
            alert(host+getUrl+status+page);
        model=orders.list;
        render();
    });
    }
    function render() {
        let $tbody=$view.find("#orderList tbody");
        $tbody.empty();
        model.forEach((order)=>{
            let createTime=timeStamp2String(order.createTime);
        let finishTime=timeStamp2String(order.finishTime);
        let status=judgeStatus(order.status);
        let tr=$("<tr>").attr("id",order.id)
            .append($("<td>").text(order.id))
            .append($("<td>").text(order.customer.name))
            .append($("<td>").text(order.customer.phone))
            .append($("<td>").text(order.customer.address))
            .append($("<td>").text(order.totalPrice))
            .append($("<td>").text(createTime))
            .append($("<td>").text(finishTime))
            .append($("<td>").append($("<button>").attr("data-am-modal","{target:'#my-popup'}").addClass("am-round").text("查看详情").on("click",(e)=>{
                getOrderItem(order.id);
        e.preventDefault();
    })))
    .append($("<td>").text(status)).appendTo($tbody);
        if(order.status=="0"){
            tr.append($("<td>").append(($("<button>").addClass("am-round")).text("接单").on("click",(e)=>{
                e.preventDefault();
            changeOrder(order,1)
        })))
        .append($("<td>").append(($("<button>").addClass("am-round")).text("拒绝").on("click",(e)=>{
                e.preventDefault();
            changeOrder(order,0)
        })))
        }else if(order.status=="1"){
            tr.append($("<td>").append(($("<button>").addClass("am-round")).text("配送").on("click",(e)=>{
                e.preventDefault();
            changeOrder(order,2)
        })))
        }else if(order.status=="2"){
            tr.append($("<td>").append($("<button>").addClass("am-round").text("完成").on("click",(e)=>{
                e.preventDefault();
            changeOrder(order,3)
        })))
        }else if(order.status=="3"){
            tr.append($("<td>").text("已完成"));
        }else if(order.status=="4"){
            tr.append($("<td>").text("已拒绝接单"));
        }else {
            tr.append($("<td>").text("接单已取消"));
        }
    })
    }
    function getOrderItem(id) {
        $.ajax({
            type:'GET',
            url:host+"/orderItems/"+id,
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            success:function (data) {
                alert(data);
                model=data;
                renderOrderItem();
            }
        })
    }

    function changeOrder(order,status) {
        order.status=status;
        $.ajax({
            type:'PUT',
            url:host+"/orders",
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            data:JSON.stringify(order),
            success:function (data) {
                $("#"+data.id).remove();
            }
        })

    }
    function timeStamp2String(time){
        if (time!=null){
            var datetime = new Date();
            datetime.setTime(time);
            var year = datetime.getFullYear();
            var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
            var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
            var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
            var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
            var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
            return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
        }else{
            return "未完成...";
        }
    }
    function judgeStatus(status) {
        switch (status){
            case "0":return "未接";
            case "1":return "已接";
            case "2":return "配送中";
            case "3":return "完成";
            case "4":return "拒绝";
            case "5":return "取消";
        }

    }
    function renderOrderItem() {
        let $tbody=$view.find("#orderItem tbody");
        $tbody.empty();
        model.forEach((orderItem)=>{
            $("<tr>").append($("<td>").text(orderItem.food.foodName))
            .append($("<td>").text(orderItem.foodNum))
            .append($("<td>").text(orderItem.totalPrice))
            .appendTo($tbody);
    })

    }
}

$(function () {
    let status="0";
    $("#down").on("change",function () {
        status=$("#down").val();
    })
    let mId = sessionStorage.getItem("mId");
    let host="http://10.222.29.189:8081/merchant";
    CustomerComponent($("#app"),host,mId,status);
})