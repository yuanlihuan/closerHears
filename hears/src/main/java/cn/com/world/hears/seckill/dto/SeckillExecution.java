package cn.com.world.hears.seckill.dto;

import cn.com.world.hears.seckill.bo.SuccessKilled;
import cn.com.world.hears.seckill.enums.SeckillStatEnum;

/**
 * 秒杀执行结果
 * @author liulai
 *
 */
public class SeckillExecution {

	private long seckillId;
	
	//秒杀的状态
	private int state;
	
	//状态的表示含义
	private String stateInfo;
	
	//秒杀成功的对象
	private SuccessKilled successKilled;
	
	/**
	 * 秒杀成功
	 * @param seckillId
	 * @param state
	 * @param stateInfo
	 * @param successKilled
	 */
	public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
		this.successKilled = successKilled;
	}
	
	/**
	 * 秒杀失败
	 * @param seckillId
	 * @param state
	 * @param stateInfo
	 */
	public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
	}
	

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
}
