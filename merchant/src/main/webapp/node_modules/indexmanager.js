$(function () {
    let mid = sessionStorage.getItem("mId");
    let cpUrl='http://localhost:8081/'+mid+'/orders/0?curPage=1&pageSize=10000';
    let pfUrl='http://localhost:8081/'+mid+'/basicInfo';
    let orURl='http://localhost:8081/'+mid+'/orders?curPage=1&pageSize=10000';
    let fdUrl = 'http://localhost:8081/'+mid+'/food?curPage=1&pageSize=10000';
    let cmUrl = 'http://localhost:8081/'+mid+'/comment?curPage=1&pageSize=10000';
    adComponenr($("#indexApp"),cpUrl,orURl,fdUrl,cmUrl,pfUrl);
})

function adComponenr($view, cpUrl,orURl,fdUrl,cmUrl,pfUrl) {
    let model=null;
    let cur=null;
    init();
    function init() {
        getData();
    }
    function  getData(){
        myAjax(cpUrl,"GET",null,(cp)=>{
            $("#orderTable").empty();
            $("#cpNum").text(cp.rowsTotal);
            let list = cp.list;
            console.log(list);
            for(let i = 0;i<list.length;i++){
                $("#orderTable").append('<tr><td>'+(i+1)+'</td><td>'+list[i].id+'</td><td>'+getMyDate(list[i].createTime)+'</td><td>'+list[i].phone+'' +
                    '</a></td><td>'+list[i].address+'</td><td><span class="am-badge am-badge-success">'+list[i].totalPrice+'</span></td></tr>');
            }
        });
        myAjax(orURl,"GET",null,(cp)=>{
            $("#orderNum").text(cp.rowsTotal);
        });
        myAjax(fdUrl,"GET",null,(cp)=>{
            $("#foodNum").text(cp.rowsTotal);
        });

        myAjax(cmUrl,"GET",null,function (cp) {
            $("#commentUl").empty();
            let list = cp.list;
            for(let i=0;i<list.length;i++){
                $("#commentUl").append(' <li class="am-comment">  <a href="#"><img src="http://amui.qiniudn.com/bw-2014-06-19.jpg?imageView/1/w/96/h/96" alt="" class="am-comment-avatar" width="48" height="48"></a>' +
                    ' <div class="am-comment-main"> <header class="am-comment-hd">  <div class="am-comment-meta"><a href="#" class="am-comment-author">某人</a></div></header>' +
                    '<div class="am-comment-bd"><p>'+list[i].content+'</p></div></div></li>');
            }

        });

        $.ajax({
            type: 'GET',
            url: pfUrl,
            contentType: 'application/json;charset=utf-8',
            success: function (cp) { //返回json结果
                $("#pfNum").text(cp.remark);
                $("#SN").text(cp.shopName);
            },
            error:function (data) {
                alert("请去商家界面完善您的信息，否则客户将看不到您的信息")
            }
        });

        // myAjax(pfUrl,"GET",null,(cp)=>{
        //     $("#pfNum").text(cp.remark);
        //     $("#SN").text(cp.shopName);
        // });
    }

    function getMyDate(str){
        var oDate = new Date(str),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth()+1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+
                getzf(oMin) +':'+getzf(oSen);//最后拼接时间
        return oTime;
    };
    //补0操作
    function getzf(num){
        if(parseInt(num) < 10){
            num = '0'+num;
        }
        return num;
    }
}