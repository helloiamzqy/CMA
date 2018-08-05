$(function () {
    setInterval('polling()',3000);
})

function polling() {
    $.ajax({
        type:'GET',
        url:'http://localhost:8081/8a5e9d3c6507f15e016507f166760000/newOrders',
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            if ($("#newOrder").text()!=data){
                alert("你有"+(data-$("#newOrder").text())+"条新订单")
            }
            $("#newOrder").text(data);
        }
    })
}

