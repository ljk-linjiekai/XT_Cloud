package com.xintu.manager.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.xintu.common.utils.JacksonMapper;
import com.xt.manage.api.interfaces.BaseService;
import com.xt.manage.domain.model.BasePojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {


    private Class<T> clazz;
    /**
     * 泛型依赖注入；在spring 4.x版本可以根据泛型找到对应的业务dao对象；必须使用@Autowired
     */
    @Autowired
    private BaseMapper<T> mapper;

    public BaseServiceImpl() {

        //获取泛型父类
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

    }

    @Override
    public T queryById(Serializable id) {
        T t = mapper.selectById(id);
        log.info("queryById result：{}", JacksonMapper.toJson(t));
        return t;
    }

    @Override
    public List<T> queryAll() {
        List<T> ts = mapper.selectList(new QueryWrapper<T>());
        log.info("queryAll result：{}", JacksonMapper.toJson(ts));
        return ts;
    }

    @Override
    public List<T> queryListByWhere(T t) {
        List<T> ts = mapper.selectList(new QueryWrapper<T>());
        log.info("queryListByWhere result：{}", JacksonMapper.toJson(ts));
        return ts;
    }

    @Override
    public long queryCountByWhere(T t) {
        Integer integer = mapper.selectCount(new QueryWrapper<T>());
        log.info("queryCountByWhere result：{}，value：{}", integer, JacksonMapper.toJson(t));
        return integer;
    }

    @Override
    public List<T> queryListByPage(Integer page, Integer rows) {
        //设置分页
        PageHelper.startPage(page, rows);
        List<T> ts = mapper.selectList(new QueryWrapper<T>());
        log.info("queryListByPage result：{}", JacksonMapper.toJson(ts));
        return ts;
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
        Integer insert = mapper.insert(t);
        log.info("saveSelective result：{}, value：{}", insert, JacksonMapper.toJson(t));

    }

    @Override
    public void updateSelectiveById(T t) {
        //设置更新时间
        if (t.getUpdated() == null) {
            t.setUpdated(new Date());
        }
        Integer integer = mapper.updateById(t);
        log.info("updateSelectiveById result：{}, value：{}", integer, JacksonMapper.toJson(t));
    }

    @Override
    public void deleteById(Serializable id) {
        Integer integer = mapper.deleteById(id);
        log.info("deleteById result：{}", integer);
    }

    @Override
    public void deleteByIds(Serializable[] ids) {
        List list = new LinkedList();
        for (int i = 0; i <= ids.length; i++) {
            list.add(ids[i]);
        }
        //批量删除
        Integer integer = mapper.deleteBatchIds(list);
        log.info("deleteByIds result：{}, value：{}", integer, JacksonMapper.toJson(list));
    }

}
