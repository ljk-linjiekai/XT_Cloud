package com.xintu.manager.web.fegin;

import com.xt.manage.domain.model.ItemDesc;

import java.io.Serializable;

public interface ItemDescApi {

    /**
     * 根据id查询
     */
    public ItemDesc queryItemDescByItemid(Long id)throws Exception;
}
