package com.test.rocketmq.consumer.service;


import com.github.pagehelper.PageSerializable;
import com.power.common.model.CommonResult;
import com.test.rocketmq.consumer.model.Account;

/**
 *
 * @author yu on 2018/11/27.
 */
public interface AccountService {

	/**
	 * 保存数据
	 * @param entity
	 * @return
     */
	CommonResult save(Account entity);

	/**
	 * 修改数据
	 * @param entity
	 * @return
     */
	CommonResult update(Account entity);

	/**
	 * 删除数据
	 * @param id
	 * @return
     */
	CommonResult delete(int id);

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
     */
	CommonResult queryById(int id);

	/**
     * 分页查询
     * @param offset 偏移量
     * @param limit 每页大小
     * @return
     */
    PageSerializable queryPage(int offset, int limit);
}