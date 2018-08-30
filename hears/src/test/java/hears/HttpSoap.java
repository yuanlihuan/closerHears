package hears;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSoap {
	
	 public static void translate(String word ) throws Exception {
	        //地址
	        String urlString = "http://www.webxml.com.cn/WebServices/TranslatorWebService.asmx ";
	        //方法
	        String soapActionString = "http://WebXml.com.cn/getEnCnTwoWayTranslator";
	        URL url = new URL(urlString);
	        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	        //拼接请求体,此处也可以在外面写xml文件然后读取,但是为了方便一个文件搞定,而且参数也比较好传递我们直接String拼接(直接将网页上的复制进来即可)
	        String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
	                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
	                "    <soap:Body>\n" +
	                "        <getEnCnTwoWayTranslator xmlns=\"http://WebXml.com.cn/\">\n" +
	                "            <Word>" + word + "</Word>\n" +
	                "        </getEnCnTwoWayTranslator>\n" +
	                "    </soap:Body>\n" +
	                "</soap:Envelope>";
	        byte[] buf = soap.getBytes();
	        //设置一些头参数
	        httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
	        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
	        httpConn.setRequestProperty("soapActionString", soapActionString);
	        httpConn.setRequestMethod("POST");
	        //输入参数和输出结果
	        httpConn.setDoOutput(true);
	        httpConn.setDoInput(true);
	        OutputStream out = httpConn.getOutputStream();
	        out.write(buf);
	        out.close();

	        //最后合格解析结果大家就各显神通了,此处打印出解析的过程,最终得到翻译答案
	        byte[] datas = readInputStream(httpConn.getInputStream());
	        String result = new String(datas);
	        System.out.println(httpConn.getResponseCode());
	        System.out.println("result:" + result);
	        System.out.println(result.substring(result.indexOf("string") - 1,result.lastIndexOf("string") + 7));
	        System.out.println(result.substring(result.indexOf("string") - 1,result.lastIndexOf("string") + 7).replaceAll("</{0,1}(string)?>",""));
	    }

	    /**
	     * 从输入流中读取数据
	     *
	     * @param inStream
	     * @return
	     * @throws Exception
	     */
	    public static byte[] readInputStream(InputStream inStream) throws Exception {
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while ((len = inStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, len);
	        }
	        byte[] data = outStream.toByteArray();
	        outStream.close();
	        inStream.close();
	        return data;
	    }

	    public static void main(String[] args) throws Exception {
	        translate("sea");
	    }
	}