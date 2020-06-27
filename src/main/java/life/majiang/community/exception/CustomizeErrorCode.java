package life.majiang.community.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"找的问题不在了"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NOT_LOGIN(2003,"当前操作未登录，请登录后重试"),
    SYS_ERROR(2004,"服务器冒烟了，要不你再试一下"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了，稍后再试"),
    COMMENT_IS_EMPTY(2007,"评论内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"你这是读别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009,"消息不见了");

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
