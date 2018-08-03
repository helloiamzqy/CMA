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
            let tr1 = $("<tr>")
                .append($("<td>").text(merchant.creditCode))
                .append($("<td>").text(merchant.idCard))
                .append($("<td>").text(merchant.corporateName))
                .append($("<td>")
                    .append($("<img>")
                        .attr("src",merchant.picture)
                        .attr("width","45px")
                        .attr("height","40px")))
                .append($("<td>").text(merchant.phone))
                .append($("<td>").text(merchant.shopName))
                .append($("<td>").text(merchant.address))
                .append($("<td>").text(merchant.comments))
                .append(addBtn(type,merchant))
                .appendTo($tbody)
        })

        function addBtn(type,merchant) {
            let btn;
            if(type==0){ //查看未审核信息的请求
                btn = $("<td>")
                .append($("<button>")
                    .text("同意")
                    .addClass("btn btn-success agree")
                    .on("click",(e)=>changeStatus(merchant,1)))
                .append($("<button>")
                    .text("驳回")
                    .addClass("btn btn-primary reject")
                    .on("click",(e)=>changeStatus(merchant,3)))
                .append($("<button>")
                    .text("不同意")
                    .addClass("btn btn-danger disgree")
                    .on("click",(e)=>changeStatus(merchant,2)))
            }else if(type==1){ //查看白名单的请求
                btn = $("<td>")
                    .append($("<button>")
                    .text("拉黑")
                    .addClass("btn btn-danger disgree")
                    .on("click",(e)=>changeStatus(merchant,2)))
            }else{//查看黑名单的请求
                btn = $("<td>")
                    .append($("<button>")
                    .text("拉白")
                    .addClass("btn btn-success")
                    .on("click",(e)=>changeStatus(merchant,1)))
            }
            return btn;
        }
    }

    function changeStatus(merchant,status){
        cur = merchant;
        merchant.status = status;
        let url = "http://localhost:9090/admin/merchantInfo/updateStatus"
        let merchant1 = myAjax(url,"PUT",merchant,(e)=>{
            let index = model.indexOf(cur);
            model.splice(index,1);
            render();
        })
    }
}

// $(function () {
    // let url = "http://localhost:9090/admin/merchantInfo/status/0";
    // MerchantComponent($("#app"),url,0);
// })
