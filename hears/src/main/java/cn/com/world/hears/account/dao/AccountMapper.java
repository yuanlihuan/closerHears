package cn.com.world.hears.account.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import cn.com.world.hears.account.bo.Account;

@Component
public interface AccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_account
     *
     * @mbggenerated Fri Jun 22 15:43:09 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_account
     *
     * @mbggenerated Fri Jun 22 15:43:09 CST 2018
     */
    int insert(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_account
     *
     * @mbggenerated Fri Jun 22 15:43:09 CST 2018
     */
    int insertSelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_account
     *
     * @mbggenerated Fri Jun 22 15:43:09 CST 2018
     */
    Account selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_account
     *
     * @mbggenerated Fri Jun 22 15:43:09 CST 2018
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pub_account
     *
     * @mbggenerated Fri Jun 22 15:43:09 CST 2018
     */
    int updateByPrimaryKey(Account record);

	Account selectByAccount(@Param(value = "account") Account account);
}