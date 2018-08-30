package hears;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUrlConnertion {
	
	
	public static void aaa () throws Exception {  
        String urlString = "http://WebXml.com.cn/qqCheckOnline";  
        String xmlFile = "C:/xinyuanyj/asd.xml";// 要发送的soap格式文件  
        //String soapActionString = "http://WebXml.com.cn/getWeatherbyCityName";//Soap 1.1中使用  
        URL url = new URL(urlString);  
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();  
        File fileToSend = new File(xmlFile);  
        byte[] buf = new byte[(int) fileToSend.length()];// 用于存放文件数据的数组  
        new FileInputStream(xmlFile).read(buf);  
//      httpConn.setRequestProperty("Content-Length",  
//              String.valueOf(buf.length));//这句话可以不用写，即使是随便写  
        //根据我的测试，过去的请求头中的Content-Length长度也是正确的，也就是说它会自动进行计算  
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");  
         //httpConn.setRequestProperty("soapActionString",soapActionString);//Soap  
        httpConn.setRequestMethod("POST");  
        httpConn.setDoOutput(true);  
        httpConn.setDoInput(true);  
        OutputStream out = httpConn.getOutputStream();  
        out.write(buf);  
        out.close();  
        InputStreamReader is = new InputStreamReader(httpConn.getInputStream(),  
                "utf-8");  
        BufferedReader in = new BufferedReader(is);  
        String inputLine;  
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream("result.xml")));// 将结果存放的位置  
        while ((inputLine = in.readLine()) != null) {  
            System.out.println(inputLine);  
            bw.write(inputLine);  
            bw.newLine();  
        }  
        bw.close();  
        in.close();  
        httpConn.disconnect();  
    }  
	
	
	 public static String doJsonPost(String urlPath, String Json) {
	        // HttpClient 6.0被抛弃了
	        String result = "";
	        BufferedReader reader = null;
	        try {
	            URL url = new URL(urlPath);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestProperty("Connection", "Keep-Alive");
	            conn.setRequestProperty("Charset", "UTF-8");
	            // 设置文件类型:
	            conn.setRequestProperty("Content-Type","text/xml; charset=UTF-8");
	            conn.setRequestProperty("Host", "www.webxml.com.cn");
	            conn.setRequestProperty("Content-Length", "length");
	            // 设置接收类型否则返回415错误
	          //conn.setRequestProperty("accept","application/json");
	            // 往服务器里面发送数据
	            if (Json != null && !StringUtils.isEmpty(Json)) {
	                byte[] writebytes = Json.getBytes();
	                // 设置文件长度
	                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
	                OutputStream outwritestream = conn.getOutputStream();
	                outwritestream.write(writebytes);
	                outwritestream.flush();
	                outwritestream.close();
	            }
	            System.out.println(conn.getResponseCode());
	            if (conn.getResponseCode() == 200) {
	                reader = new BufferedReader(
	                        new InputStreamReader(conn.getInputStream()));
	                result = reader.readLine();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return result;
	    }
	 
	 public static void main(String[] args) {
		 try {
			aaa();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 /*String word = "ssdsd";
		 String  urlPath = "http://WebXml.com.cn/qqCheckOnline";	
		 String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
	        		"          <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
	        		"            <soap:Body>\n" + 
	        		"              <qqCheckOnline xmlns=\"http://WebXml.com.cn/\">\n" + 
	        		"                <qqCode>string</qqCode>\n" + 
	        		"              </qqCheckOnline>\n" + 
	        		"            </soap:Body>\n" + 
	        		"          </soap:Envelope>";
		 String doJsonPost = doJsonPost(urlPath, soap);
		 System.out.println(doJsonPost);*/
	}
}
