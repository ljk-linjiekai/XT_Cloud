package com.xintu.manager.web.fegin;

import com.xintu.common.vo.DataGridResult;
import com.xt.manage.domain.model.Content;

import java.io.Serializable;

public interface ContentApi {
    /**
     * 查询类目内容分页列表
     * queryContentListByPage:().
     *
     * @param categoryId
     * @param page
     * @param rows
     * @return
     * @author 林捷凯
     * @Time：2017年2月16日 下午7:24:49
     */
    DataGridResult queryContentListByPage(Long categoryId, Integer page, Integer rows)throws Exception;


    /**
     * 获取门户系统首页中展示的6条大广告数据
     *
     * @return 列表数据（json格式字符串）
     * @throws Exception
     */
    String getPortalBigAdData() throws Exception;

    /**
     * 选择性新增
     */
    void saveSelective(Content content) throws Exception;

    /**
     * 根据id选择性更新
     */
    void updateSelectiveById(Content content) throws Exception;
    /**
     * 批量删除
     * @param ids
     */
    public void deleteByIds(Serializable[] ids)throws Exception;
}
