package com.xt.manage.api.interfaces;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T>{

	
	/**
	 * 根据id查询
	 */
	public T queryById(Serializable id);
	
	/**
	 * 查询所有
	 */
	public List<T> queryAll();
	
	/**
	 * 根据条件查询
	 */
	public List<T> queryListByWhere(T t);
	
	/**
	 * 根据条件查询记录总数
	 */
	public long queryCountByWhere(T t);
	
	/**
	 * 分页查询记录
	 * 
	 * @param page 当前页号
	 * @param rows 页大小
	 * @return
	 */
	public List<T> queryListByPage(Integer page, Integer rows);

	/**
	 * 选择性新增
	 * @param t
	 */
	public void saveSelective(T t);

	/**
	 * 根据id选择性更新
	 * @param t
	 */
	public void updateSelectiveById(T t);

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteById(Serializable id);

	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteByIds(Serializable[] ids);
}
