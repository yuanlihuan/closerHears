package cn.com.world.hears.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.world.hears.seckill.bo.Seckill;
import cn.com.world.hears.seckill.utils.SpringRedisDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class RedisDaoTest {

	private long id = 1001;

	@Autowired
	private SpringRedisDao springRedisDao;
	
	@Autowired
	private SeckillMapper seckillMapper;
	
	@Test
	public void testSeckill() throws Exception {
		
		Seckill seckill = (Seckill) springRedisDao.get(String.valueOf(id));
		if (seckill == null) {
			seckill = seckillMapper.queryById(id);
			if (seckill != null) {
				boolean set = springRedisDao.set(String.valueOf(id), seckill);
				if (set) {
					seckill = (Seckill) springRedisDao.get(String.valueOf(id));
					System.out.println(seckill);
				}
				System.out.println(set);
			}
		}
	}
}
