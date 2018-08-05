$(function () {
    let init = -1;
    sessionStorage.setItem("init",-1);
    polling();
    setInterval('polling()',3000);
})

function polling() {
    let mid = sessionStorage.getItem("mId");
    $.ajax({
        type:'GET',
        url:'http://localhost:8081/'+mid+'/newOrders',
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            let init = sessionStorage.getItem("init");
            if(init==-1){
                sessionStorage.setItem("init",data);
            }else{
                if (init<data){
                    alert("你有"+(data-init)+"条新订单");
                     init = data;
                    sessionStorage.setItem("init",init);
                }
                $("#newOrder").text(data);
            }
        }
    })
}
