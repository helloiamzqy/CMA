function CustomerComponent($view,host,mId,status) {
    let model=null;
    let cur=null;
    let mid = sessionStorage.getItem("mId");
    let getUrl="/"+mid+"/orders/";
    init();
    function init() {
        let curPage=$("#currentPage").val();
        let pageSize=$("#pageSize").val();
        let page="?curPage="+curPage+"&pageSize="+pageSize;
        $view.find("#down").on("change",function () {
            status=$("#down").val();
            myAjax(host+getUrl+status+page,"GET",null,(orders)=>{
                model=orders.list;
                makePage(orders);
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
        model=orders.list;
            makePage(orders);
        render();
    });
        //改变每页显示的数目
        $("#pageSize").on("blur",function () {
            $(".datas").remove();
            init();
        })
    }
    function render() {
        let $tbody=$view.find("#orderList tbody");
        $tbody.empty();
        model.forEach((order)=>{
            let createTime=timeStamp2String(order.createTime);
        let finishTime=timeStamp2String(order.finishTime);
        let status=judgeStatus(order.status);
        let tr=$("<tr>").attr("id",order.id).addClass("datas")
            .append($("<td>").text(order.id))
            .append($("<td>").text(order.customer.name))
            .append($("<td>").text(order.phone))
            .append($("<td>").text(order.address))
            .append($("<td>").text(order.totalPrice))
            .append($("<td>").text(createTime))
            .append($("<td>").text(finishTime))
            .append($("<td>").append($("<button>").attr("data-am-modal","{target:'#orderItem-popup'}").addClass("am-round").text("查看详情").on("click",(e)=>{
                getOrderItem(order.id);
        e.preventDefault();
    })))
    .append($("<td>").text(status)).appendTo($tbody);
        if(order.status=="0"){
            tr.append($("<td>").append(($("<button>").addClass("am-round")).text("接单").on("click",(e)=>{
                e.preventDefault();
                $("#newOrder").text($("#newOrder").text()-1)
            changeOrder(order,1)
        })))
        .append($("<td>").append(($("<button>").addClass("am-round")).text("拒绝").on("click",(e)=>{
                e.preventDefault();
                let init = sessionStorage.getItem("init");
                init--;
                sessionStorage.setItem("init",init);
            changeOrder(order,4)
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
            tr.append($("<td>").append($("<button>").attr("data-am-modal","{target:'#comment-popup'}").addClass("am-round").text("查看评价").on("click",(e)=>{
                e.preventDefault();
                getComments(order.id);
            })))
        }else if(order.status=="4"){
            tr.append($("<td>").text("已拒绝接单"));
        }else {
            tr.append($("<td>").text("接单已取消"));
        }
    })
    }
    function getComments(id) {
        $.ajax({
            type:'GET',
            url:host+"/order/"+id+"/comment",
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            success:function (data) {
                model=data;
                renderComment(data);
            }

        })
    }
    function renderComment() {
        let $tbody=$view.find("#Comment tbody");
        $tbody.empty();
            $("<tr>").append($("<td>").text(model.content))
            .append($("<td>").text(model.rank))
            .appendTo($tbody);
    }
    function getOrderItem(id) {
        $.ajax({
            type:'GET',
            url:host+"/orderItems/"+id,
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            success:function (data) {
                model=data;
                renderOrderItem(data);
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
                init();
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
    function makePage(data){

        let totalPage=data.pageTotal;
        let totalCount=data.rowsTotal;
        $(".lis").remove();
        // let page=$("#page");
        let ul=$("#uu");
        // let ul=$("<ul>").addClass("pagination");
        // ul.appendTo(page);
        let li1=$("<li>").addClass("lis").appendTo(ul);
        let a1=$("<a>").attr("href","#").attr("aria-label","Previous").appendTo(li1);
        $("<span>").attr("aria-hidden","true").text("首页").appendTo(a1);
        for(let i=0;i<data.pageTotal;i++){
            let li=$("<li>").addClass("lis");
            $("<a>").attr("href","#").text(i+1).appendTo(li);
            li.appendTo(ul);
        }
        let li2=$("<li>").addClass("lis").appendTo(ul);
        let a2=$("<a>").attr("href","#").attr("aria-label","Previous").appendTo(li2);
        $("<span>").attr("aria-hidden","true").text("尾页").appendTo(a2);
        $("#totalPage").text(data.pageTotal);
        $("#totalCount").text(data.rowsTotal);
        $(".lis").on("click",function(){
            if($(this).text()=="首页"){
                $("#currentPage").val(1);
            }else if($(this).text()=="尾页"){
                $("#currentPage").val(totalPage);
            }else{
                $("#currentPage").val($(this).text());
            }
            $(".datas").remove();
            init();
        })
    }
}

$(function () {
    let mId = sessionStorage.getItem('mId');
    let status="0";
    $("#down").on("change",function () {
        status=$("#down").val();
    })
    let host="http://10.222.29.188:8081";
    CustomerComponent($("#app"),host,mId,status);
})