package cn.com.world.hears.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.world.hears.account.bo.Account;
import cn.com.world.hears.account.dao.AccountMapper;
import cn.com.world.hears.account.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource(name = "accountMapper")
	private AccountMapper accountMapper;

	@Override
	public Account selectByAccount(Account account) {
		
		return accountMapper.selectByAccount(account);
	}
}
