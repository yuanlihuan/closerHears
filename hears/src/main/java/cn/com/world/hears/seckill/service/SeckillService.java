package cn.com.world.hears.seckill.service;

import java.util.List;

import cn.com.world.hears.seckill.bo.Seckill;
import cn.com.world.hears.seckill.dto.Exposer;
import cn.com.world.hears.seckill.dto.SeckillExecution;
import cn.com.world.hears.seckill.exception.RepeatKillException;
import cn.com.world.hears.seckill.exception.SeckillCloseException;
import cn.com.world.hears.seckill.exception.SeckillException;

/**
 * 秒杀接口
 * @author liulai
 */
public interface SeckillService {
	
	/**
	 * 查询所有秒杀记录
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个的秒杀记录
	 * @param seckillId
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 秒杀开启时候输出秒杀的地址
	 * 否则输出系统时间和秒杀时间
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) 
			throws SeckillException,SeckillCloseException,RepeatKillException;
}
