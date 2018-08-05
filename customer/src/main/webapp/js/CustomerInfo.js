function OrderComponent($view, url, customerId) {
    let receiveInfo_model = null;
    let updateId = null;
    init();

    function init() {
        initAllModalEvent();
        getCustomerInfo();
        getReceiveInfo();

    }

//////////////////////////Modal Event//////////////////////////
    function initAllModalEvent() {
        if(sessionStorage.getItem("customerId")==""||sessionStorage.getItem("customerId")==null){
            window.location.href = '/customer/html/login.html';
        }
        $("#addReceiveInfoBtn").on('click', () => {
            $('#addReceiveInfoModal').modal('show');
        })

        $("#addReceiveInfo").on('click', () => {
            addReceiveInfo();
            $('#addReceiveInfoModal').modal('hide');
        })
        $("#updateReceiveInfo").on('click', () => {
            updateReceiveInfo();
            $('#updateReceiveInfoModal').modal('hide');
        })
    }

    function addReceiveInfo() {
        let jsonData ={'phone':$("#addReceiveInfoPhone").val(),'address':$("#addReceiveInfoAddress").val()}
        let api = url + "/receiveInfo/" + customerId;
        myAjax(api, "POST", jsonData, (data) => {
            receiveInfo_model.push(data)
            renderReceiveInfo()
            // renderReceiveInfo();
        })
    }

    function renderCustomerInfo(data) {
        let $div = $view.find("#customerInfo tbody");
        $div.empty();

        // $("<tr>")
        //     .append($("<td>").text("你的id："))
        //     .append($("<td>").text(data.id))
        //     .appendTo($div);
        $("<tr>")
            .append($("<td>").text("用户名："))
            .append($("<td>").text(data.name))
            .appendTo($div);
    }
    function deleteReceiveInfo(data){
        let api = url + "/receiveInfo/" + data.id;
        myAjax(api, "DELETE", null, (msg) => {
            let index = receiveInfo_model.indexOf(data);
            receiveInfo_model.splice(index,1);
            renderReceiveInfo();
        })
    }
    function updateReceiveInfo() {
        // alert(JSON.stringify(receiveInfo))
        // alert(receiveInfo.id)
        // alert(updateId)
        let jsonData ={'id':updateId,'phone':$("#updateReceiveInfoPhone").val(),'address':$("#updateReceiveInfoAddress").val()}
        let api = url + "/receiveInfo/"+customerId;
        myAjax(api, "PUT", jsonData, (data) => {
            // let index = receiveInfo_model.indexOf(data);
            // receiveInfo_model.splice(index,1,data);
            getReceiveInfo();
            renderReceiveInfo();
        })
    }
    function getCustomerInfo() {
        let api = url + "/customer/customerInfo/" + customerId;
        myAjax(api, "GET", null, (data) => {
            renderCustomerInfo(data)
        })
    }
    function getReceiveInfo() {
        let api = url + "/receiveInfo/" + customerId;
        myAjax(api, "GET", null, (data) => {
            receiveInfo_model = data;
            renderReceiveInfo();
        })
    }
    function renderReceiveInfo() {
        let $tbody = $view.find("#receiveInfo tbody");
        $tbody.empty();
        receiveInfo_model.forEach((receiveInfo) => {
            $("<tr>")
                .append($("<td>").text(receiveInfo.id))
                .append($("<td>").text(receiveInfo.address))
                .append($("<td>").text(receiveInfo.phone))
                .append($("<td>")
                    .append($("<button class='btn btn-danger' type='button'>")
                        .text("删除")
                        .on("click",(e)=>deleteReceiveInfo(receiveInfo))
                    ))
                .append($("<td>")
                    .append($("<button class='btn btn-warning' type='button'>")
                        .text("修改")
                        .on("click",(e)=>{
                            $('#updateReceiveInfoAddress').val(receiveInfo.address);
                            $('#updateReceiveInfoPhone').val(receiveInfo.phone);
                            updateId = receiveInfo.id;
                            $('#updateReceiveInfoModal').modal('show');
                        })
                    ))
                .appendTo($tbody);
        })
    }
}

$(function () {
    let url = "/customer/";
    OrderComponent($("#view"), url, sessionStorage.getItem("customerId"));
})