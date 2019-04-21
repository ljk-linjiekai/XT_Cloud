package com.xt.manage.domain.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public abstract class BasePojo extends Model {

    private static final long serialVersionUID = -1764615629102428742L;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;
    @TableField(value = "updated", fill = FieldFill.INSERT_UPDATE)
    private Date updated;

}
