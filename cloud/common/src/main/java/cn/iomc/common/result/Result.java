package cn.iomc.common.result;

import lombok.Data;

/**
 * @Author song
 * @Date 2024/3/28 03:56
 * @Version 1.0
 */
@Data
public class Result<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private Long timestamp;

    private T data;

    /*
    构造方法私有化，里面的方法都为静态方法
    达到保护属性的作用
     */
    private Result(){
        this.timestamp = System.currentTimeMillis();
    }
    /*
    使用链式编程
    该部分在项目组中一般是项目组长写好的
     */
    public static Result OK(){
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result ERROR(){
        Result result=new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());
        return result;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(T t){
        this.setData(t);
        return this;
    }

}