function ComplaintComponent($view, url) {
    let model = null;
    let cur;
    let flag = "Complaint";

    let wsUrl = "ws://10.222.29.192:9090/admin/sync";
    let countUrl = "http://localhost:9090/admin/message/unReadCount/";
    let adNewCount = 0;
    let merNewCount = 0;
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
            myAjax(countUrl,"GET",null,(unReadCount)=>{
                adNewCount = unReadCount.advertisementNewCount;
                merNewCount = unReadCount.merchantInfoNewCount;
                renderBar();
            });
            makePage(cmop);
            renderTable();
        });

        let ws = new WebSocket(wsUrl);

        ws.onopen = function () {
            ws.send(flag);
        };

        ws.onmessage = function (evt) {
            console.log(evt);
            let data = JSON.parse(evt.data);
            if (data.className=="Complaint"){
                if(model.length>=$("#pageSize").val()){
                    model.splice(model.length-1,1,data);
                    model = sort(model);
                    renderTable();
                }else{
                    model.push(data);
                    model = sort(model);
                }

                renderTable();
            }else if (data.className=="Advertisement") {
                adNewCount++;
                renderBar();
            }else{
                merNewCount++;
                renderBar();
            }

        }
    }

    function renderTable() {
        let $tbody = $view.find("#ComplaintList tbody");
        $tbody.empty();
        model.forEach((complaint)=>{
            let tr = $("<tr>")
                .addClass("trCursor")
                .append($("<td>").text(complaint.merchantName))
                .append($("<td>").text(complaint.reason))
                .append($("<td>").text(complaint.createTime))
                .append($("<td>")
                    .append($("<button>")
                        .text("拉黑商家")
                        .addClass("btn btn-danger opt")
                        .on("click",(e)=>changeStatus(complaint))))
                .on("dblclick",(e)=>{
                    updateIsRead(complaint)
                    rendermodal(complaint);
                })
                .attr("id",complaint.id).addClass("datas").appendTo($tbody)
            let td;
            if(complaint.isRead == "false"){
                td = $("<td>")
                    .attr("id","sign")
                    .append($("<span>")
                        .addClass("glyphicon glyphicon-envelope")
                        .addClass("isRead"))
                    .unbind("dblclick").click((e)=>{
                        updateIsRead(complaint);
                    })
            }else{
                td = $("<td>")
                    .append($("<span>")
                        .addClass("glyphicon glyphicon-check"))
            }
            tr.prepend(td);
        })
    }

    function renderBar() {
        $("#cm").hide();
        if (merNewCount > 0){
            // $("#merchantInfoItem").text("待审核("+merNewCount+")");
            $("#merchantInfoItem").text(merNewCount);
        } else{
            $("#m").hide();
            // $("#merchantInfoItem").text("待审核");
        }

        if (adNewCount > 0){
            // $("#advertisementItem").text("广告审核("+adNewCount+")");
            $("#advertisementItem").text(adNewCount);
        } else {
            $("#ad").hide();
            // $("#advertisementItem").text("广告审核");
        }
    }

    function updateIsRead(complaint) {
        cur = complaint;
        complaint.isRead = "true";
        let url = "http://localhost:9090/admin/complaint"
        myAjax(url,"PUT",complaint,(cb)=>{
            let index = model.indexOf(cur);
            model.splice(index,1,cb);
            renderTable();
        })
    }

    function changeStatus(complaint){
        $("#MerchantModal").modal("hide");//隐藏模态框
        cur = complaint;
        complaint.isRead = "true";

        let url = "http://localhost:9090/admin/merchantInfo/updateStatus/"+complaint.merchantId;
        myAjax(url,"PUT",null,(e)=>{
            let index = model.indexOf(cur);
            model.splice(index,1);
            $("#ComplaintModal").modal("hide");
            renderTable();
        })
    }

    function rendermodal(complaint){
        $("#msg").empty();
        $("#optBtn").empty();
        $("#msg")
            .append($("<div>").text("订单号:" + complaint.orderId).addClass("detail"))
            .append($("<div>").text("商家名:" + complaint.merchantName).addClass("detail"))
            .append($("<div>").text("投诉时间： " +complaint.createTime).addClass("detail"))
            .append($("<div>").text("投诉原因：" + complaint.reason).addClass("detail"));
        $("#optBtn")
            .append($("<button>")
                .text("拉黑商家")
                .addClass("btn btn-danger opt")
                .on("click",(e)=>changeStatus(complaint)));
        $("#ComplaintModal").modal("show");
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

    function sort(arr){
        for(let i=0;i<arr.length-1;i++){
            for(let j=0;j<arr.length-1-i;j++){
                if(arr[j].createTime<arr[j+1].createTime){
                    let temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        return arr;
    }
}
