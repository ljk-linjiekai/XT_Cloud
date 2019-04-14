package com.xt.manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@TableName("tb_item_desc")
@Data
public class ItemDesc extends BasePojo {

    /**
     * TODO
     */
    private static final long serialVersionUID = -7278009598146996766L;

    @TableId
    @TableField("item_id")
    private Long itemId;

    @TableField("item_desc")
    private String itemDesc;


}
