package com.xt.manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@TableName(value = "tb_content")
@Data
public class Content extends BasePojo {

	private static final long serialVersionUID = 6147989922803865860L;

	@TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @TableField(value = "category_id")
    private Long categoryId;

    @TableField(value = "title")
    private String title;

    @TableField(value = "sub_title")
    private String subTitle;

    @TableField(value = "title_desc")
    private String titleDesc;

    @TableField(value = "url")
    private String url;

    @TableField(value = "pic")
    private String pic;

    @TableField(value = "pic2")
    private String pic2;

    @TableField(value = "content")
    private String content;
}
