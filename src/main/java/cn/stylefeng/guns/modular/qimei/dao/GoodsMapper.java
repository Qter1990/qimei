package cn.stylefeng.guns.modular.qimei.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.stylefeng.guns.modular.qimei.model.Goods;
import cn.stylefeng.guns.modular.qimei.model.SpecGood;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author vincent
 * @since 2019-01-03
 */
public interface GoodsMapper extends BaseMapper<Goods> {
	
	public List<Goods> getGoods(@Param("goodName")String goodName) ;
}
