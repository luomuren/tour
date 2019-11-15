package top.tmstarry.tour.base.service;

/**
 * @ClassName ServiceException
 * @Author 落幕人
 * <p>
 * 业务逻辑层异常处理
 * 暂时不知道干啥的
 * <p>
 * @Date 2019/10/26 13:33
 * @Version 1.0
 **/
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
