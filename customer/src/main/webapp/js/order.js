        let userId=sessionStorage.getItem("user");
        let shopId = GetRequest().shop_id;
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
        window.onload=function () {
            if(!userId){
                window.location.href = "/typhoon_consuemer/html/login.html";
                return;
            }
            function renderDetail(foods) {
                let total =  0 ;
                for(let i=0;i<foods.length;i++){
                    let foodTr = '<tr><td width="50">'+foods[i].foodName+'</td>';
                    let imgTr = ' <td width="80"><img src="'+foods[i].picture+'" alt="" width="100"></td>';
                    let priceTr = ' <td width="20">￥'+foods[i].price+'</td>';
                    let numTr = '<td width="20">'+foods[i].num+'</td></tr>';
                    $("#fishDish").append(foodTr+imgTr+priceTr+numTr);
                    total += Number.parseInt(foods[i].price*foods[i].num);
                }
                
                document.getElementById("total").textContent= '总价 : ￥'+total;
               
            }
            function getData(userId,shopId){
                console.log(userId+shopId);
                let foods=$1.queryUserCart(userId,shopId);

                renderDetail(foods);
            }
            getData(userId,shopId);

        }
