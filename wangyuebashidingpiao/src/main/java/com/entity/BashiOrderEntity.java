package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 巴士购票订单
 *
 * @author 
 * @email
 */
@TableName("bashi_order")
public class BashiOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public BashiOrderEntity() {

	}

	public BashiOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单号
     */
    @TableField(value = "bashi_order_uuid_number")

    private String bashiOrderUuidNumber;


    /**
     * 巴士
     */
    @TableField(value = "bashi_id")

    private Integer bashiId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 购买的座位
     */
    @TableField(value = "buy_zuowei")

    private String buyZuowei;


    /**
     * 实付价格
     */
    @TableField(value = "bashi_order_true_price")

    private Double bashiOrderTruePrice;


    /**
     * 订单类型
     */
    @TableField(value = "bashi_order_types")

    private Integer bashiOrderTypes;


    /**
     * 支付类型
     */
    @TableField(value = "bashi_order_payment_types")

    private Integer bashiOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getBashiOrderUuidNumber() {
        return bashiOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setBashiOrderUuidNumber(String bashiOrderUuidNumber) {
        this.bashiOrderUuidNumber = bashiOrderUuidNumber;
    }
    /**
	 * 设置：巴士
	 */
    public Integer getBashiId() {
        return bashiId;
    }


    /**
	 * 获取：巴士
	 */

    public void setBashiId(Integer bashiId) {
        this.bashiId = bashiId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买的座位
	 */
    public String getBuyZuowei() {
        return buyZuowei;
    }


    /**
	 * 获取：购买的座位
	 */

    public void setBuyZuowei(String buyZuowei) {
        this.buyZuowei = buyZuowei;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getBashiOrderTruePrice() {
        return bashiOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setBashiOrderTruePrice(Double bashiOrderTruePrice) {
        this.bashiOrderTruePrice = bashiOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getBashiOrderTypes() {
        return bashiOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setBashiOrderTypes(Integer bashiOrderTypes) {
        this.bashiOrderTypes = bashiOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getBashiOrderPaymentTypes() {
        return bashiOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setBashiOrderPaymentTypes(Integer bashiOrderPaymentTypes) {
        this.bashiOrderPaymentTypes = bashiOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BashiOrder{" +
            "id=" + id +
            ", bashiOrderUuidNumber=" + bashiOrderUuidNumber +
            ", bashiId=" + bashiId +
            ", yonghuId=" + yonghuId +
            ", buyZuowei=" + buyZuowei +
            ", bashiOrderTruePrice=" + bashiOrderTruePrice +
            ", bashiOrderTypes=" + bashiOrderTypes +
            ", bashiOrderPaymentTypes=" + bashiOrderPaymentTypes +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
