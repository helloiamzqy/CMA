function MerchantComponent($view, url, type) {
    let model = null;
    let cur;

    init();

    function init() {
        myAjax(url,"GET",null,(merchants)=>{
            model = merchants;
            render();
        })
    }

    function render() {
        let $tbody = $view.find("#merchantList tbody");
        $tbody.empty();
        model.forEach((merchant)=>{
            $("<tr>")
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
                    rendermodal(merchant,type);
                })
                .appendTo($tbody)
        })
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
        cur = merchant;
        merchant.status = status;
        let url = "http://localhost:9090/admin/merchantInfo/updateStatus"
        let merchant1 = myAjax(url,"PUT",merchant,(e)=>{
            let index = model.indexOf(cur);
            model.splice(index,1);
            render();
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
}

// $(function () {
// let url = "http://localhost:9090/admin/merchantInfo/status/0";
// MerchantComponent($("#app"),url,0);
// })
