(function (global) {
    let cartService={};

    //update cart
    cartService.updateCart = function updateCart(foodTemp) {
    	//判断是否登录
        if (!userId) {
        	//以下为登录用户
        	//若购物车为空则直接添加food
            if (!foodsInCart) {
                foodsInCart = [];
                foodTemp.num = 1;
                foodsInCart.push(foodTemp);
                $1.updateCart(shopId, foodsInCart);
            } else {
            	//若购物车不为空，则查找是否有其，若有则加一
                let flag = -1;
                for (let z = 0; z < foodsInCart.length; z++) {
                    if (foodsInCart[z].id == foodTemp.id) {
                        flag = z;
                        break;
                    }
                }
                if (flag != -1) {
                    foodsInCart[flag].num++;
                    $1.updateCart(shopId, foodsInCart);
                } else {
                    foodTemp.num = 1;
                    foodsInCart.push(foodTemp);
                    $1.updateCart(shopId, foodsInCart);
                }
            }

        } else {
        	//以下为未登录用户
        	//若购物车为空则直接添加food
            if (!foodsInCart) {
                foodsInCart = [];
                foodTemp.num = 1;
                foodsInCart.push(foodTemp);
                $1.updateUserCart(userId, shopId, foodsInCart);
            } else {
                let flag = -1;
                for (let z = 0; z < foodsInCart.length; z++) {
                    if (foodsInCart[z].id == foodTemp.id) {
                        flag = z;
                        break;
                    }
                }
                if (flag != -1) {
                    foodsInCart[flag].num++;
                    $1.updateUserCart(userId, shopId, foodsInCart);
                } else {
                    foodTemp.num = 1;
                    foodsInCart.push(foodTemp);
                    $1.updateUserCart(userId, shopId, foodsInCart);
                }
            }
        }
    }
    //reducecart
    cartService.reduceCart = function updateCart(foodTemp) {
    	//购物车减少操作
        if (userId) {
        	//以下为登录用户操作
        	//若购物车减少为0则直接删除
                let flag = -1;
                for (let z = 0; z < foodsInCart.length; z++) {
                    if (foodsInCart[z].id == foodTemp.id) {
                        flag = z;
                        break;
                    }
                }
                //若不为零则-1
                if (flag != -1) {
                	foodsInCart[flag].num = foodsInCart[flag].num-1;
                    if(foodsInCart[flag].num==0)foodsInCart.splice(flag,1);
                    $1.updateUserCart(userId,shopId, foodsInCart);
                }
        } else {
        	//以下为登录游客操作
        	//若购物车减少为0则直接删除
                let flag = -1;
                for (let z = 0; z < foodsInCart.length; z++) {
                    if (foodsInCart[z].id == foodTemp.id) {
                        flag = z;
                        break;
                    }
                }
                //若不为零则-1
                if (flag != -1) {
                	foodsInCart[flag].num = foodsInCart[flag].num-1;
                    if(foodsInCart[flag].num==0)foodsInCart.splice(flag,1);
                    $1.updateCart(shopId, foodsInCart);
                }
           
        }
    }

    global.$cartservice=cartService;
})(window);