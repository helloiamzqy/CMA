$(function () {
    let mid = sessionStorage.getItem("mId");
    // let mid = "8a5e9d3d64feef560164feef5b9f0001";

    let url='http://localhost:8081/'+mid+'/basicInfo';
    merchantInfoComponent($("#app"),url);
})

function merchantInfoComponent($view, url) {
    let model=null;
    let cur=null;
    init();
    function init() {
        getData();
        $view.find("#merchantInfo").on("submit",(e)=>{
            e.preventDefault();
            if(!$("#pictureSrc").val()){
                alert("请为商家的添加图片！")
            }
        let obj=$(e.target).serializeObject();
        myAjax(url,"PUT",obj,()=>{
            sessionStorage.setItem("basicStatus","1");
            getData();
            alert("更改成功");
    });

    })
        $("#imgBtn").on("click",function (e) {
            e.preventDefault();
            uploadPic();
        });
    }
    function  getData(){
        myAjax(url,"GET",null,(basicInfo)=>{
            console.log(basicInfo);
        $("#shopName").val(basicInfo.shopName);
        $("#slogan").val(basicInfo.slogan);
        $("#delivery").val(basicInfo.delivery);
        $("#deliFee").val(basicInfo.deliFee);
        $("#opentime").val(getMyDate(basicInfo.openTime));
        $("#closetime").val(getMyDate(basicInfo.closeTime));
        $("#imgp").attr("src",basicInfo.picture);
        $("#hid").val(basicInfo.id);
        $("#pictureSrc").val(basicInfo.picture);
    });
    }
    function uploadPic() {
        var options = {
            url: "http://10.222.29.191:9091/picture/upload",
            type: "post",
            contentType: 'multipart/form-data',
            dataType: "json",
            success: function(data, status, xhr) {
                $("#imgp").attr("src",data[0].fileName);
                $("#pictureSrc").val(data[0].fileName);
            }
        };
        $("#imgForm").ajaxSubmit(options);
    }

    function getMyDate(str){
        var oDate = new Date(str),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth()+1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = getzf(oHour) +':'+
                getzf(oMin)//最后拼接时间
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