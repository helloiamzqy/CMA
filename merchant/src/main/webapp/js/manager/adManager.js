$(function () {
    // let mid = sessionStorage.getItem("mId");
    let mid = sessionStorage.getItem("mId");

    let url='http://localhost:8081/'+mid+'/advertisement';
    adComponenr($("#adApp"),url);
})

function adComponenr($view, url) {
    let model=null;
    let cur=null;
    init();
    function init() {
        getData();
        $view.find("#applyAd").on("submit",(e)=>{
            e.preventDefault();
            let obj=$(e.target).serializeObject();
            myAjax(url,"POST",obj,()=>{
                getData();
            });

        })
        $view.find("#pic").on("change",()=>{
            uploadPic();
        })
    }
    function  getData(){
        myAjax(url,"GET",null,(ad)=>{

            $("#adtable").empty();
             for(var i=0;i<ad.length;i++){
                  $("#adtable").append('<tr><td><img src='+ad[i].picture+'></td>' +
                      '<td>'+ad[i].price+'</td></tr>');
             }
        });
    }
    function uploadPic() {
        var options = {
            url: "http://10.222.29.191:9091/picture/upload",
            type: "post",
            contentType: 'multipart/form-data',
            dataType: "json",
            success: function(data, status, xhr) {
                // $("#addImg").attr("src",data[0].fileName);
                $("#pictureSrc").val(data[0].fileName);
            }
        };
        $("#adImg").ajaxSubmit(options);
    }
}