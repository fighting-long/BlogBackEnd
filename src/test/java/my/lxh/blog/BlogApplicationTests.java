package my.lxh.blog;


import my.lxh.blog.utils.CodeUtil;
import org.apache.commons.codec.digest.Md5Crypt;

class BlogApplicationTests {

    public static void main(String[] args) {
        // $apr1$123$6yDGZPXBpSqQ7dLnPLNmU.
        String code = CodeUtil.getCode(8);
        System.out.println(code);
        System.out.println(Md5Crypt.apr1Crypt("343932572",code));
    }

}
