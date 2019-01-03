package cn.stylefeng.guns.modular.qimei.model;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author vincent
 * @since 2019-01-03
 */
@TableName("qimei_goods")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 商品编号
     */
    private String number;
    /**
     * 商品名称
     */
    private String name;
    /*
     * 商品图片
     */
    private String imageUrl;
    /**
     * 单位-字典表 type=2的ID
     */
    @TableField("unit_id")
    private Integer unitId;
    @TableField("create_date")
    private Date createDate;
    @TableField("update_date")
    private Date updateDate;
    /**
     * 数据标记 0显示 1删除
     */
    private Integer flag;
    
    /*
     * 商品规格
     */
    private List<SpecGood> specGoods;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String image_url) {
		this.imageUrl = image_url;
	}

	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<SpecGood> getSpecGoods() {
		return specGoods;
	}

	public void setSpecGoods(List<SpecGood> specGoods) {
		this.specGoods = specGoods;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Goods{" +
        ", id=" + id +
        ", number=" + number +
        ", name=" + name +
        ", unitId=" + unitId +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", flag=" + flag +
        "}";
    }
}
