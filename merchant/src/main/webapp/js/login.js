function RegistComponent($view,host) {

    let error=new Map();
    let pswReg = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
    let nameReg=/^[a-zA-Z][a-zA-Z0-9_]{3,15}$/;

    init();
    function init() {
        $view.find("#RegName").on("blur",function () {
            if(!nameReg.test($("#RegName").val())){
                let nameError=$("#regist_name_error").text("字母开头，长度3-15");
                error.set("name",nameError);
            }else{
                error.delete("name");
                $("#regist_name_error").text("");
            }
        });
        $view.find("#RegPassword").on("blur",function () {
            if(!pswReg.test($("#RegPassword").val())){
                let pswError=$("#RegpswError").text("最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符")
                error.set("password",pswError);
            }else {
                error.delete("password");
                $("#RegpswError").text("");
            }
        });
        $view.find("#rpassword").on("blur",function () {
            if($("#rpassword").val()!=$("#RegPassword").val()){
                let rpswError=$("#noSame").text("密码不一致！");
                error.set("repassword",rpswError);
            }else {
                error.delete("repassword");
                $("#noSame").text("");
            }
        })
        $view.find("#register_form").on("submit",function (e) {
            e.preventDefault();
            if(error.size==0&&$("#rpassword").val()!=""&&$("#RegPassword").val()!=""&&$("#RegName").val()!=""){
                $.ajax({
                    type:"POST",
                    url:host+"/merchant/regist",
                    contentType:"application/json; charset=utf-8",
                    dataType:"json",
                    data:JSON.stringify({
                        name:$("#RegName").val(),
                        password:$("#RegPassword").val()
                    }),
                    success:function (data) {
                        alert("注册成功！")
                        $("#register_form").css("display", "none");
                        $("#login_form").css("display", "block");
                    }
                })
            }else {
                alert("请完善您的信息！！！")
            }
        })
        $view.find("#login_form").on("submit",function (e) {
            e.preventDefault();
            $.ajax({
                type:"POST",
                url:host+"/merchant/login",
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                data:JSON.stringify({
                    name:$("#loginName").val(),
                    password:$("#loginPsw").val()
                }),
                success:function (data) {
                    if (data.nameError==null&&data.pswError==null){
                        sessionStorage.setItem("mId",data.merchant.id);

                    }
                    $("#login_name_error").text(data.nameError);
                    $("#pswError").text(data.pswError);
                }
            })
        })
    }
}

$(function () {
    let host="http://localhost:8081";
    RegistComponent($("#app"),host);
})