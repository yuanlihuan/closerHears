package cn.com.world.hears.account.web.client;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.world.hears.account.bo.Account;
import cn.com.world.hears.account.bo.AccountConst;
import cn.com.world.hears.account.bo.ExceptionConst;
import cn.com.world.hears.account.common.utils.JSONResult;
import cn.com.world.hears.account.service.AccountService;
import cn.com.world.hears.common.utils.NoteUtils;
import cn.com.world.hears.common.utils.contant.CommonConst;
import cn.com.world.hears.common.utils.property.CookiesUtils;
import cn.com.world.hears.common.utils.verify.VerifyCode;

@Controller
@RequestMapping("/")
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource(name = "accountService")
	private AccountService accountService;
	
	@RequestMapping("login.action")
	private ModelAndView login(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/client/account/login");
		logger.info("### logger success");  
		return view;
	}
	
	/**
	 * 用户提交登录
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = {"accountLogin.action", "/" }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	private Object accountLogin(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, Object> form) throws ServletException, IOException {
		ExceptionConst exceptionConst = new ExceptionConst();
		exceptionConst.setStatus("error");
		if (StringUtils.isEmpty(form.get("userName"))) {
			exceptionConst.setMessage("用户名为空");
			return exceptionConst;
		}
		if (StringUtils.isEmpty(form.get("password"))) {
			exceptionConst.setMessage("密码为空");
			return exceptionConst;
		}
		Account account = new Account();
		account.setName(form.get("userName").toString());
		account = accountService.selectByAccount(account);
		if (StringUtils.isEmpty(account)) {
			exceptionConst.setMessage("用户不存在");
			return exceptionConst;
		}
		if (account.getStatus().equals(AccountConst.FORBIDDEN_ACCOUNT_STATUS)) {
			exceptionConst.setMessage("用户已经禁用");
			return exceptionConst;
		}
		//TODO md5加密
		//String md5Password = SecurityUtils.MD5(form.get("password").toString().trim());
		if (!form.get("password").toString().trim().equals(account.getPassword())) {
			exceptionConst.setMessage("密码不一致");
			return exceptionConst;
		}
		exceptionConst.setStatus("success");
		exceptionConst.setUrl("/hears/seckill/list");
		//request.getRequestDispatcher("/seckill/seckillList.jsp").forward(request, response);   
		///return "redirect:/seckill/list";
		return exceptionConst;
	}
	
	/**
	 * 进入后台管理页面
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping("manage.action")
	public ModelAndView managerAction(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, Object> form) {
		
		ModelAndView view = new ModelAndView("/sever/account/manager");
		view.addAllObjects(form);
		return view;
	}
	
	/**
	 * 用户注册
	 */
	@RequestMapping("accountRegister.action")
	public ModelAndView accountRegister(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, Object> form) {
		
		ModelAndView view = new ModelAndView("/client/account/accountRegister");
		view.addAllObjects(form);
		return view;
	}
	
	/**
	 * 用户手机号注册获取验证码
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("getSecurityCode.action")
	public Object getSecurityCode(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> form) throws Exception {
		//获取session对象
		HttpSession session = request.getSession();
		session.getAttribute("");
		//获取手机号
		String phone = form.get("phone").toString();
		String name = "先生/女生";
		//生成验证码
		VerifyCode vc = new VerifyCode();
    	BufferedImage bi = vc.getImage();
    	//创建保存路径
    	String url = CommonConst.verify_code_url + System.currentTimeMillis();
    	VerifyCode.output(bi, new FileOutputStream(url));
    	System.out.println(vc.getText());
		String code = vc.getText();
		
		try {
			String aLiYunNote = NoteUtils.ALiYunNote(phone, name, code);
			if (aLiYunNote.equals("yes")) {
				String cookieName = "VerifyCode";
				CookiesUtils.setCookie(request, response, cookieName, code, 600, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
}
