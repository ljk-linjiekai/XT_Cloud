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

@TableName(value = "tb_content_category")
@Data
public class ContentCategory extends BasePojo {

	private static final long serialVersionUID = 2225029178174818526L;

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

}
