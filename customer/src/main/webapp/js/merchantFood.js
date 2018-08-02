    let currentPage = 0;
    let pageSize = 8;
    let param = GetRequest().shop_id;
    let userId = sessionStorage.getItem('user');
    let foodsInCart = "";
    if(userId){
        foodsInCart = $1.queryUserCart(userId, param);
    }else{
        foodsInCart = $1.queryCart(param);
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
        $("#order").attr("href", "/typhoon_consuemer/html/order.html?shop_id=" + param);
        let url = "/typhoon_consuemer/findDishes?shop_id=" + param;
        let method = "POST";
        let headers = [{"key": "Content-Type", "value": "application/json"}];
        renderCart(foodsInCart);
        function renderTable(foods) {
            let maindiv = document.querySelector("#dishList");
            for (let i = 0; i < foods.length; i++) {
                if (i % 3 == 0) {
                    $("#dishList").append('<div class="row">');
                }
                let conDiv = '<div class="col-md-3"><div id="dish"><br><br><a href=""><div class="dishImg">';
                let img = ' <img src="' + foods[i].picture + '" alt="" width="180" height="100"></a>';
                let foodinfo = '<div class="dishDetail">' + foods[i].foodName + '<br>￥' + foods[i].price;
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
                    renderTable(data.dataList);
                    renderNav(data);
                }
            });
        }

        getData(method, url, null, headers);

        $.ajax({
            type: "POST",
            url: "/typhoon_consuemer/showRestaurantDetail",
            data: {shop_id: param},
            dataType: "json",
            success: function (data) {
                let html = '   &nbsp;<img src="' + data.picture + '" alt="..." class="img-thumbnail" width="140" height="140"><span>店名：' + data.shopName + "<span>";
                $("#imgDiv").append(html);
            }
        });

    }

    function clearCart(){
        $1.clearUserCart(userId,param);
        $1.clearCart(param);
        renderCart(foodsInCart);
    }
    function renderCart(dishs) {
        $("#dishCart").empty();
        if (dishs) {
            for (let i = 0; i < dishs.length; i++) {
                let cartHtml = '<div class="title"><span class="f1 scope1">'+dishs[i].foodName+'</span>';
                let numHtml = '<span class="f1  scope2"> <a class="f1 minus" href="#" id="cut'+i+'">-</a><div class="count">'+dishs[i].num+'</div>' +
                    '<a class="plus" href="#" id="add'+i+'">+</a></span>';
                let total="<span class=\"fr pri scope2\">￥"+(dishs[i].price*dishs[i].num)+"</span></div>";
                $("#dishCart").append(cartHtml+numHtml+total);
                $("#cut"+i).on('click', function () {
                    //if user is null
                    foodTemp = dishs[i];
                    $cartservice.reduceCart(foodTemp);
                    renderCart(foodsInCart);
                });

                $("#add"+i).on('click', function () {
                    //if user is null
                    foodTemp = dishs[i];
                    $cartservice.updateCart(foodTemp);
                    renderCart(foodsInCart);
                });
            }
        }
    }
    function renderNav(page){
        $("#navul").empty();
        //上一页按钮
        let firstLi = ' <li><a href="#" aria-label="Previous" id="preNav"><span aria-hidden="true">&laquo;</span></a></li>';
        $("#navul").append(firstLi);
        for(let i=0;i<page.totalPage;i++){
            let status = '';
            if(page.currentPage==i){
                status = 'active';
            }
            let navHtml = '<li class="'+status+'"><a href="#" id="page'+i+'">'+(i+1)+'<span class="sr-only">(current)</span></a></li>';
            $("#navul").append(navHtml);
            //绑定事件
            $("#page"+i).on('click',function () {
                currentPage = i;
                getData(method,url,null,headers);
            });
        }
        //下一页按钮
        let nextLi = '<li><a href="#" aria-label="Next" id="nextNav"><span aria-hidden="true">»</span></a></li>';
        $("#navul").append(nextLi);
        //为上下页绑定事件
        $("#nextNav").on('click',function () {
            currentPage = currentPage+1;
            getData(method,url,null,headers);
        });
        $("#preNav").on('click',function () {
            if(currentPage>0){currentPage = currentPage-1;}
            getData(method,url,null,headers);
        });
    }