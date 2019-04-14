package com.xintu.manager.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.xt.manage.api.interfaces.BaseService;
import com.xt.manage.model.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {


    private Class<T> clazz;
    @Autowired
    private BaseMapper<T> mapper;    //泛型依赖注入；在spring 4.x版本可以根据泛型找到对应的业务dao对象；必须使用@Autowired

    public BaseServiceImpl() {

        //获取泛型父类
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

    }

    @Override
    public T queryById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public List<T> queryAll() {
        return mapper.selectList(new QueryWrapper<T>());
    }

    @Override
    public List<T> queryListByWhere(T t) {

        return mapper.selectList(new QueryWrapper<T>());
    }

    @Override
    public long queryCountByWhere(T t) {

        return mapper.selectCount(new QueryWrapper<T>());
    }

    @Override
    public List<T> queryListByPage(Integer page, Integer rows) {

        //设置分页
        PageHelper.startPage(page, rows);
        return mapper.selectList(new QueryWrapper<T>());
    }

    @Override
    public void saveSelective(T t) {
        //如果是新添加，则创建时间和更新时间
        if (t.getCreated() == null) {
            t.setCreated(new Date());
            t.setUpdated(t.getCreated());
        } else if (t.getUpdated() == null) {
            t.setUpdated(new Date());
        }
        mapper.insert(t);
    }

    @Override
    public void updateSelectiveById(T t) {
        //设置更新时间
        if (t.getUpdated() == null) {
            t.setUpdated(new Date());
        }
        //mapper.updateByPrimaryKeySelective(t);
        mapper.updateById(t);
    }

    @Override
    public void deleteById(Serializable id) {
        //mapper.deleteByPrimaryKey(id);
        mapper.deleteById(id);
    }

    @Override
    public void deleteByIds(Serializable[] ids) {
        List list = new LinkedList();
        for (int i = 0; i <= ids.length; i++) {
            list.add(ids[i]);
        }
        //批量删除
        mapper.deleteBatchIds(list);
    }

}
