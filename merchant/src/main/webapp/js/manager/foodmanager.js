function FoodComponent($view, url) {
    let model=null;
    let cur=null;
    let page = 1;
    let row = 100;
    let pageTotal = 1;
    let pageRows = 10;
    init();
    function init() {
        $("#addFoodBtn").on("click",function(){
            $("#foodAddDiv").css("display","block");
        });
        $view.find("#addForm").on("submit",(e)=>{
            e.preventDefault();
            let obj=$(e.target).serializeObject();
            myAjax(url,"POST",obj,(nc)=>{
                model.push(nc);
                render();
                $("#foodAddDiv").css("display","none");
            })
        });
            myAjax(url+"?curPage="+page+"&pageSize="+row,"GET",null,(foods)=>{
            console.log(foods);
            $("#nums").text("共有:"+foods.rowsTotal+"条数据");
            model=foods.list;
            render();
        });
        $view.find("#updateForm").on("submit",(e)=>{
            e.preventDefault();
            let obj=$(e.target).serializeObject();
            myAjax(url,"PUT",obj,(nc)=>{
                console.log(nc);
                let index=model.indexOf(cur);
                model.splice(index,1,nc);
                render();
                $("#foodUpdateDiv").css("display","none");
            });

        })

        $view.find("#fileInput").on("change",()=>{
            uploadPic();
        })
        $view.find("#fileInput1").on("change",()=>{
            uploadPic1();
        })
    }

    function uploadPic() {
        var options = {
            url: "http://10.222.29.191:9091/picture/upload",
            type: "post",
            contentType: 'multipart/form-data',
            dataType: "json",
            success: function(data, status, xhr) {
                $("#addImg").attr("src",data[0].fileName);
                $("#aHiddenPicture").val(data[0].fileName);
                $("#comments").val(data[0].comments);
            }
        };
            $("#uploadFile").ajaxSubmit(options);
            render();
    }

    function uploadPic1() {
        var options = {
            url: "http://10.222.29.191:9091/picture/upload",
            type: "post",
            contentType: 'multipart/form-data',
            dataType: "json",
            success: function(data, status, xhr) {
                $("#uImg").attr("src",data[0].fileName);
                $("#uPicture").val(data[0].fileName);
                $("#ucomments").val(data[0].comments);
            }
        };
        $("#uuploadFile").ajaxSubmit(options);
        render();
    }

    function render() {
        let $tbody=$view.find("#foodLi");
        $tbody.empty();

        model.forEach((food)=>{
            $("<li>")
                .append($("<img class=\"am-img-thumbnail am-img-bdrs\">").attr("src",food.picture))
                .append($("<div class=\"gallery-title\">").text("菜名:"+food.foodName))
                .append($("<div class=\"gallery-desc\">").text("价格："+food.price))
                .append($("<td>").append($("<button>").text("删除").on("click",(e)=>deleteCustomer(food))))
                .on("dblclick",(e)=>{
                    selectFood(food);
                })
                .append($("<td>").append($("<button>").text("修改").on("click",function (){
                    $("#foodUpdateDiv").css("display","block");
                    $("#ufoodName").val(food.foodName);
                    $("#uImg").attr("src",food.picture);
                    $("#uprice").val(food.price);
                    $("#uPicture").val(food.picture);
                    $("#ucomments").val(food.comments);
                    $("#uid").val(food.id);
                })))
                .on("dblclick",(e)=>{
                    selectFood(food);
                })
                .appendTo($tbody)
        })
    }
    function selectFood(food) {
        cur=food;
        let cnameInput=$view.find("#updateForm input[name=cname]");
        cnameInput.val(food.cname);
    }
    function deleteCustomer(food) {
        myAjax(url+"/"+food.id,"DELETE",null,(msg)=>{
            if(msg.error){
                alert("删除异常")
            }
            else {
                let index=model.indexOf(food);
                model.splice(index,1);
                render();
            }
        })
    }
}

$(function () {
    let mid = sessionStorage.getItem("mId");
    // let mid = "8a5e9d3d65037c800165037c86140001";
    let url='http://localhost:8081/'+mid+'/food';
    FoodComponent($("#app"),url);
})