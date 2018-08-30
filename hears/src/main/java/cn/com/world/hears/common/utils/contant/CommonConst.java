package cn.com.world.hears.common.utils.contant;

import cn.com.world.hears.common.utils.property.PropertiesLoader;


public interface CommonConst {
	
	PropertiesLoader propertiesLoader = new PropertiesLoader("config/common.properties");
	
	//获取验证码图片的上传路径
	String verify_code_url = propertiesLoader.getProperty("verifyCodeUrl");
}
