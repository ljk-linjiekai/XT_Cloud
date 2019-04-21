package com.xt.manage.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@TableName(value = "tb_content_category")
@Data
public class ContentCategory extends BasePojo {

	private static final long serialVersionUID = 2225029178174818526L;

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
