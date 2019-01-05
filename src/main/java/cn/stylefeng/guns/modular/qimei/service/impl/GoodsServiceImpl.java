package cn.stylefeng.guns.modular.qimei.service.impl;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.qimei.dao.GoodsMapper;
import cn.stylefeng.guns.modular.qimei.dao.SpecGoodMapper;
import cn.stylefeng.guns.modular.qimei.model.Goods;
import cn.stylefeng.guns.modular.qimei.model.SpecGood;
import cn.stylefeng.guns.modular.qimei.service.IGoodsService;
import cn.stylefeng.guns.modular.system.model.LoginLog;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
	public void addGoods(Goods good, Map<String, String> specGoodMap) {
		//生成商品编号
		Integer goodCount = goodsMapper.selectCount(null) + 1;
		String goodNumber = String.format("%010d", goodCount);
		good.setNumber("P" + goodNumber);
		
		Integer goodRet = goodsMapper.insert(good);
		if(goodRet > 0) {
			for (Map.Entry<String, String> m : specGoodMap.entrySet()) {
				SpecGood specGood = new SpecGood();
				Integer specCount = specGoodMapper.selectCount(null) + 1;
				String specGoodNumber = String.format("%010d", specCount);
				specGood.setNumber("SP" + specGoodNumber);
				specGood.setName(m.getValue());
				specGood.setValue(m.getKey());
				specGood.setGoodsId(good.getId());
				specGood.setCreateDate(new Date());
				specGood.setUpdateDate(new Date());
				specGoodMapper.insert(specGood);
			}
		}
	}
	
	public Map<String, String>parseSpecFromStr(String str){
		Map<String, String> specMap = new HashMap<>();
		String[]specStrList = str.split(";");
		for (int i = 0; i < specStrList.length; i++) {
			String specStr = specStrList[i];
			String[] specObj = specStr.split(":");
			if(specObj.length == 2) {
				String name = specObj[0];
				String[] valueList = specObj[1].split("-");
				for (int j = 0; j < valueList.length; j++) {
					specMap.put(valueList[i],name);
				}
			}
		}
		
		return specMap;
	}

	@Override
	public void addGoods(String goodName, String imageUrl, String unitId, String specStr) {
		Map<String, String> specList = parseSpecFromStr(specStr);
		Goods good = new Goods();
		good.setName(goodName);
		good.setImageUrl(imageUrl);
		good.setCreateDate(new Date());
		good.setUpdateDate(new Date());
		good.setUnitId(Integer.parseInt(unitId));
		
		this.addGoods(good, specList);
	}

	@Override
	public List<Goods> getGoods( String goodName) {
		return goodsMapper.getGoods(goodName);
	}

	@Override
	public List<Map<String, Object>> getSpecGood(Page<SpecGood> page, String goodName) {
		return specGoodMapper.getSpecGood(page, goodName);
	}

	@Override
	public SpecGood selectSpecGoodById(Integer specGoodId) {
		return specGoodMapper.getOneSpecGood(specGoodId);
	}
}
