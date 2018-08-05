let currentPage = 0;
let pageSize = 8;
let shopId = GetRequest().shop_id;
let userId = sessionStorage.getItem('user');
let foodsInCart = "";
if (userId) {
    foodsInCart = $1.queryUserCart(userId, shopId);
} else {
    foodsInCart = $1.queryCart(shopId);
}

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
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

window.onload = function () {
    $("#order").attr("href", "/customer/html/order.html?shop_id=" + shopId);
    let url = "/customer/food/findFoodByMerchant/" + shopId;
    let method = "POST";
    let headers = [{"key": "Content-Type", "value": "application/json"}];
    renderCart(foodsInCart);

    //刷新页面
    function renderTable(foods) {
        let maindiv = document.querySelector("#dishList");
        for (let i = 0; i < foods.length; i++) {
            if (i % 3 == 0) {
                $("#dishList").append('<div class="row">');
            }
            let conDiv = '<div class="col-md-3"><div id="dish"><br><br><a href=""><div class="dishImg">';
            let img = ' <img src="' + foods[i].picture + '" alt="" width="180" height="100"></a>';
            let foodinfo = '<div class="dishDetail"><strong>' + foods[i].foodName + '</strong><br><span class="foodPri">￥' + foods[i].price + "</span>";
            let spanButton = '<span class="addBtn glyphicon glyphicon-plus" id="' + foods[i].id + '"></span></div></div></div>'
            $("#dishList").append(conDiv + img + foodinfo + spanButton);
            if (i % 3 == 0) {
                $("#dishList").append('</div>');
            }
            $("#" + foods[i].id).on('click', function () {
                //if user is null
                let foodTemp = foods[i];
                $cartservice.updateCart(foodTemp);
                renderCart(foodsInCart);
            });
        }
    }

//获取商家信息
    function getData(method, url, data, headers) {
        $.ajax({
            type: method,
            url: url,
            dataType: "json",
            data: {
                currentPage: currentPage,
                pageSize: pageSize
            },
            success: function (data) {
                renderTable(data);
                // renderTable(data.dataList);
                // renderNav(data);
            }
        });
    }

    getData(method, url, null, headers);
    $.ajax({
        type: "POST",
        url: "/customer/merchant/showMerchantDetail",
        data: {shop_id: shopId},
        dataType: "json",
        success: function (data) {
            console.log(data);
            let html = '<div class="shopImg"><img src="' + data.picture + '" alt="..." class="img-thumbnail" width="200" height="160"><span>' + data.shopName + "<span></div>";
            $("#imgDiv").append(html);
            $("#shopTime").text(getBusinessTime(data.openTime) + '-' + getBusinessTime(data.closeTime));
            $("#deli_fee").text(data.deliFee);
            $("#slogan").text(data.slogan);
            if (data.rank != 0) {
                var result = data.rank.substring(0,3)
                $("#merchantRank").text(result);
            }else{
                $("#merchantRank").text("未评价");
            }
        }
    });
}

//清空购物车
function clearCart() {
    $1.clearUserCart(userId, shopId);
    $1.clearCart(shopId);
    renderCart(foodsInCart);
}

//以下为拼接购物车信息
function renderCart(dishs) {
    $("#dishCart").empty();
    if (dishs) {
        for (let i = 0; i < dishs.length; i++) {
            //遍历并将信息渲染到页面
            let cartHtml = '<div class="title"><span class="f1 scope1">' + dishs[i].foodName + '</span>';
            let numHtml = '<span class="f1  scope2"> <a class="f1 minus" href="#" id="cut' + i + '">-</a><div class="count">' + dishs[i].num + '</div>' +
                '<a class="plus" href="#" id="add' + i + '">+</a></span>';
            let total = "<span class=\"fr pri scope2\">￥" + (dishs[i].price * dishs[i].num) + "</span></div>";
            $("#dishCart").append(cartHtml + numHtml + total);
            //绑定减少按钮
            $("#cut" + i).on('click', function () {
                //if user is null
                foodTemp = dishs[i];
                $cartservice.reduceCart(foodTemp);
                renderCart(foodsInCart);
            });
            //绑定添加按钮
            $("#add" + i).on('click', function () {
                //if user is null
                foodTemp = dishs[i];
                $cartservice.updateCart(foodTemp);
                renderCart(foodsInCart);
            });
        }
    }
}

//分页
$("#selectSize").change(function () {
    pageSize = $("#selectSize").val();
    getData(method, url, null, headers);
})

function renderNav(page) {
    $("#navul").empty();
    //上一页按钮
    let firstLi = ' <li><a href="#" aria-label="Previous" id="preNav"><span aria-hidden="true">&laquo;</span></a></li>';
    $("#navul").append(firstLi);
    for (let i = 0; i < page.totalPage; i++) {
        let status = '';
        if (page.currentPage == i) {
            status = 'active';
        }
        let navHtml = '<li class="' + status + '"><a href="#" id="page' + i + '">' + (i + 1) + '<span class="sr-only">(current)</span></a></li>';
        $("#navul").append(navHtml);
        //绑定事件
        $("#page" + i).on('click', function () {
            currentPage = i;
            getData(method, url, null, headers);
        });
    }
    //下一页按钮
    let nextLi = '<li><a href="#" aria-label="Next" id="nextNav"><span aria-hidden="true">»</span></a></li>';
    $("#navul").append(nextLi);
    //为上下页绑定事件
    $("#nextNav").on('click', function () {
        currentPage = currentPage + 1;
        getData(method, url, null, headers);
    });
    $("#preNav").on('click', function () {
        if (currentPage > 0) {
            currentPage = currentPage - 1;
        }
        getData(method, url, null, headers);
    });
}