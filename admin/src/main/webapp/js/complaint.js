function AdvertisementComponent($view,url) {
    let model = null;
    let cur = null;
    let method="GET";
    init();

    function init() {
        //改变每页显示的数据数
        $("#pageSize").on("blur",function () {
            $(".datas").remove();
            let path = url + "?currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
            myAjax(path,"GET",null,(comp)=>{
                model = comp.dataList;
                makePage(comp);
                renderTable();
            });
        });

        //获取数据
        let path = url + "?currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
        myAjax(path,"GET",null,(cmop)=>{
            model = cmop.dataList;
            makePage(cmop);
            renderTable();
        });

        let ws = new WebSocket("ws://10.222.29.192:9090/admin/sync");
        ws.onopen = function () {
            ws.send("I am Client");
        };

        ws.onmessage = function (evt) {
            alert("okok");
        }
    }

    function renderTable() {
        let $tbody = $("#ads tbody").empty();
        for(let i=0;i<model.length;i++){
            let tr=$("<tr>");
            $("<td>").text(model[i].orderId).appendTo(tr);
            $("<td>").text(model[i].merchantId).appendTo(tr);
            $("<td>").text(model[i].reason).appendTo(tr);
            $("<td>").text(model[i].createTime).appendTo(tr);
            // let td1=$("<td>");
            // let td2=$("<td>");
            let td3=$("<td>");
            // $("<button>").text("上架").addClass("btn btn-success").on("click",function () {
            //     changeState(model[i],1);
            // }).appendTo(td1);
            // $("<button>").text("下架").addClass("btn btn-danger").on("click",function () {
            //     changeState(model[i],0);
            // }).appendTo(td2);
            $("<button>").text("删除").addClass("btn btn-danger").on("click",function () {
                delComp(model[i]);
            }).appendTo(td3);
            // if(model[i].status==0){
            //     $("<td>").text("下架").appendTo(tr);
            //     td1.appendTo(tr);
            // }else{
            //     $("<td>").text("上架").appendTo(tr);
            //     td2.appendTo(tr);
            // }
            td3.appendTo(tr);
            tr.attr("id",model[i].id).addClass("datas").appendTo($tbody);
        }
    }

    //删除广告
    function delComp(data){
        let path = url + "/" + data.id;
        myAjax(path,"DELETE",null,(comp)=>{
            let index = model.indexOf(data);
            model.splice(index,1);
            renderTable();
        });
    }


    //改变状态
    function changeState(data,state) {

        let path = url + "?id=" + data.id + "&state=" + state;
        myAjax(path,"PUT",null,(nc)=>{
            let index = model.indexOf(data);
            model.splice(index,1,nc);
            renderTable();
        })
    }



    //渲染页数
    function makePage(data){
        let totalPage=data.totalPage;
        let totalCount=data.totalCount;
        $(".pagination").remove();
        let page=$("#page");
        let ul=$("<ul>").addClass("pagination");
        ul.appendTo(page);
        let li1=$("<li>").addClass("lis").appendTo(ul);
        let a1=$("<a>").attr("href","#").attr("aria-label","Previous").appendTo(li1);
        $("<span>").attr("aria-hidden","true").text("首页").appendTo(a1);
        for(let i=0;i<totalPage;i++){
            let li=$("<li>").addClass("lis");
            $("<a>").attr("href","#").text(i+1).appendTo(li);
            li.appendTo(ul);
        }
        let li2=$("<li>").addClass("lis").appendTo(ul);
        let a2=$("<a>").attr("href","#").attr("aria-label","Previous").appendTo(li2);
        $("<span>").attr("aria-hidden","true").text("尾页").appendTo(a2)
        $("#totalPage").text(data.totalPage);
        $("#totalCount").text(data.totalCount);
        $(".lis").on("click",function(){
            if($(this).text()=="首页"){
                $("#currentPage").val(1);
            }else if($(this).text()=="尾页"){
                $("#currentPage").val(totalPage);
            }else{
                $("#currentPage").val($(this).text());
            }
            $(".datas").remove();
            let path = url + "?currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
            myAjax(path,"GET",null,(comp)=>{
                model = comp.dataList;
                makePage(comp);
                renderTable();
            });
        })
    }

}

$(function () {
    AdvertisementComponent($("app"),"http://localhost:9090/admin/complaint");
})