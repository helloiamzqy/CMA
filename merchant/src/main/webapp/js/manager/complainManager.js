$(function () {
    let mid = sessionStorage.getItem("mId");
    // let mid = "8a5e9d3d650441a801650441ad8a0001";
    //http://10.222.29.192:9090/admin/message/complaint/8a5e9d3d650441a801650441ad8a0001
    let url='http://localhost:8081/'+mid+'/complain';
    adComponenr($("#adApp"),url);
})

function adComponenr($view, url) {
    let model=null;
    let cur=null;
    init();
    function init() {
        getData();
    }
    function  getData(){
        myAjax(url,"GET",null,(cp)=>{
            $("#cptable").empty();
            let ad = $.parseJSON( cp );
            for(let i in ad){
                $("#cptable").append('<tr><td>'+ad[i].reason+'</td><td>'+ad[i].createTime+'</td>' +
                    '</tr>');
            }
        });
    }
}