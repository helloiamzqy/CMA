function MerchantComponent($view, url, type, tag) {
    let model = null;
    let cur;
    let flag = "Merchant";

    let wsUrl = "ws://10.222.29.192:9090/admin/sync";
    let countUrl = "http://localhost:9090/admin/message/unReadCount/";
    let adNewCount = 0;
    let compNewCount = 0;
    let merNewCount = 0;
    init();

    function init() {
        //改变每页显示的数据数
        $("#pageSize").on("blur",function () {
            $(".datas").remove();
       //     let path = url + "?status=0&"+"currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
            let path = url ;

            myAjax(path,"GET",null,(comp)=>{
                model = comp.dataList;
                makePage(comp);
                renderTable();
            });
        });

        //获取数据
      //  let path = url + "?status=0&"+ "currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
        let path = url ;
        myAjax(path,"GET",null,(mer)=>{
    //        model = mer.dataList;
            model = mer;
            myAjax(countUrl,"GET",null,(unReadCount)=>{

                adNewCount = unReadCount.advertisementNewCount;
                compNewCount = unReadCount.complaintNewCount;
                merNewCount = unReadCount.merchantInfoNewCount;

                renderBar();
            });
            model = model.reverse()
            makePage(model);
            renderTable();
        });

        let ws = new WebSocket(wsUrl);

        ws.onopen = function () {
            ws.send(flag);
        };

        ws.onmessage = function (evt) {
            console.log(evt);
            let data = JSON.parse(evt.data);
            if (type == "0"){
                if (data.className=="MerchantInfo"){
                    if(model.length>=$("#pageSize").val()){
                        model = model.reverse();
                        model.push(data);
                        model = model.reverse();
                        // model.sort(function (a,b) {
                        //     return b.createTime - a.createTime;
                        // });
                        renderTable();
                    }else{
                        model = model.reverse();
                        model.push(data);
                        model = model.reverse();
                        // model.sort(function (a,b) {
                        //     return b.createTime - a.createTime;
                        // });
                    }

                    renderTable();
                }else if (data.className=="Advertisement") {
                    adNewCount++;
                    renderBar();
                }else{
                    compNewCount++;
                    renderBar();
                }
            }else{
                if (data.className=="MerchantInfo") {
                    merNewCount++;
                    renderBar();
                }else if (data.className=="Advertisement") {
                    adNewCount++;
                    renderBar();
                }else {
                    compNewCount++;
                    renderBar();
                }
            }
        }
    }

    function renderTable() {
        let $tbody = $view.find("#merchantList tbody");
        $tbody.empty();
        model.forEach((merchant)=>{
            let tr = $("<tr>")
                .addClass("trCursor")
                .append($("<td>").text(merchant.creditCode))
                .append($("<td>").text(merchant.idCard))
                .append($("<td>").text(merchant.corporateName))
                // .append($("<td>")
                //     .append($("<img>")
                //         .attr("src",merchant.picture)
                //         .attr("width","45px")
                //         .attr("height","40px")))
                // .append($("<td>").text(merchant.phone))
                .append($("<td>").text(merchant.shopName))
                // .append($("<td>").text(merchant.address))
                // .append($("<td>").text(merchant.comments))
                .append($("<td>")
                    .append(addBtn(type,merchant)))
                .on("dblclick",(e)=>{
                    // updateIsRead(merchant);
                    rendermodal(merchant,type);

                })
                .appendTo($tbody)
            if(tag==1){//待审核页面
                let td;
                if(merchant.isRead == "false"){
                    td = $("<td>")
                        .attr("id","sign")
                        .append($("<span>")
                            .addClass("glyphicon glyphicon-envelope")
                            .addClass("isRead"))
                }else{
                    td = $("<td>")
                        .append($("<span>")
                            .addClass("glyphicon glyphicon-check"))
                }
                tr.prepend(td);
            }
        })
    }

    function renderBar() {
        $("#m").hide();
        if (compNewCount > 0){
            // $("#complaintItem").text("投诉审核("+compNewCount+")");
            $("#complaintItem").text(compNewCount);

        } else {
            $("#cm").hide();
            // $("#complaintItem").text("投诉审核");
        }

        if (adNewCount > 0){
            // $("#advertisementItem").text("广告审核("+adNewCount+")");
            $("#advertisementItem").text(adNewCount);
        } else {
            $("#ad").hide();
            // $("#advertisementItem").text("广告审核");
        }

        if (type != "0"){
            $("#m").show();
            if (merNewCount > 0){
                // $("#merchantInfoItem").text("待审核("+merNewCount+")");
                $("#merchantInfoItem").text(merNewCount);
            } else{
                $("#m").hide();
                // $("#merchantInfoItem").text("待审核");
            }
        }
    }

    function updateIsRead(merchant) {
        cur = merchant;
        merchant.isRead = "true";
        let url = "http://localhost:9090/admin/merchantInfo/update"
        myAjax(url,"PUT",merchant,(cb)=>{
            let index = model.indexOf(cur);
            model.splice(index,1,cb);
            renderTable();
        });
    }

    function addBtn(type,merchant) {
        let btn;
        if(type==0){ //查看未审核信息的请求
            btn = $("<div>")
                .append($("<button>")
                    .text("同意")
                    .addClass("btn btn-success opt")
                    .on("click",(e)=>changeStatus(merchant,1)))
                .append($("<button>")
                    .text("驳回")
                    .addClass("btn btn-warning opt")
                    .on("click",(e)=>changeStatus(merchant,3)))
                .append($("<button>")
                    .text("不同意")
                    .addClass("btn btn-danger opt")
                    .on("click",(e)=>changeStatus(merchant,2)))
        }else if(type==1){ //查看白名单的请求
            btn = $("<div>")
                .append($("<button>")
                    .text("拉黑")
                    .addClass("btn btn-danger opt")
                    .on("click",(e)=>changeStatus(merchant,2)))
        }else{//查看黑名单的请求
            btn = $("<div>")
                .append($("<button>")
                    .text("拉白")
                    .addClass("btn btn-success opt")
                    .on("click",(e)=>changeStatus(merchant,1)))
        }
        return btn;
    }

    function changeStatus(merchant,status){
        $("#MerchantModal").modal("hide");//隐藏模态框
        // cur = merchant;
        merchant.status = status;
        merchant.isRead = "true";
        let url = "http://localhost:9090/admin/merchantInfo/update"
        let merchant1 = myAjax(url,"PUT",merchant,(e)=>{
            let index = model.indexOf(cur);
            model.splice(index,1);
            renderTable();
        })
    }

    function rendermodal(merchant,type){
        $("#msg").empty();
        $("#img").empty();
        $("#optBtn").empty();
        $("#msg")
            .append($("<div>").text("工商号:" + merchant.creditCode).addClass("detail"))
            .append($("<div>").text("身份证号:" + merchant.idCard).addClass("detail"))
            .append($("<div>").text("法人名： " + merchant.corporateName).addClass("detail"))
            .append($("<div>").text("联系方式：" + merchant.phone).addClass("detail"))
            .append($("<div>").text("店铺名： " + merchant.shopName).addClass("detail"))
            .append($("<div>").text("店铺地址：" + merchant.address).addClass("detail"))
            .append($("<div>").text("备注信息：" + merchant.comments).addClass("detail"));
            // .append(addBtn(type,merchant))
        $("#img")
            .append($("<img>")
                .attr("src",merchant.picture)
                .attr("width","160px")
                .attr("height","120px"));
        $("#optBtn")
            .append(addBtn(type,merchant));
        $("#MerchantModal").modal("show");
    }

    //渲染页数
    function makePage(data){
        let totalPage="1";
        let totalCount=model.length;
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
        $("#totalPage").text("1");
        $("#totalCount").text(model.length);
        $(".lis").on("click",function(){
            if($(this).text()=="首页"){
                $("#currentPage").val(1);
            }else if($(this).text()=="尾页"){
                $("#currentPage").val(totalPage);
            }else{
                $("#currentPage").val($(this).text());
            }
            $(".datas").remove();
        //    let path = url+ "?status=0&" + "currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
            let path = url ;
            myAjax(path,"GET",null,(comp)=>{
                model = comp.dataList;
                makePage(comp);
                renderTable();
            });
        })
    }
}
