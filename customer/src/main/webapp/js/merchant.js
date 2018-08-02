    let currentPage = 0;
    let pageSize = 8;
    window.onload=function () {
        let url="http://localhost:8081/typhoon_consuemer/showRestaurants";
        let method="POST";
        let headers=[{"key":"Content-Type","value":"application/json"}];
        function renderTable(merchants) {
            $("#merchants").empty();
            for(let i=0;i<merchants.length;i++){

                let colDiv='<a href="/typhoon_consuemer/html/merchantFood.html?shop_id='+merchants[i].shopId+'">' +
                    '<div class="col-md-3"><div id="merchant"><br><br><div class="merchantImg">' +
                    ' <img src="'+merchants[i].picture+'" alt="" width="180" height="100">' +
                    '</div><br><div class="merchantDetail">' +
                    ' &nbsp;&nbsp;'+merchants[i].shopName+'<br> &nbsp;&nbsp;'+merchants[i].slogan+'<br>' +
                    '</div></div></div></a>'
                $("#merchants").append(colDiv);
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

        function getData(method,url,data,headers){
            $.ajax({
                type: method,
                url: url,
                dataType:"json",
                data: {currentPage:currentPage,
                    pageSize:pageSize},
                success: function(data){
                    renderTable(data.dataList);
                    renderNav(data);
                }
            });
        }
        getData(method,url,null,headers);

        $.ajax({
            type: "GET",
            url: "/typhoon_consuemer/getAdvertisement",
            dataType: "json",
            success: function(data){
                for(let i=0;i<data.length;i++){
                    if(i == 0){
                        $("#olnum").append("<li data-target='#carousel-example-generic' data-slide-to='0'  class='active'></li>")
                    }else{
                        $("#olnum").append("<li data-target='#carousel-example-generic' data-slide-to='"+i+"'></li>")
                    }
                }
                for(let i=0;i<data.length;i++){
                    if(i == 0) {
                        $("#adver").append("<div class='item active'>" +'<a href="/typhoon_consuemer/html/merchantFood.html?shop_id='+data[i].shopId+'">'+
                            "<img src='" + data[i].picture + "' alt=''>" + "</a></div>");
                    } else {
                        $("#adver").append("<div class='item'>" +'<a href="/typhoon_consuemer/html/merchantFood.html?shop_id='+data[i].shopId+'">'+
                            "<img src='" + data[i].picture + "' alt=''>" + "</a></div>");
                    }
                }
            }
        });
    }

