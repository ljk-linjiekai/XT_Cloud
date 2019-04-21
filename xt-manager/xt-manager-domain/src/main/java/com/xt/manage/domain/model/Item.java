package com.xt.manage.domain.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@TableName("tb_item")
@JsonIgnoreProperties //表示忽略属性
@Data
public class Item extends BasePojo {
	private static final long serialVersionUID = 161966794110563201L;

    /**
     * 数据库主键自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("sell_point")
    private String sellPoint;

    @TableField("price")
    private Long price;

    @TableField("num")
    private Integer num;

    @TableField("barcode")
    private String barcode;

    @TableField("image")
    private String image;

    @TableField("cid")
    private Long cid;

    @TableField("status")
    private Integer status;

    //添加images属性
    @JsonIgnore //表示忽略该属性，页面将不会加载
    public String[] getImages(){
    	if(image != null){
    		return image.split(",");
    	}
    	return null;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
