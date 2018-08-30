package cn.com.world.hears.seckill.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.world.hears.seckill.bo.Seckill;
import cn.com.world.hears.seckill.dto.Exposer;
import cn.com.world.hears.seckill.dto.SeckillExecution;
import cn.com.world.hears.seckill.dto.SeckillResult;
import cn.com.world.hears.seckill.enums.SeckillStatEnum;
import cn.com.world.hears.seckill.exception.RepeatKillException;
import cn.com.world.hears.seckill.exception.SeckillCloseException;
import cn.com.world.hears.seckill.service.SeckillService;

/**
 * 秒殺系統
 * @author liulai
 */
@Controller
@RequestMapping("/seckill")//url:麼快／資源/{id}細分
public class SeckillController {

	private Logger log = LoggerFactory.getLogger(SeckillController.class);
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView skillList(Model model) {
		ModelAndView mv = new ModelAndView("seckill/seckillList");
		//获取列表
		List<Seckill> seckillList = seckillService.getSeckillList();
		mv.addObject("seckillList", seckillList);
		return mv;
	}
	
	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId")Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:seckill/seckillList";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "redirect:seckill/seckillList";
		}
		model.addAttribute("seckill", seckill);
		return "seckill/detail";
	}
	
	//ajax json
	@RequestMapping(value = "/{seckillId}/exposer", 
			method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId) {
		System.out.println("11111111");
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result = new SeckillResult<Exposer>(true, e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/{seckillId}/{md5}/execution", 
			method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execution(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long phone) {
		if (phone == null ) {
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		SeckillResult<SeckillExecution> result;
		try {
			SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
			result = new SeckillResult<SeckillExecution>(true, execution);
		} catch (SeckillCloseException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.END);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		} catch (RepeatKillException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		}
		return result;
	}
	
	@RequestMapping(value = "/time/new", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time() {
		Date date = new Date();
		return new SeckillResult<Long>(true, date.getTime());
	}
	
	
	
}
