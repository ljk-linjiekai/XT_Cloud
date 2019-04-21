package com.xintu.manager.web.fegin;

import com.xintu.common.vo.DataGridResult;
import com.xt.manage.domain.model.Item;

import java.io.Serializable;

public interface ItemApi {

    /**
     * saveItem:(). 保存商品
     *
     * @param item 商品信息
     * @param desc 商品描述
     * @author 林捷凯
     * @Time：2017年2月15日 上午10:06:53
     */
    void saveItem(Item item, String desc) throws Exception;


    /**
     * updateItem:(). 更新商品
     *
     * @param item 商品信息
     * @param desc 商品描述
     * @author 林捷凯
     * @Time：2017年2月15日 上午10:07:11
     */
    void updateItem(Item item, String desc) throws Exception;

    /**
     * queryItemList:(). 查询商品列表
     *
     * @param title 列表搜索条件
     * @param page  当前页
     * @param rows  页面大小
     * @return
     * @author 林捷凯
     * @Time：2017年2月15日 上午10:07:43
     */
    DataGridResult queryItemList(String title, Integer page, Integer rows) throws Exception;

    /**
     * updateItemStatuByIds:(). 修改商品状态：上架、下架、删除
     *
     * @param ids   商品id
     * @param statu 状态码
     * @author 林捷凯
     * @Time：2017年2月15日 上午10:30:24
     */
    void updateItemStatuByIds(Long[] ids, Integer statu) throws Exception;

    /**
     * 选择性新增
     */
    public void saveSelective(Item item) throws Exception;

    /**
     * 根据id查询
     */
    public Item queryById(Long id) throws Exception;

    /**
     * 根据id选择性更新
     */
    public void updateSelectiveById(Item item) throws Exception;

    /**
     * 批量删除
     */
    public void deleteByIds(Long[] ids) throws Exception;
}
