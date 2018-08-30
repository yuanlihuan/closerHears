package hears;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.world.hears.account.bo.User;
import cn.com.world.hears.account.service.UserService;
import cn.com.world.hears.common.utils.contant.CommonConst;
import cn.com.world.hears.common.utils.property.PropertiesLoader;
import cn.com.world.hears.common.utils.verify.VerifyCode;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class Text {  
    private static Logger logger = Logger.getLogger(Text.class);  
//  private ApplicationContext ac = null;  
    @Resource(name = "userService")
    private UserService userService;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
        User user = userService.getUserById(1);  
        // System.out.println(user.getUserName());  
        // logger.info("ֵ��"+user.getUserName());  
        System.out.println(user.toString());
        logger.info(JSON.toJSONString(user));  
    }  
    
    @Test
    public void test4() {
    	PropertiesLoader propertiesLoader = new PropertiesLoader("config/common.properties");
    	String property = propertiesLoader.getProperty("accessKeyId");
    	System.out.println(propertiesLoader);
    }
    
    @Test
    public void test5() throws FileNotFoundException, IOException {
    	VerifyCode vc = new VerifyCode();//创建VerifyCode类的对象
    	BufferedImage bi = vc.getImage();//调用getImge()方法获得一个BufferedImage对象
    	VerifyCode.output(bi, new FileOutputStream("C:/develop/yzm.jpg"));//调用静态方法output()方法将图片保存在文件输出流中
    	System.out.println(vc.getText());//在控制台上打印验证码的文本值
    }
}  