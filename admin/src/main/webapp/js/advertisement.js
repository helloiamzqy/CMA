function AdvertisementComponent($view, url) {
    let model = null;
    let cur;
    let flag = "Advertisement";
    let wsUrl = "ws://10.222.29.192:9090/admin/sync";
    let countUrl = "http://localhost:9090/admin/message/unReadCount/";
    let compNewCount = 0;
    let merNewCount = 0;
    init();

    function init() {

        //改变每页显示的数据数
        $("#pageSize").on("blur",function () {
            $(".datas").remove();
            let path = url + "?currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
            myAjax(path,"GET",null,(ads)=>{
                model = ads.dataList;
                makePage(ads);
                renderTable();
            });
        });

        //获取数据
        let path = url + "?currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
        myAjax(path,"GET",null,(advertisements)=>{
            model = advertisements.dataList;
            myAjax(countUrl,"GET",null,(unReadCount)=>{
                compNewCount = unReadCount.complaintNewCount;
                merNewCount = unReadCount.merchantInfoNewCount;
                renderBar();
            });
            //makePage(advertisements);
            renderTable();
        });

        let ws = new WebSocket(wsUrl);

        ws.onopen = function () {
            ws.send(flag);
        };

        ws.onmessage = function (evt) {
            console.log(evt);
            let data = JSON.parse(evt.data);
            if (data.className=="Advertisement"){
                if(model.length>=$("#pageSize").val()){
                    model.push(data);
                    // model.sort(function (a,b) {
                    //     return b.createTime - a.createTime;
                    // });
                    renderTable();
                }else{
                    model.push(data);
                    model.sort(function (a,b) {
                        return b.createTime - a.createTime;
                    });
                }
                renderTable();
            }else if (data.className=="Complaint") {
                compNewCount++;
                renderBar();
            }else{
                merNewCount++;
                renderBar();
            }

        }
    }

    function renderTable() {
        let $tbody = $view.find("#advertisementList tbody");
        $tbody.empty();
        model.forEach((advertisement)=>{
            let type = advertisement.status;
            $("<tr>")
                .addClass("trCursor")
                .append($("<td>").text(advertisement.merchantName))
                .append($("<td>").text(advertisement.price))
                .append($("<td>")
                    .append(addBtn(type,advertisement)))
                .on("dblclick",(e)=>{
                    rendermodal(advertisement,type);
                })
                .appendTo($tbody)
        })
    }

    function addBtn(type,advertisement) {
        let btn;
        if(type==0){ //广告上架
            btn = $("<div>")
                .append($("<button>")
                    .text("上架")
                    .addClass("btn btn-success opt")
                    .on("click",(e)=>changeStatus(advertisement,1)))
        }else{ //广告下架
            btn = $("<div>")
                .append($("<button>")
                    .text("下架")
                    .addClass("btn btn-danger opt")
                    .on("click",(e)=>changeStatus(advertisement,0)))
        }
        return btn;
    }

    function changeStatus(advertisement,status){
        $("#MerchantModal").modal("hide");//隐藏模态框
        cur = advertisement;
        advertisement.status = status;
        myAjax(url,"PUT",advertisement,(cb)=>{
            let index = model.indexOf(cur);
            model.splice(index,1,cb);
            renderTable();
        })
    }

    function rendermodal(advertisement,type){
        $("#msg").empty();
        $("#img").empty();
        $("#optBtn").empty();
        $("#msg")
            .append($("<div>").text("商家名:" + advertisement.merchantName).addClass("detail"))
            .append($("<div>").text("竞价:" + advertisement.price).addClass("detail"))
        $("#img")
            .append($("<img>")
                .attr("src",advertisement.picture)
                .attr("width","160px")
                .attr("height","120px"));
        $("#optBtn")
            .append(addBtn(type,advertisement));
        $("#MerchantModal").modal("show");
    }

    function renderBar() {
        if (merNewCount > 0){
            $("#merchantInfoItem").text("待审核("+merNewCount+")");
        } else{
            $("#merchantInfoItem").text("待审核");
        }

        if (compNewCount > 0){
            $("#complaintItem").text("投诉审核("+compNewCount+")");
        } else {
            $("#complaintItem").text("投诉审核");
        }
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

// $(function () {
// let url = "http://localhost:9090/admin/merchantInfo/status/0";
// MerchantComponent($("#app"),url,0);
// })
