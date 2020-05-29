package groove.wind.me.event.web.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    SUCCESS(200, "操作成功"),
    FAIL(505, "操作失败"),
    ERROR(500, "系统错误，请联系管理员"),

    PARAM_ERR(400, "传参错误"),

    CREATE_WRONG(3001, "新增失败"),
    MODIFY_LOSE_ID(3002, "编辑失败，缺少主键"),

    FILE_UPLOAD_ERR(4001, "文件上传失败"),
    NOT_SUPPORT_FILE_TYPE(4002, "不支持此类型文件上传"),

    ;

    private int code;
    private String message;


}
