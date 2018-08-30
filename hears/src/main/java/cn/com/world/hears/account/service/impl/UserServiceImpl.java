package cn.com.world.hears.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.world.hears.account.bo.User;
import cn.com.world.hears.account.dao.UserMapper;
import cn.com.world.hears.account.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	@Override
	public User getUserById(int id) {
		
		User selectByPrimaryKey = this.userMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

}
