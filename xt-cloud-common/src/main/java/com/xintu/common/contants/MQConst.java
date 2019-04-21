package com.xintu.common.contants;

/**
 * @param :
 * @Description(描述): 定义一些常量
 * @auther: Jack Lin
 * @return :
 * @date: 2019/4/21 12:13
 */
public interface MQConst {

    /**
     * @Description(描述): 商品相关
     * @auther: Jack Lin
     *      * @param :
     *      * @return :
     * @date: 2019/4/21 14:31
     */
    interface ITEM {
        /**
         * 通配符模式exchange
         */
        public  static  final  String  TOPICEXCHANGE="ITEM_TOPIC_EXCHANGE";
        /**
         * 绑定通配符
         */
        public  static  final  String  ROUTINGKEY="ITEM.#";
        /**
         * 新增
         */
        public  static  final  String  INTERSQUEUE="ITEM.INSERT";
        /**
         * 更新
         */
        public  static  final  String  UPDATEQUEUE="ITEM.UPDATE";
        /**
         * 删除
         */
        public  static  final  String  DELETEQUEUE="ITEM.DELETE";
    }

}
