package com.xintu.manager.services.configs;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 配置公共字段自动填充功能  @TableField(..fill = FieldFill.INSERT)
 * 特别注意，3.0-gamma之前的版本 MetaObjectHandler 是抽象类
 * 3.0-RC之后的版本MetaObjectHandler 是接口
 */
@Component
public class MetaObjectHandlerConfig extends MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName("created", metaObject);
        Object updateTime = getFieldValByName("updated", metaObject);
        if (createTime == null) {
            //mybatis-plus版本2.0.9+
            setFieldValByName("created", new Date(), metaObject);
        }
        if (updateTime == null) {
            setFieldValByName("updated", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updated", metaObject);
        if (updateTime == null) {
            setFieldValByName("updated", new Date(), metaObject);
        }
    }
}