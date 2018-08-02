    $(function () { 
        $("#loginForm").on("submit",function (e) {
            e.preventDefault();
            let opt =new Object();
            opt.type="POST";
            opt.url="/typhoon_consuemer/login";
            opt.success=function(result){
                if(result!=0){
                	window.location.href="/typhoon_consuemer/html/merchant.html";
                	sessionStorage.setItem('user',$("#userName").val());
                }else{
                    $("#error").text("用户名或密码错误！");
                }
            }
            opt.contentType="application/json";
            opt.data=JSON.stringify({'username':$("#userName").val(),'password':$("#password").val()});
            $.ajax(opt)
        })
    })
