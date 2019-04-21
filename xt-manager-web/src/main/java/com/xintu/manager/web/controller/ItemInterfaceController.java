package com.xintu.manager.web.controller;

import com.xintu.manager.web.fegin.ItemApi;
import com.xt.manage.domain.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/item/interface")
@Controller
public class ItemInterfaceController {

    @Autowired
    private ItemApi itemApi;


    /**
     * saveItem:(). 新增商品
     *
     * @param item
     * @return
     * @author 林捷凯
     * @Time：2017年2月20日 下午3:13:54
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item) {

        try {
            //保存
            itemApi.saveSelective(item);
            //响应成功
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * queryItemById:(). 查询商品
     *
     * @param itemId
     * @return
     * @author 林捷凯
     * @Time：2017年2月20日 下午3:17:54
     */
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Item> queryItemById(@PathVariable("itemId") Long itemId) {

        try {
            Item item = itemApi.queryById(itemId);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    /**
     * updateItem:(). 更新商品
     *
     * @param item
     * @return
     * @author 林捷凯
     * @Time：2017年2月20日 下午3:23:38
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(Item item) {
        try {
            itemApi.updateSelectiveById(item);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    /**
     * deleteItem:(). 批量删除商品
     *
     * @param ids
     * @return
     * @author 林捷凯
     * @Time：2017年2月20日 下午3:25:47
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteItem(@RequestParam(value = "ids", required = false) Long[] ids) {

        try {
            if (ids != null && ids.length > 0) {
                itemApi.deleteByIds(ids);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


}
