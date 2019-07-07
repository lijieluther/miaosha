package com.miaosha.controller;

import com.miaosha.controller.viewobiect.ItemVo;
import com.miaosha.error.BusinessException;
import com.miaosha.model.ItemModel;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luther
 */
@Controller("item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController{
    @Autowired
    private ItemService itemService;

    /**
     * 创建商品的controller
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name="title")String title,
                                       @RequestParam(name="description")String description,
                                       @RequestParam(name="price") String price,
                                       @RequestParam(name="stock")Integer stock,
                                       @RequestParam(name="imgUrl")String imgUrl
    ) throws BusinessException {
        //封装service请求用来创建商品
        ItemModel itemModel=new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(new BigDecimal(price));
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModelForReturn=itemService.createItem(itemModel);
        ItemVo itemVo=convertVOFromModel(itemModelForReturn);
        return CommonReturnType.create(itemVo);
    }
    /**
     * 获取商品详情页
     */
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name="id")Integer id
    ) throws BusinessException {
       ItemModel itemModel=itemService.getItemById(id);

       ItemVo itemVO=convertVOFromModel(itemModel);

       return CommonReturnType.create(itemVO);
    }
    /**
     * 商品列表页面浏览
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem(){
        List<ItemModel> itemModelList=itemService.listItem();
        //使用stream api将list内itemModel转化为itemvo
        List<ItemVo> itemVoList=itemModelList.stream().map(itemModel -> {
            ItemVo itemVo=this.convertVOFromModel(itemModel);
            return itemVo;
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVoList);
    }
    private ItemVo convertVOFromModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemVo itemVo=new ItemVo();
        BeanUtils.copyProperties(itemModel,itemVo);
        return itemVo;
    }
}
