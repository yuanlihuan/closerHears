package cn.com.world.hears.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	public static String sendHttpRequest(String url, String data, String sendType) throws IOException {
        String result = "";
        URL requestUrl = new URL(url);
        HttpURLConnection httpConn = (HttpURLConnection) requestUrl.openConnection();
        if (sendType.equals("POST")) {
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod(sendType);
            httpConn.setRequestProperty("Content-type", "application/json;charset=UTF-8"); 
            httpConn.setUseCaches(false);
            httpConn.setInstanceFollowRedirects(true);
            OutputStream out = httpConn.getOutputStream();
            if (data != null) {
                out.write(data.getBytes("UTF-8"));
            }
            out.flush();
            out.close();
        } else {
            httpConn.connect();
        }

        int code = httpConn.getResponseCode();
        if (HttpsURLConnection.HTTP_OK == code) {
            result = IOUtils.toString(httpConn.getInputStream(), "UTF-8");
        }
        IOUtils.closeQuietly(httpConn.getInputStream());
        checkError(result);
        return result;
    }
    
    /**
     * 检查错误相应
     * @param responseString
     */
    private static void checkError(String responseString) {
        if (StringUtils.isEmpty(responseString)) {
            return;
        }
        JSONObject jt = JSONObject.parseObject(responseString);
        if (jt != null && jt.containsKey("status") && "error".equals(jt.getString("status"))) {
            throw new IllegalArgumentException(jt.getString("message"));
        }
    }
}
