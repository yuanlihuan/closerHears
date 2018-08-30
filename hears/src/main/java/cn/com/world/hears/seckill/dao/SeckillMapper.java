package cn.com.world.hears.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import cn.com.world.hears.seckill.bo.Seckill;

@Component
public interface SeckillMapper {
    int deleteByPrimaryKey(Long seckillId);

    int insert(Seckill record);

    int insertSelective(Seckill record);

    int updateByPrimaryKeySelective(Seckill record);

    int updateByPrimaryKey(Seckill record);
    
    /**
     * 减库存
     * @param seckillId
     * @return
     */
    int reduceNumber(@Param(value = "seckillId") long seckillId, @Param(value = "killTime") Date killTime);
    
    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);
    
    /**
     * 根据便宜了查询秒杀的商品列表
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param(value = "offset") int offset, @Param(value = "limit") int limit);
    
    
}