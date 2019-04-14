package com.xt.manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@TableName("tb_item_cat")
@Data
public class ItemCat extends BasePojo {

    /**
     * TODO
     */
    private static final long serialVersionUID = -5677701897284231066L;

    @TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @TableField("name")
    private String name;

    @TableField("status")
    private Integer status;

    @TableField("sort_order")
    private Integer sortOrder;

    @TableField("is_parent")
    private Boolean isParent;

    //添加easyui tree需要的属性（方法）
    public String getText() {
        return getName();
    }

}
