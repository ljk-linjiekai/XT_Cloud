package com.xt.manage.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@TableName("tb_item")
@JsonIgnoreProperties //表示忽略属性
@Data
public class Item extends BasePojo {
	private static final long serialVersionUID = 161966794110563201L;

	@TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
