function FoodComponent($view, url) {

    init();
    function init() {

        $view.find("#addForm").on("submit",(e)=>{
            if($("#aHiddenPicture").val()==""){
                alert("请提交照片");
            }
            e.preventDefault();
            let obj=$(e.target).serializeObject();
            myAjax(url,"POST",obj,(nc)=>{
                window.location="admin-waiting.html";
            })
        });

        $view.find("#upPicture").on("change",()=>{
            uploadPic();
        })

        myAjax(url,"GET",null,(data)=>{
            $("#address").val(data.address);
            $("#corporateName").val(data.corporateName);
            $("#creditCode").val(data.creditCode);
            $("#idCard").val(data.idCard);
            $("#phone").val(data.phone);
            $("#shopName").val(data.shopName);
            $("#picSrc").attr("src",data.picture);
            $("#aHiddenPicture").val(data.picture);
            if(sessionStorage.getItem("status")==3) {
                $("#block").css("display","block");
                $("#comments").text("您的申请信息被驳回！具体原因请咨询电话10086********");
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
                $("#picSrc").attr("src",data[0].fileName);
                $("#aHiddenPicture").val(data[0].fileName);
            }
        };
        $("#picForm").ajaxSubmit(options);
    }
}

$(function () {
    // let mid = sessionStorage.getItem("mId");
    let mid = sessionStorage.getItem("mId");
    let url='http://localhost:8081/'+mid+'/registinfo';
    FoodComponent($("#indexApp"),url);
})