package cn.itcast.user.service;

/**
 * 自定义一个异常类
 * 只是给出父类中的构造器即可！方便用来创建对象！
 * @author dongkaihua
 */
public class UserException extends Exception{
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

}
