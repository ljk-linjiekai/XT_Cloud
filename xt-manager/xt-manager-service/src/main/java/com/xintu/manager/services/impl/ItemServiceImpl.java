package com.xintu.manager.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xintu.common.contants.MQConst;
import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.vo.DataGridResult;
import com.xt.manage.api.interfaces.ItemService;
import com.xt.manage.api.mapper.ItemDescMapper;
import com.xt.manage.api.mapper.ItemMapper;
import com.xt.manage.domain.model.Item;
import com.xt.manage.domain.model.ItemDesc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void saveItem(Item item, String desc) {
        // 1.保存商品基本信息
        item.setStatus(1);
        saveSelective(item);

        // 2.保存商品描述信息
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        Integer insert = itemDescMapper.insert(itemDesc);
        log.info("saveItem result:{}",insert);
        // 发送mq消息
        sendMqMsg(item, MQConst.ITEM.INTERSQUEUE);
    }


    /**
     * 发送activeMQ消息
     * @param item 商品
     * @param type 操作类型
     */
    private void sendMqMsg(Item item, String type) {
        try {
            final String itemJsonStr = MAPPER.writeValueAsString(item);
            // 发送消息
            rabbitTemplate.convertAndSend(MQConst.ITEM.TOPICEXCHANGE, type, itemJsonStr);
            log.info("sendMqMsg success, exchange：{},queue：{} ,message：{}", MQConst.ITEM.TOPICEXCHANGE, type, itemJsonStr);
        } catch (Exception e) {
            log.error("sendMqMsg error:：{}", e);
        }
    }

    @Override
    public void updateItem(Item item, String desc) {
        // 1.更新商品基本信息
        updateSelectiveById(item);
        // 2.更新商品描述信息
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        Integer integer = itemDescMapper.updateById(itemDesc);
        //发送mq消息
        sendMqMsg(item, MQConst.ITEM.UPDATEQUEUE);
        log.info("updateItem result:{},value：{}",integer,JacksonMapper.toJson(item));
    }

    @Override
    public DataGridResult queryItemList(String title, Integer page, Integer rows) {

        // 使用example查询
        // 创建example
		/*Example example = new Example(Item.class);
		// 添加查询条件
		Criteria criteria = example.createCriteria();*/

        // 状态集合，页面只显示状态是上架和下架的商品
        List<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        // 添加查询条件
        // criteria.andIn("status", statusList);
        try {

            // 如果查询条件不为空
            if (StringUtils.isNotBlank(title)) {
                // 解码
                title = URLDecoder.decode(title, "utf-8");
                //criteria.andLike("title", "%" + title + "%");
            }
        } catch (UnsupportedEncodingException e) {

        }
        // 排序：更加更新时间降序排序
        //example.orderBy("updated").desc();

        // 设置分页
        PageHelper.startPage(page, rows);
        // 查询
        List<Item> list = itemMapper.selectList(new QueryWrapper<Item>());
        // 将列表转换成pageInfo
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        DataGridResult dataGridResult = new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
        log.info("queryItemList result:{}",JacksonMapper.toJson(dataGridResult));
        return dataGridResult;
    }

    @Override
    public void updateItemStatuByIds(Long[] ids, Integer status) {
        if (ids != null && ids.length > 0) {
            for (Long id : ids) {
                Item item = itemMapper.selectById(id);
                item.setUpdated(new Date());
                item.setStatus(status);
                itemMapper.updateById(item);
                //发送mq消息
                sendMqMsg(item, MQConst.ITEM.DELETEQUEUE);
            }
        }
    }

}
