package com.xt.manage.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("tb_item_desc")
@Data
public class ItemDesc extends BasePojo {

    private static final long serialVersionUID = -7278009598146996766L;

    @TableField("item_id")
    private Long itemId;

    @TableField("item_desc")
    private String itemDesc;


    @Override
    protected Serializable pkVal() {
        return this.itemId;
    }
}
