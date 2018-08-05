function ComplaintComponent($view, url) {
    let model = null;
    let cur;

    init();

    function init() {
        let path = url + "?currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val();
        myAjax(path,"GET",null,(complaints)=>{
            model = complaints.dataList;
            render();
        })
    }

    function render() {
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
                    rendermodal(complaint);
                })
                .appendTo($tbody)
            let td;
            if(complaint.isRead == "false"){
                td = $("<td>")
                    .attr("id","sign")
                    .append($("<span>")
                        .addClass("glyphicon glyphicon-envelope"))
                    .unbind("dblclick").click((e)=>{
                        updateIsRead(complaint);
                    })
            }else{
                td = $("<td>")
                    .append($("<span>")
                        .addClass("glyphicon glyphicon-check")
                        .addClass("isRead"))
            }
            tr.prepend(td);
        })
    }

    function updateIsRead(complaint) {
        cur = complaint;
        complaint.isRead = "true";
        let url = "http://localhost:9090/admin/complaint"
        myAjax(url,"PUT",complaint,(cb)=>{
            let index = model.indexOf(cur);
            model.splice(index,1,cb);
            render();
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
            render();
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
}
