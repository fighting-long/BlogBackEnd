package my.lxh.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author lxh
 * @date 2020-12-09 21:08
 */
public class JwtUtil {
    public static final String SECRET="$JFKDN#$}@<S12";

    /**
     * 传入要加到token中的信息，生成token并返回
     * @param map
     * @param day
     * @return
     */
    public static String getToken(Map<String ,Object> map,Integer day){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->builder.withClaim(k,v.toString()));
        //给token设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH,day);
        builder.withExpiresAt(instance.getTime());
        //---------
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证token，可以从返回值中获取token中的信息
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
}
