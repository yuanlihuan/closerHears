package cn.com.world.hears.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import cn.com.world.hears.seckill.bo.SuccessKilled;

@Component
public interface SuccessKilledMapper {
	
    /**
     * 插入购买明细
     * @param record
     * @return
     */							 
    int insertSuccessKilled(@Param(value = "seckillId") long seckillId, @Param(value = "userPhone") long userPhone);
    
    int insert(SuccessKilled record);

    int insertSelective(SuccessKilled record);

    int updateByPrimaryKeySelective(SuccessKilled record);

    int updateByPrimaryKey(SuccessKilled record);
    
    /**
     * 根据id查询Successkilled并携带秒杀对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
    
}