    $(function () { 
        $("#registForm").on("submit",function (e) {
            e.preventDefault();
            if($("#password").val()!=$("#password1").val()){
        		  $("#error").text("俩次密码不一致！");
        	}
            let opt =new Object();
            opt.type="POST";
            opt.url="/typhoon_consuemer/regist";
            opt.success=function(result){
                if(result!=0){
                	window.location.href="/typhoon_consuemer/html/merchant.html";
                	sessionStorage.setItem('user',$("#username").val());
                }else{
                    $("#error").text("用户已存在！");
                }
            }
            opt.contentType="application/json";
            opt.data=JSON.stringify({'username':$("#username").val(),'password':$("#password").val()});
            $.ajax(opt)
        })
    })
