package cn.stylefeng.guns.modular.qimei.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import net.sf.ehcache.config.TerracottaConfiguration.Consistency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
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
    @RequestMapping("/goods_update/{spec_good_id}")
    public String goodsUpdate(@PathVariable Integer spec_good_id, Model model) {
        SpecGood goods = goodsService.selectSpecGoodById(spec_good_id);
        model.addAttribute("item",goods);
        model.addAttribute("unit",  ConstantFactory.me().getDictsByName("单位", goods.getGood().getUnitId()));
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
    }
    

    /*
     * GoodsInfoDlg.collectData = function() {
	this.goodName = $("#name").val();
	this.imageUrl = $("#avatar").val();
	this.unitId = $("#unit").find("option:selected").val();

    var mutiString = "";
	$("[name='specItem']").each(function(){
        var specName = $(this).find("[name='itemName']").val();
        var specVlues = $(this).find("[name='itemValue']").val();
        mutiString = mutiString + (code + ":" + name + ":"+ num+";");
    });
	this.specMutiString = mutiString;
}
     */
    /**
     * 新增商品管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(String goodName, String imageUrl, String unitId, String specMutiString) {
        goodsService.addGoods(goodName, imageUrl, unitId, specMutiString);
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
    @RequestMapping(value = "/detail/{specGoodId}")
    @ResponseBody
    public Object detail(@PathVariable("specGoodId") Integer specGoodId) {
        return specGoodId;
    }
}
