package com.xintu.manager.web.fegin;

import com.xt.manage.domain.model.ContentCategory;

import java.util.List;

public interface ContentCategoryApi {
    /**
     *
     * saveContentCategory:(). 添加内容分类
     * @author 林捷凯
     * @Time：2017年2月16日 下午5:05:44
     * @param contentCategory
     * @return
     */
    ContentCategory saveContentCategory(ContentCategory contentCategory) throws  Exception;


    /**
     *
     * deleteContentCategory:(). 删除节点以及子孙节点
     * @author 林捷凯
     * @Time：2017年2月16日 下午6:40:32
     * @param contentCategory
     */
    void deleteContentCategory(ContentCategory contentCategory) throws  Exception;

    /**
     * 根据id选择性更新
     */
    public void updateSelectiveById(ContentCategory contentCategory)throws  Exception;

    /**
     * 根据条件查询
     */
    public List<ContentCategory> queryListByWhere(ContentCategory contentCategory)throws  Exception;
}
