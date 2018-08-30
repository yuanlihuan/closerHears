package cn.com.world.hears.seckill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.world.hears.seckill.bo.Seckill;
import cn.com.world.hears.seckill.bo.SuccessKilled;
/**
 * 配置spring和junit整合
 * @author liulai
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class SeckillMapperTest {
	@Resource
	private SeckillMapper seckillMapper;

	@Resource
	private SuccessKilledMapper successKilledMapper;
	@Test
	public void testInsertSuccessKilled() {
		SuccessKilled record = new SuccessKilled();
		record.setUserPhone(Long.parseLong("18401541897"));
		record.setSeckillId(1000L);
		record.setState(Byte.valueOf("1"));
		//int insertSuccessKilled = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
		int insert = successKilledMapper.insert(record);
		System.out.println(insert);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled queryByIdWithSeckill = successKilledMapper.queryByIdWithSeckill(1000);
		System.out.println(queryByIdWithSeckill);
	}
	@Test
	public void testReduceNumber() {
		int reduceNumber = seckillMapper.reduceNumber(1000, new Date());
		System.out.println(reduceNumber);
	}

	@Test
	public void testQueryByid() {
		long id = 1000;
		Seckill seckill = seckillMapper.queryById(id);
		System.err.println(seckill.getName());
	}

	@Test
	public void testQueryAll() {
		List<Seckill> queryAll = seckillMapper.queryAll(1, 3);
		System.out.println(queryAll);
	}

}
