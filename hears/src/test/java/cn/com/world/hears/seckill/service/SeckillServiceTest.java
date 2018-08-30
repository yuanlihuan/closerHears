package cn.com.world.hears.seckill.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.world.hears.seckill.bo.Seckill;
import cn.com.world.hears.seckill.dto.Exposer;
import cn.com.world.hears.seckill.dto.SeckillExecution;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml",
	"classpath:spring-mvc.xml"})
public class SeckillServiceTest {

	@Resource
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() {
		List<Seckill> seckillList = seckillService.getSeckillList();
		System.out.println(seckillList);
	}

	@Test
	public void testGetById() {
		Seckill byId = seckillService.getById(1000);
		System.out.println(byId);
	}

	@Test
	public void testExportSeckillUrl() {
		Exposer exportSeckillUrl = seckillService.exportSeckillUrl(1000);
		System.out.println(exportSeckillUrl);
	}

	@Test
	public void testExecuteSeckill() {
		String md5 = "380d7a6e3c0f3ad410a5d62f00483aa6";
		long userPhone = 18401541896L;
		long seckillId = 1000;
		SeckillExecution executeSeckill = seckillService.executeSeckill(seckillId, userPhone, md5);
		System.out.println(executeSeckill);
	}

}
