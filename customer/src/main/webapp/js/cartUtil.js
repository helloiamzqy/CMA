(function (global) {
    let harper={};

    //某用户添加某店铺的菜品到购物车
    harper.updateUserCart=function (userId,shopId,foods) {
        let id=userId+shopId;
        localStorage.setItem(id,JSON.stringify(foods));
    }

    //查询某用户在某店铺里的购物车
    harper.queryUserCart =function (userId,shopId) {
        let id=userId+shopId;
        if(localStorage.getItem(id)!=""){
        return JSON.parse(localStorage.getItem(id));
        }
    }

    //清空某用户在某店铺的购物车
    harper.clearUserCart =function (userId,shopId) {
        let id=userId+shopId;
        localStorage.setItem(id,"");
    }
    harper.clearCart =function (shopId) {
        sessionStorage.setItem(shopId,"");
    }

    //游客添加某店铺的菜品到购物车
    harper.updateCart =function (shopId,foods) {
        sessionStorage.setItem(shopId,JSON.stringify(foods));
    }

    //查询游客在某店铺里的购物车
    harper.queryCart =function (shopId) {
        if(sessionStorage.getItem(shopId)!=""){
        return JSON.parse(sessionStorage.getItem(shopId));
        }
    }

    global.$1=harper;
})(window);