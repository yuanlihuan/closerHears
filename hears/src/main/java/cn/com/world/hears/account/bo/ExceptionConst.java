package cn.com.world.hears.account.bo;

public class ExceptionConst {
	
	private String status;
	
	private String message;
	
	private String url;
	
	public void getSuccess(String status, String url) {
		this.status = status;
		this.url = url;
	}
	
	public void getError(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
