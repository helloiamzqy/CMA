function AdvertisementComponent($view, url) {
    let model = null;
    let cur;

    init();

    function init() {
        let path = url + "?currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
        myAjax(path,"GET",null,(advertisements)=>{
            model = advertisements.dataList;
            render();
        })
    }

    function render() {
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
        let url = "http://localhost:9090/admin/advertisement?id="
            +advertisement.id + "&state=" + status;
        myAjax(url,"PUT",advertisement,(cb)=>{
            let index = model.indexOf(cur);
            model.splice(index,1,cb);
            render();
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
}

// $(function () {
// let url = "http://localhost:9090/admin/merchantInfo/status/0";
// MerchantComponent($("#app"),url,0);
// })
