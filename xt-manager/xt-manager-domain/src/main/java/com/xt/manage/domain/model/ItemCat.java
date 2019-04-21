package com.xt.manage.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@TableName("tb_item_cat")
@Data
public class ItemCat extends BasePojo {

    /**
     * TODO
     */
    private static final long serialVersionUID = -5677701897284231066L;

    /**
     * 数据库主键自增
     */
    @TableId(value = "id",type = IdType.AUTO)
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
