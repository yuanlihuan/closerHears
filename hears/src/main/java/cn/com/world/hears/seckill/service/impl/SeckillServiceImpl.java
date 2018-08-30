package cn.com.world.hears.seckill.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.com.world.hears.seckill.bo.Seckill;
import cn.com.world.hears.seckill.bo.SuccessKilled;
import cn.com.world.hears.seckill.dao.SeckillMapper;
import cn.com.world.hears.seckill.dao.SuccessKilledMapper;
import cn.com.world.hears.seckill.dto.Exposer;
import cn.com.world.hears.seckill.dto.SeckillExecution;
import cn.com.world.hears.seckill.enums.SeckillStatEnum;
import cn.com.world.hears.seckill.exception.RepeatKillException;
import cn.com.world.hears.seckill.exception.SeckillCloseException;
import cn.com.world.hears.seckill.exception.SeckillException;
import cn.com.world.hears.seckill.service.SeckillService;
import cn.com.world.hears.seckill.utils.SpringRedisDao;

@Service
public class SeckillServiceImpl implements SeckillService {
	
	private Logger log = LoggerFactory.getLogger(SeckillServiceImpl.class);
	
	@Resource
	private SeckillMapper seckillMapper;
	
	@Resource
	private SuccessKilledMapper successKilledMapper;
	
	@Resource
	private SpringRedisDao springRedisDao;
	
	private final String slat = "sfjsdfslfjsk_adfsklw1234p_dfdkfakf=1=-30120";
	
	@Override
	public List<Seckill> getSeckillList() {
		
		return seckillMapper.queryAll(0, 4);
	}

	@Override
	public Seckill getById(long seckillId) {
		
		return seckillMapper.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		
		Seckill seckill = (Seckill) springRedisDao.get(String.valueOf(seckillId));
		if (seckill == null) {
			seckill = seckillMapper.queryById(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			} else {
				boolean bool = springRedisDao.set(String.valueOf(seckillId), seckill);
				if (!bool) {
					new Exception("缓存保存失败");
				}
			}
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() ||
				nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), 
					endTime.getTime());
			
		}
		//MD5是不可逆的
		String md5 = getMd5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMd5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	@Override
	@Transactional
	/**
	 * 1.开发明确表明事务编程风格
	 * 2.保证事务方法的执行时间尽可能的短，不要穿插其他网络操作rpc、http请求
	 * 3.不是所有的犯法都需要数据，如只有一条操作
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, SeckillCloseException,
			RepeatKillException {
		if (md5 == null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillCloseException("seckill date rewite");
		}
		try {
			//记录购买行为
			int count = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
			if (count <= 0) {
				throw new RepeatKillException("秒杀重复");
			} else {
				//执行秒杀逻辑：减库存 加购买记录
				int reduceNumber = seckillMapper.reduceNumber(seckillId, new Date());
				if (reduceNumber <= 0) {
					//没有减库存，秒杀结束
					throw new SeckillCloseException("seckill is close");
				} else {
					SuccessKilled SuccessKilled = successKilledMapper.queryByIdWithSeckill(seckillId);
					return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, SuccessKilled);
				}
			}
		} catch(SeckillCloseException e1) {
			throw e1;
		} catch(RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			//所以编译的异常转化为运行期异常
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}
	}

}
