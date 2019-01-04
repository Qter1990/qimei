package cn.stylefeng.guns.modular.qimei.service.impl;

import cn.stylefeng.guns.modular.qimei.dao.GoodsMapper;
import cn.stylefeng.guns.modular.qimei.dao.SpecGoodMapper;
import cn.stylefeng.guns.modular.qimei.model.Goods;
import cn.stylefeng.guns.modular.qimei.model.SpecGood;
import cn.stylefeng.guns.modular.qimei.service.IGoodsService;
import cn.stylefeng.guns.modular.system.model.LoginLog;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author vincent
 * @since 2019-01-03
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private SpecGoodMapper specGoodMapper;

	@Override
	public void addGoods(Goods good, List<SpecGood> specGoodList) {
		//生成商品编号
		Integer goodCount = goodsMapper.selectCount(null);
		String goodNumber = String.format("%010d", goodCount);
		good.setNumber(goodNumber);
		
		Integer goodRet = goodsMapper.insert(good);
		if(goodRet > 0) {
			for (SpecGood specGood : specGoodList) {
				specGood.setGoodsId(goodRet);
				specGoodMapper.insert(specGood);
			}
		}
	}

	@Override
	public List<Goods> getGoods( String goodName) {
		return goodsMapper.getGoods(goodName);
	}

	@Override
	public List<Map<String, Object>> getSpecGood(Page<SpecGood> page, String goodName) {
		return specGoodMapper.getSpecGood(page, goodName);
	}
}
