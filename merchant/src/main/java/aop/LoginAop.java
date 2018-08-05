package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pojo.Merchant;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author Dunn
 */
@Component
@Aspect
public class LoginAop {

    private final String POINT_CUT="execution(* service.MerchantManager.*(..))";
    //环绕
    @Around(value=POINT_CUT)
    public Object logArroud(ProceedingJoinPoint point) {
        Object obj = null;
        try {
            Merchant arg = (Merchant) point.getArgs()[0];
            arg.setPassword(EncoderByMd5(arg.getPassword()));
            obj = point.proceed(new Object[] {arg});
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }

    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
