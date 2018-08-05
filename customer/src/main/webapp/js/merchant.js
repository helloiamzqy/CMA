window.onload=function () {
    let currentPage = 1;
    let pageSize = 4;
    let url="/customer/merchant/findBasicInfoByPager/";
    let method="POST";
    let headers=[{"key":"Content-Type","value":"application/json"}];
    function renderTable(merchants) {
        $("#merchants").empty();
        //拼接商家信息
        for(let i=0;i<merchants.length;i++){
            let colDiv='<a href="/customer/html/merchantFood.html?shop_id='+merchants[i].merchant.id+'">' +
            // let colDiv='<a href="/typhoon_consuemer/html/merchantFood.html/'+ merchants[i].merchant.id +'">' +
                '<div class="col-md-3"><div id="merchant"><br><br><div class="merchantImg">' +
                ' <img src="'+merchants[i].picture+'" alt="" width="180" height="100">' +
                '</div><br><div class="merchantDetail">' +
                ' &nbsp;&nbsp;<strong>'+merchants[i].shopName+'</strong><br> &nbsp;&nbsp;'+merchants[i].slogan+'<br>' +
                '</div></div></div></a>'
            $("#merchants").append(colDiv);
        }
    }
    //分页
    $("#selectSize").change(function(){
        pageSize = $("#selectSize").val();
        getData(method,url,null,headers);
    })


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
    $
    //以下为请求商家信息
    function getData(method,url,data,headers){
        $.ajax({
            type: method,
            url: url,
            dataType:"json",
            data: {
                currentPage: currentPage,
                pageSize: pageSize
            },
            success: function(data){
                renderTable(data.list);
                renderNav(data);
            }
        });
    }
    getData(method,url,null,headers);

    //以下为请求广告
    $.ajax({
        type: "GET",
        url: "/customer/ad/findAd",
        dataType: "json",
        success: function(data){
            for(let i=0;i<data.length;i++){
                //拼接轮播选择点
                if(i == 0){
                    $("#olnum").append("<li data-target='#carousel-example-generic' data-slide-to='0'  class='active'></li>")
                }else{
                    $("#olnum").append("<li data-target='#carousel-example-generic' data-slide-to='"+i+"'></li>")
                }
            }
            for(let i=0;i<data.length;i++){
                //拼接轮播图
                if(i == 0) {
                    $("#adver").append("<div class='item active'>" +'<a href="/customer/html/merchantFood.html?shop_id='+data[i].merchantId+'">'+
                        "<img src='" + data[i].picture + "' alt=''>" + "</a></div>");
                } else {
                    $("#adver").append("<div class='item'>" +'<a href="/customer/html/merchantFood.html?shop_id='+data[i].merchantId+'">'+
                        "<img src='" + data[i].picture + "' alt=''>" + "</a></div>");
                }
            }
        }
    });
}

