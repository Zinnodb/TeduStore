package cn.tedu.store.service.ex;

public class DataNotException extends ServiceException {

	private static final long serialVersionUID = 947659844329405260L;

	public DataNotException() {
		super();
	}

	public DataNotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataNotException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotException(String message) {
		super(message);
	}

	public DataNotException(Throwable cause) {
		super(cause);
	}
			
}
