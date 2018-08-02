$(function () {
        let nameReg=/^[a-zA-Z][a-zA-Z0-9_]{3,15}$/;
        let pswReg=/^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)\S{6,}$/;
        let errors=new Map();
        /////////////////////////////////////
        function validate(message) {
            errors.clear();
            $("#error").empty();
            if(message.name){
                if(!nameReg.test(message.name)){errors.set('name','请输入3到15位数字或字母');}
            }
            if(message.psw){
                if(!pswReg.test(message.psw)){errors.set('psw','用户名至少由数字/大写/小写字母中的两种组成，长度不小于6');}
            }
            renderError(errors);
        }
        function renderError(errors) {
            let nameSpan=$("#error");
            nameSpan.text(errors.get('name'));
            nameSpan.text(errors.get('psw'));
        }
        $("#registForm").on("submit",function (e) {
            e.preventDefault();
            if($("#password").val()!=$("#password1").val()){
                $("#error").text("两次输入的密码不一致！");
            }else{
            let opt =new Object();
            opt.type="POST";
            opt.url="/customer/regist";
            opt.success=function(result){
                if(result!=0){
//                    window.location.href="/customer/html/merchant.html";
                    sessionStorage.setItem('user',$("#userName").val());
                    let userId = $("#userName").val();
                    let shopId = sessionStorage.getItem("shopId");   
                    let foodCarts=$1.queryCart(shopId);
                    $1.updateUserCart(userId,shopId,foodCarts);
                    window.location.href="/customer/html/merchant.html";
                }else{
                    $("#error").text("用户已存在！");
                }
            }
            opt.contentType="application/json";
            opt.data=JSON.stringify({'username':$("#username").val(),'password':$("#password").val()});
            $.ajax(opt)
            }
           })

        $("#registForm").on("change",function () {
            let name=$("#username").val();
            let psw=$("#password").val();
            let message={name:name,psw:psw};
            validate(message);
        })


    })