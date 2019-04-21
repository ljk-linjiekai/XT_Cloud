package com.xintu.manager.web.controller;

import com.xintu.manager.web.fegin.ItemCatApi;
import com.xt.manage.domain.model.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("/item/cat")
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatApi itemCatApi;


    @RequestMapping(value = "/{itemCatId}", method = RequestMethod.GET)
    public ResponseEntity<ItemCat> queryItemCatListById(@PathVariable("itemCatId") Long itemCatId) {
        try {
            ItemCat itemCat = itemCatApi.queryById(itemCatId);
            //返回ok
            return ResponseEntity.ok(itemCat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    /**
     * 根据页号和页大小分页查询商品类目列表
     *
     * @param page 页号
     * @param rows 页大小
     * @return
     */
    @RequestMapping(value = "/query/{page}")
    public ResponseEntity<List<ItemCat>> queryItemCatByPage(@PathVariable("page") Integer page,
                                                            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        try {
            List<ItemCat> list = itemCatApi.queryItemCatByPage(page, rows);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);


    }


    /**
     * 根据父类目id查询该类目下的所有子类目
     *
     * @param parentId 父类目id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ItemCat>> queryItemCatListByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId) {

        try {
            //设置查询条件
            ItemCat itemCat = new ItemCat();
            itemCat.setParentId(parentId);
            //执行查询
            List<ItemCat> list = itemCatApi.queryListByWhere(itemCat);
            //返回查询列表
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);


    }


}
