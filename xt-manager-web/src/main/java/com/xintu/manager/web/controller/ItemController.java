package com.xintu.manager.web.controller;

import com.xintu.common.vo.DataGridResult;
import com.xintu.manager.web.fegin.ItemApi;
import com.xt.manage.domain.model.Item;
import com.xt.manage.domain.model.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 林捷凯
 * @version 1.0 Function: TODO
 * @Time：2017年2月15日 上午9:20:04
 */
@RequestMapping("/item")
@Controller
public class ItemController {

    @Autowired
    private ItemApi itemApi;

    /**
     * deleteItemByIds:(). 删除商品，修改状状态为删除
     *
     * @param ids
     * @return
     * @author 林捷凯
     * @Time：2017年2月15日 上午10:16:51
     */
    @RequestMapping("/delete")
    public ResponseEntity<Void> deleteItemByIds(@RequestParam("ids") Long[] ids) {
        try {
            itemApi.updateItemStatuByIds(ids, 3);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * reshelfItemById:(). 上架商品
     *
     * @param ids
     * @return
     * @author 林捷凯
     * @Time：2017年2月15日 上午9:42:58
     */
    @RequestMapping(value = "/reshelf")
    public ResponseEntity<Void> reshelfItemByIds(@RequestParam(value = "ids", required = true) Long[] ids) {
        try {
            itemApi.updateItemStatuByIds(ids, 1);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * instockItemById:().下架商品
     *
     * @param ids 商品id
     * @return
     * @author 林捷凯
     * @Time：2017年2月15日 上午9:31:39
     */
    @RequestMapping("/instock")
    public ResponseEntity<Void> instockItemByIds(@RequestParam("ids") Long[] ids) {
        try {
            itemApi.updateItemStatuByIds(ids, 2);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * queryItemListByPage:(). 根据标题分页查询商品列表
     *
     * @param title 页面搜索条件
     * @param page  当前页
     * @param rows  页面大小
     * @return
     * @author 林捷凯
     * @Time：2017年2月15日 上午11:13:50
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<DataGridResult> queryItemListByPage(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            DataGridResult list = itemApi.queryItemList(title, page, rows);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 保存商品基本信息和描述信息
     *
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(method = RequestMethod.POST) // post表示新增
    public ResponseEntity<List<ItemCat>> saveItem(Item item,
                                                  @RequestParam(value = "desc", required = false) String desc) {
        try {
            // 调用service保存
            itemApi.saveItem(item, desc);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }

    /**
     * updateItem:(). 更新商品信息
     *
     * @param item 商品信息
     * @param desc 商品描述
     * @return
     * @author 林捷凯
     * @Time：2017年2月15日 上午9:21:54
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> updateItem(Item item, @RequestParam(value = "desc", required = false) String desc) {
        try {
            // 调用ItemService实现更新商品
            itemApi.updateItem(item, desc);
            // 返回ok
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


}
