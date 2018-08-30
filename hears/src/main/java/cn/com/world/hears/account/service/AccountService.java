package cn.com.world.hears.account.service;

import cn.com.world.hears.account.bo.Account;

public interface AccountService {

	/**
	 * 通过account查询Account数据
	 * @param account
	 * @return Account
	 */
	Account selectByAccount(Account account);

}
