package cn.stylefeng.guns.modular.qimei.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.stylefeng.guns.modular.qimei.model.Goods;
import cn.stylefeng.guns.modular.qimei.model.SpecGood;
import cn.stylefeng.guns.modular.system.model.LoginLog;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author vincent
 * @since 2019-01-03
 */
public interface IGoodsService extends IService<Goods> {

	/*
	 * 添加多种规格商品
	 */
	public void addGoods(Goods goods, List<SpecGood> specGoodList); 
	
	/*
	 * 获取商品列表
	 */
	List<Map<String, Object>> getGoods(Page<SpecGood> page, String goodName, String orderByField, boolean asc);
}
