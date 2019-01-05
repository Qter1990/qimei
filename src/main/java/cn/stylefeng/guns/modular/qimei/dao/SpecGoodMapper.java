package cn.stylefeng.guns.modular.qimei.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.stylefeng.guns.modular.qimei.model.SpecGood;

/**
 * <p>
 * 商品规格表，一对多 Mapper 接口
 * </p>
 *
 * @author vincent
 * @since 2019-01-03
 */
public interface SpecGoodMapper extends BaseMapper<SpecGood> {
	
	public List<Map<String, Object>> getSpecGood(@Param("page") Page<SpecGood> page, @Param("goodName")String goodName);

	public SpecGood getOneSpecGood(@Param("id")Integer id);
}
