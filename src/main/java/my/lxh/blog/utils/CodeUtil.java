package my.lxh.blog.utils;

/**
 * 验证码
 * @author lxh
 * @date 2021-01-28 16:26
 */
public class CodeUtil {

    public static String getCode(int n){
        char[] s ="abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(s[ (int) (Math.random() * s.length) ]);
        }
        return sb.toString();
    }

}
