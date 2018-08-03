function MerchantComponent($view, url) {
    let model = null;
    let cur;

    init();

    function init() {
        myAjax(url,"GET",null,(merchants)=>{
            model = merchants;
            render();
        })
    }

    function render() {
        let $tbody = $view.find("#merchantList tbody");
        $tbody.empty();
        model.forEach((merchant)=>{
            $("<tr>")
                .append($("<td>").text(merchant.creditCode))
                .append($("<td>").text(merchant.idCard))
                .append($("<td>").text(merchant.corporateName))
                .append($("<td>")
                    .append($("<img>")
                        .attr("src",merchant.picture)
                        .attr("width","45px")
                        .attr("height","40px")))
                .append($("<td>").text(merchant.phone))
                .append($("<td>").text(merchant.shopName))
                .append($("<td>").text(merchant.address))
                .append($("<td>").text(merchant.comments))
                .append($("<td>")
                    .append($("<button>")
                        .text("拉黑")
                        .addClass("btn btn-danger disgree")
                        .on("click",(e)=>changeStatus(merchant,2))))
                .appendTo($tbody)
        })

        function addBtn(type) {

        }
    }

    function changeStatus(merchant,status){
        cur = merchant;
        merchant.status = status;
        let url = "http://localhost:9090/admin/merchantInfo/updateStatus"
        let merchant1 = myAjax(url,"PUT",merchant,(e)=>{
            let index = model.indexOf(cur);
            model.splice(index,1);
            render();
        })
    }
}

$(function () {
    let url = "http://localhost:9090/admin/merchantInfo/status/1";
    MerchantComponent($("#app"),url);
})





// $(function () {
 //        let url="findagreemerchants";
 //        let method="GET";
 //        let headers=[{"key":"Content-Type","value":"application/json"}];
 //
 //        //渲染表格
 //        function renderTable(merchants) {
 //            let table=document.querySelector("#merchantList");
 //            for(let i=0;i<merchants.length;i++){
 //                let tr=$("<tr>");
 //                $("<td>").text(merchants[i].creditCode).appendTo(tr);
 //                $("<td>").text(merchants[i].idCard).appendTo(tr);
 //                $("<td>").text(merchants[i].corporateName).appendTo(tr);
 //                let imgtd=$("<td>");
 //                imgtd.appendTo(tr);
 //                $("<img>").attr("src",merchants[i].picture).attr("alt","picture").attr("width","45px").attr("height","40px").appendTo(imgtd);
 //                $("<td>").text(merchants[i].phone).appendTo(tr);
 //                $("<td>").text(merchants[i].shopName).appendTo(tr);
 //                $("<td>").text(merchants[i].address).appendTo(tr);
 //                $("<td>").text(merchants[i].comments).appendTo(tr);
 //                let td=$("<td>").appendTo(tr);
 //                $("<button>").text("拉黑").addClass("btn btn-danger disgree").on("click",function () {
 //                	changeStatus(merchants[i].shopId,3);
 //                }).appendTo(td);
 //                tr.attr("id",merchants[i].shopId).addClass("datas").appendTo(table);
 //            }
 //        }
 //
 //        //获取数据
 //        function getData(method,url,data,headers){
 //            $.ajax({
 //                type: method,
 //                url: url,
 //                data:"currentPage="+$("#currentPage").val()+"&pageSize="+$("#pageSize").val(),
 //                dataType:"json",
 //                success: function(data){
 //                	makePage(data);
 //                    renderTable(data.dataList);
 //                }
 //            });
 //        }
 //
 //        //改变状态
 //        function changeStatus(id,status) {
 //            $.ajax({
 //                type:'GET',
 //                url:'changemstatus',
 //                data:'id='+id+"&status=3",
 //                success(){
 //                	$(".datas").remove();
 //                	getData(method,url,null,headers);
 //                }
 //            })
 //        }
 //        //function reject(id) {
 //    	//$("#myModal").modal();
 //    	//$("#submit").on("click",function(){
 //    	//	$.ajax({
 //        //        type:'GET',
 //       //         url:'mreject',
 //         //       data:'id='+id+"&status=2"+"&reason="+$("#reason").val(),
 //       //         success(){
 //       //         	$(".datas").remove();
 //         //       	getData(method,url,null,headers);
 //        //        }
 //       //     })
 //    //	})
 //   // }
 //
 //
 //        getData(method,url,null,headers);
 //
 //        //渲染页数
 //        function makePage(data){
	//        	 let totalPage=data.totalPage;
	//     	 let totalCount=data.totalCount;
	//     	 $(".pagination").remove();
	//     	 let page=$("#page");
	// 		let ul=$("<ul>").addClass("pagination");
	// 		ul.appendTo(page);
	// 		let li1=$("<li>").addClass("lis").appendTo(ul);
	//         let a1=$("<a>").attr("href","#").attr("aria-label","Previous").appendTo(li1);
	//         $("<span>").attr("aria-hidden","true").text("首页").appendTo(a1);
	//     	 for(let i=0;i<totalPage;i++){
	//     		 let li=$("<li>").addClass("lis");
	//     		 $("<a>").attr("href","#").text(i+1).appendTo(li);
	//     		 li.appendTo(ul);
	//     	 }
	//     	 let li2=$("<li>").addClass("lis").appendTo(ul);
	//          let a2=$("<a>").attr("href","#").attr("aria-label","Previous").appendTo(li2);
	//          $("<span>").attr("aria-hidden","true").text("尾页").appendTo(a2)
	//     	 $("#totalPage").text(data.totalPage);
	//     	 $("#totalCount").text(data.totalCount);
	//     	 $(".lis").on("click",function(){
	//     		 if($(this).text()=="首页"){
	//     				 $("#currentPage").val(1);
	//     			 }else if($(this).text()=="尾页"){
	//     				 $("#currentPage").val(totalPage);
	//     			 }else{
	//     				 $("#currentPage").val($(this).text());
	//     			 }
	//     		 $(".datas").remove();
	//     		 getData(method,url,data,headers);
	//     	 })
 //     	}
 //
 //        //改变每页显示的数目
 //       $("#pageSize").on("blur",function () {
 //      	 	$(".datas").remove();
 //       		getData(method,url,null,headers);
 //       })
 //
 //
 //    })