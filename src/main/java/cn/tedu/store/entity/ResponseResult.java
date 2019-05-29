package cn.tedu.store.entity;

public class ResponseResult<T> {
	private Integer state;
	private String message;
	private T data;
	public  static final int STATE_OK=1;
	public  static final int STATE_ERR=-1;
	
	
	public ResponseResult() {
		super();
	}
	
	
	public ResponseResult(Integer state) {
		super();
		setState(state);
	}
	

	public ResponseResult(Integer state, String message) {
		super();
		setState(state);
		setMessage(message);
	}
	
	public ResponseResult(Exception e) {
		super();
		setState(STATE_ERR);
		setMessage(e.getMessage());
	}
	
	
	public ResponseResult(Integer state, String message, T data) {
		super();
		setData(data);
		setState(state);
		setMessage(message);
	}


	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
