function OrderComponent($view, url, customerId) {
    let receiveInfo_model = null;
    init();

    function init() {
        initAllModalEvent();
        getCustomerInfo();
    }

//////////////////////////Modal Event//////////////////////////
    function initAllModalEvent() {
        alert(1);
    }

    function renderCustomerInfo(data) {
        alert(JSON.stringify(data))
        let $div = $view.find("#customerInfo tbody");
        $div.empty();

        $("<tr>")
            .append($("<td>").text(data.id))
            .append($("<td>").text(data.name))
            .append($("<td>").text(data.password))
            .appendTo($div);
    }

    function getCustomerInfo() {
        alert('getCustomerInfo')
        let api = url + "/customer/customerInfo/" + customerId;
        myAjax(api, "GET", null, (data) => {
            renderCustomerInfo(data)
        })
    }

    // function renderOrder() {
    //     let $tbody = $view.find("#orderList tbody");
    //     $tbody.empty();
    //     info_model.forEach((order) => {
    //         $("<tr>")
    //             .append($("<td>").text(order.id))
    //             .append($("<td>").text(order.merchant.name))
    //             .append($("<td>").text(order.createTime))
    //             .append($("<td>").text(order.totalPrice))
    //             .append($("<td>").text(order.status))
    //             .append($("<td>").append($("<button>").text("查看详情").on("click", () => {
    //                 orderDetail(order)
    //             })))
    //             .append($("<td>").append($("<button data-toggle='modal' data-target='#cancelModal'>").text("取消订单").on("click", () => {
    //                 cancelModal(order)
    //             })))
    //             .append($("<td>").append($("<button data-toggle='modal' data-target='#successModal'>").text("确认送达")))
    //             .append($("<td>").append($("<button data-toggle='modal' data-target='#commentModal'>").text("评论订单")))
    //             .append($("<td>").append($("<button data-toggle='modal' data-target='#complainModal'>").text("投诉订单").on("click", () => {
    //                 complainModal(order)
    //             })))
    //             .appendTo($tbody);
    //     })
    // }
}

$(function () {
    let url = "/customer/";
    OrderComponent($("#view"), url, sessionStorage.getItem("customerId"));
})