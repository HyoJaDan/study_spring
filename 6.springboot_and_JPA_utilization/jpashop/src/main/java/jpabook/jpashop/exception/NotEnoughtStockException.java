package jpabook.jpashop.exception;

public class NotEnoughtStockException extends RuntimeException {

    //여기 아래부분은 command+N -> overrideMethod 작성 -> 위에 5개 가져왔다.
    public NotEnoughtStockException() {
        super();
    }

    public NotEnoughtStockException(String message) {
        super(message);
    }

    public NotEnoughtStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughtStockException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughtStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
