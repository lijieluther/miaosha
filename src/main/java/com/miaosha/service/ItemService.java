package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.model.ItemModel;

import java.util.List;

/**
 * @author luther
 */
public interface ItemService {
    /**
     * 创建商品
     * @param  itemModel
     * @return Result<ItemModel>
     */
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    /**
     * 商品列表浏览
     * @return Result<list>
     */
    List<ItemModel> listItem();

    /**
     * 商品详情浏览
     * @param  id
     * @return Result<ItemModel>
     */
    ItemModel getItemById(Integer id);
}
