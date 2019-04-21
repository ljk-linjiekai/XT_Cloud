package com.xintu.manager.web.fegin;

import com.xt.manage.domain.model.ItemCat;

import java.io.Serializable;
import java.util.List;

public interface ItemCatApi {
    /**
     * 根据页号和页大小分页查询商品类目列表
     *
     * @param page 页号
     * @param rows 页大小
     * @return
     */
    List<ItemCat> queryItemCatByPage(Integer page, Integer rows) throws Exception;

    /**
     * @param :[id]
     * @return :com.xt.manage.domain.model.ItemCat
     * @Description(描述): 根据id查询
     * @auther: Jack Lin
     * @date: 2019/4/21 17:22
     */
    public ItemCat queryById(Long id) throws Exception;

    /**
     * @param :[itemCat]
     * @return :java.util.List<com.xt.manage.domain.model.ItemCat>
     * @Description(描述): 根据条件查询
     * @auther: Jack Lin
     * @date: 2019/4/21 17:23
     */
    public List<ItemCat> queryListByWhere(ItemCat itemCat) throws Exception;
}
