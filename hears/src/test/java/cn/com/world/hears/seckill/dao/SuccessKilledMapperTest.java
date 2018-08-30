package cn.com.world.hears.seckill.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

public class SuccessKilledMapperTest {

	@Resource
	private SuccessKilledMapper successKilledMapper;
	@Test
	public void testInsertSuccessKilled() {
		long seckillId = 1000;
		long userPhone = 1840;
		int insertSuccessKilled = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
		System.out.println(insertSuccessKilled);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		fail("Not yet implemented");
	}

}
