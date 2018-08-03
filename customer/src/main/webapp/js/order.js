let userId=sessionStorage.getItem("user");
let shopId = GetRequest().shop_id;

function GetRequest() {
    var url = location.search; 
    //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
window.onload=function () {
    $("#submitOrder").on('click',()=>{
        let foods=$1.queryUserCart(userId,shopId);
        alert(JSON.stringify(foods))

        submitOrder(foods);
    })
    if(!userId){
        sessionStorage.setItem("shopId",shopId);
        window.location.href = "/customer/html/login.html";
        return;
    }
    function submitOrder(orderItems) {
        alert(orderItems)

        $.ajax({
            type: "POST",
            url: "/customer/orders/addOrder/"+shopId+"/"+userId,
            data: JSON.stringify(orderItems),
            contentType:"application/json",
            dataType: "json",
            success: function (data) {
                alert("123")
                $("#shopName").text(data.shopName);
            }
        });

    }

    //渲染订单信息
    function renderDetail(foods) {
        let total =  0 ;
        for(let i=0;i<foods.length;i++){
            let foodTr = '<tr><td width="50">'+foods[i].foodName+'</td>';
            let imgTr = ' <td width="80"><img src="'+foods[i].picture+'" alt="" width="100" height="80"></td>';
            let priceTr = ' <td width="20">￥'+foods[i].price+'</td>';
            let numTr = '<td width="20">'+foods[i].num+'</td></tr>';
            $("#fishDish").append(foodTr+imgTr+priceTr+numTr);
            total += Number.parseInt(foods[i].price*foods[i].num);
        }

        document.getElementById("total").textContent= '￥'+total;

    }
    //从localstorage获取订单信息
    function getData(userId,shopId){
        console.log(userId+shopId);
        let foods=$1.queryUserCart(userId,shopId);

        renderDetail(foods);
    }
    getData(userId,shopId);
    //获取商品具体信息
    $.ajax({
        type: "POST",
        url: "/customer/showRestaurantDetail",
        data: {shop_id: shopId},
        dataType: "json",
        success: function (data) {
            $("#shopName").text(data.shopName);
        }
    });

}
