package my.lxh.blog.enums;

import lombok.Getter;

/**
 * @author lxh
 * @date 2020-12-15 10:40
 */
@Getter
public enum OperateResult {
    // 操作成功返回的消息
    SUCCESS("操作成功!",200),
    // 系统错误返回的消息
    ERROR("系统错误！！！",500),
    // 操作失败返回消息
    FAILURE("操作失败!",250);

    private final String msg;
    private final Integer code;
    OperateResult(String msg, Integer code){
        this.msg=msg;
        this.code=code;
    }
}
