package com.xt.manage.api.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xt.manage.domain.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemMapper extends BaseMapper<Item> {

}
