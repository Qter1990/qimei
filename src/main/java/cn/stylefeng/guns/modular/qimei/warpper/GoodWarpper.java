package cn.stylefeng.guns.modular.qimei.warpper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;

public class GoodWarpper extends BaseControllerWrapper {


    public GoodWarpper(Map<String, Object> single) {
        super(single);
    }

    public GoodWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public GoodWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public GoodWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }
	
	@Override
	protected void wrapTheMap(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

}
