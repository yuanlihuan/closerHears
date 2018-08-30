package cn.com.world.hears.account.common.utils;

/**
 * action 返回json串
 * @author liulai
 */
public class JSONResult {

	/**
     * 成功消息
     * @return string
     */
    public static final String success() {
        return "{\"status\" : \"success\", \"message\" : \"\"}";
    }
    
    /**
     * 带信息的成功消息
     * @param message 消息
     * @return string 
     */
    public static final String success(String message) {
        return "{\"status\" : \"success\", \"message\" : \"" + message + "\"}";
    }
    
    /**
     * 带信息和对象的成功消息
     * @param message 消息
     * @param model 转为json的对象
     * @return string 
     */
    public static final String success(String message, Object data) {
        if (data == null) {
            return JSONResult.success(message);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"status\" : \"success\", \"message\" : \"\", \"data\" : ");
        sb.append(GsonUtils.toJson(data));
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * 带信息的成功消息
     * @param message 消息
     * @return string 
     */
    public static final String success(Object data) {
        if (data == null) {
            return JSONResult.success();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"status\" : \"success\", \"message\" : \"\", \"data\" : ");
        sb.append(GsonUtils.toJson(data));
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * 带信息的消息
     * @param message 消息
     * @return string 
     */
    public static final String data(Object data) {
    	if (data == null) {
    		return JSONResult.success();
    	}
    	return GsonUtils.toJson(data);
    }
    
    /**
     * 错误消息
     * @return string
     */
    public static final String error() {
        return "{\"status\" : \"error\", \"message\" : \"\"}";
    }
    
    /**
     * 带消息的错误消息
     * @param message 消息
     * @return string 
     */
    public static final String error(String message) {
        return "{\"status\" : \"error\", \"message\" : \"" + message + "\"}";
    }

    /**
     * 重新登录消息
     * @return string
     */
    public static final String noLogin() {
        return "{\"status\" : \"nologin\", \"message\" : \" \"}";
    }

    /**
     * 重新登录消息
     * @param message 消息
     * @return string
     */
    public static final String noLogin(String message) {
        return "{\"status\" : \"nologin\", \"message\" : \"" + message + "\"}";
    }
    
}
