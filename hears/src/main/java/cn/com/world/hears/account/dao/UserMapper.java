package cn.com.world.hears.account.dao;

import org.springframework.stereotype.Component;

import cn.com.world.hears.account.bo.User;

@Component
public interface UserMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}