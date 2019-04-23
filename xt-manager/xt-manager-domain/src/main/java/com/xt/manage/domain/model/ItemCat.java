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
    public Long id;

   @TableField("parent_id")
   public Long parentId;

    @TableField("name")
    public String name;

    @TableField("status")
    public Integer status;

    @TableField("sort_order")
    public Integer sortOrder;

    @TableField("is_parent")
    public Boolean isParent;

    //添加easyui tree需要的属性（方法）
    public String getText() {
        return getName();
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    //手动重写getset方法
    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }
}
