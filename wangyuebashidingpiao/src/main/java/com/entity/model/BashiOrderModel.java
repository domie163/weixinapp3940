package com.entity.model;

import com.entity.BashiOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 巴士购票订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class BashiOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String bashiOrderUuidNumber;


    /**
     * 巴士
     */
    private Integer bashiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买的座位
     */
    private String buyZuowei;


    /**
     * 实付价格
     */
    private Double bashiOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer bashiOrderTypes;


    /**
     * 支付类型
     */
    private Integer bashiOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getBashiOrderUuidNumber() {
        return bashiOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setBashiOrderUuidNumber(String bashiOrderUuidNumber) {
        this.bashiOrderUuidNumber = bashiOrderUuidNumber;
    }
    /**
	 * 获取：巴士
	 */
    public Integer getBashiId() {
        return bashiId;
    }


    /**
	 * 设置：巴士
	 */
    public void setBashiId(Integer bashiId) {
        this.bashiId = bashiId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买的座位
	 */
    public String getBuyZuowei() {
        return buyZuowei;
    }


    /**
	 * 设置：购买的座位
	 */
    public void setBuyZuowei(String buyZuowei) {
        this.buyZuowei = buyZuowei;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getBashiOrderTruePrice() {
        return bashiOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setBashiOrderTruePrice(Double bashiOrderTruePrice) {
        this.bashiOrderTruePrice = bashiOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getBashiOrderTypes() {
        return bashiOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setBashiOrderTypes(Integer bashiOrderTypes) {
        this.bashiOrderTypes = bashiOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getBashiOrderPaymentTypes() {
        return bashiOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setBashiOrderPaymentTypes(Integer bashiOrderPaymentTypes) {
        this.bashiOrderPaymentTypes = bashiOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
