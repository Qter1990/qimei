package cn.stylefeng.guns.modular.qimei.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import cn.stylefeng.guns.modular.qimei.model.Goods;
import cn.stylefeng.guns.modular.qimei.model.SpecGood;
import cn.stylefeng.guns.modular.qimei.service.IGoodsService;
import cn.stylefeng.guns.modular.qimei.warpper.GoodWarpper;

/**
 * 商品管理控制器
 *
 * @author fengshuonan
 * @Date 2019-01-03 10:42:00
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    private String PREFIX = "/qimei/goods/";

    @Autowired
    private IGoodsService goodsService;
    

    /**
     * 跳转到商品管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "goods.html";
    }

    /**
     * 跳转到添加商品管理
     */
    @RequestMapping("/goods_add")
    public String goodsAdd() {
        return PREFIX + "goods_add.html";
    }

    /**
     * 跳转到修改商品管理
     */
    @RequestMapping("/goods_update/{goodsId}")
    public String goodsUpdate(@PathVariable Integer goodsId, Model model) {
        Goods goods = goodsService.selectById(goodsId);
        model.addAttribute("item",goods);
        LogObjectHolder.me().set(goods);
        return PREFIX + "goods_edit.html";
    }

    /**
     * 获取商品管理列表
     */
    
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	String goodName = "耐克";
    	
    	Page<SpecGood>page = new PageFactory<SpecGood>().defaultPage();

        List<Map<String, Object>> specGoods = goodsService.getSpecGood(page, goodName);
        
        page.setRecords(new GoodWarpper(specGoods).wrap());
        return new PageInfoBT<>(page);

//        for (SpecGood specGood : specGoods) {
//			Map<String, Object>specGoodMap = new HashMap<>();
//			specGoodMap.put("id", specGood.getGood().getId());
//			specGoodMap.put("image", specGood.getGood().getImageUrl());
//			specGoodMap.put("number", specGood.getGood().getNumber());
//			specGoodMap.put("name", specGood.getGood().getName());
//			specGoodMap.put("spec", specGood.getName());
//			specGoodMap.put("unit", specGood.getGood().getUnitId());
//			specGoodMap.put("cost", specGood.getCost());
//			specGoodMap.put("book", specGood.getBookCount());
//			specGoodMap.put("stock", specGood.getStock());
//			specGoodMap.put("createDate", specGood.getGood().getCreateDate());
//			specGoodMap.put("updateDate", specGood.getGood().getUpdateDate());
//			specGoodsMap.add(specGoodMap);
//		}
    }
    

    /**
     * 新增商品管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Goods goods) {
        goodsService.insert(goods);
        return SUCCESS_TIP;
    }

    /**
     * 删除商品管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer goodsId) {
        goodsService.deleteById(goodsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改商品管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Goods goods) {
        goodsService.updateById(goods);
        return SUCCESS_TIP;
    }

    /**
     * 商品管理详情
     */
    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Object detail(@PathVariable("goodsId") Integer goodsId) {
        return goodsService.selectById(goodsId);
    }
}
