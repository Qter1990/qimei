package cn.stylefeng.guns.modular.qimei.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品规格表，一对多
 * </p>
 *
 * @author vincent
 * @since 2019-01-03
 */
@TableName("qimei_spec_good")
public class SpecGood extends Model<SpecGood> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 规格名称
     */
    private String name;
    /**
     * 商品ID
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 商品编号
     */
    private String number;
    /**
     * 库存数量
     */
    private Integer stock;
    /**
     * 成本价
     */
    private BigDecimal cost;
    /**
     * 预定数量
     */
    @TableField("book_count")
    private Integer bookCount;
    @TableField("create_date")
    private Date createDate;
    @TableField("update_date")
    private Date updateDate;
    private String flag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SpecGood{" +
        ", id=" + id +
        ", name=" + name +
        ", goodsId=" + goodsId +
        ", number=" + number +
        ", stock=" + stock +
        ", cost=" + cost +
        ", bookCount=" + bookCount +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", flag=" + flag +
        "}";
    }
}
