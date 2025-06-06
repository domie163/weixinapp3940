package com.entity.vo;

import com.entity.BashiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 巴士信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("bashi")
public class BashiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 往返站点
     */

    @TableField(value = "bashi_name")
    private String bashiName;


    /**
     * 巴士类型
     */

    @TableField(value = "bashi_types")
    private Integer bashiTypes;


    /**
     * 照片
     */

    @TableField(value = "bashi_photo")
    private String bashiPhoto;


    /**
     * 积分
     */

    @TableField(value = "bashi_new_jifen")
    private Double bashiNewJifen;


    /**
     * 发车时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "bashi_time")
    private Date bashiTime;


    /**
     * 座位数量
     */

    @TableField(value = "zuowei_number")
    private Integer zuoweiNumber;


    /**
     * 已购座位
     */

    @TableField(value = "selected")
    private String selected;


    /**
     * 票价
     */

    @TableField(value = "bashi_new_money")
    private Double bashiNewMoney;


    /**
     * 逻辑删除
     */

    @TableField(value = "bashi_delete")
    private Integer bashiDelete;


    /**
     * 简介
     */

    @TableField(value = "bashi_content")
    private String bashiContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：往返站点
	 */
    public String getBashiName() {
        return bashiName;
    }


    /**
	 * 获取：往返站点
	 */

    public void setBashiName(String bashiName) {
        this.bashiName = bashiName;
    }
    /**
	 * 设置：巴士类型
	 */
    public Integer getBashiTypes() {
        return bashiTypes;
    }


    /**
	 * 获取：巴士类型
	 */

    public void setBashiTypes(Integer bashiTypes) {
        this.bashiTypes = bashiTypes;
    }
    /**
	 * 设置：照片
	 */
    public String getBashiPhoto() {
        return bashiPhoto;
    }


    /**
	 * 获取：照片
	 */

    public void setBashiPhoto(String bashiPhoto) {
        this.bashiPhoto = bashiPhoto;
    }
    /**
	 * 设置：积分
	 */
    public Double getBashiNewJifen() {
        return bashiNewJifen;
    }


    /**
	 * 获取：积分
	 */

    public void setBashiNewJifen(Double bashiNewJifen) {
        this.bashiNewJifen = bashiNewJifen;
    }
    /**
	 * 设置：发车时间
	 */
    public Date getBashiTime() {
        return bashiTime;
    }


    /**
	 * 获取：发车时间
	 */

    public void setBashiTime(Date bashiTime) {
        this.bashiTime = bashiTime;
    }
    /**
	 * 设置：座位数量
	 */
    public Integer getZuoweiNumber() {
        return zuoweiNumber;
    }


    /**
	 * 获取：座位数量
	 */

    public void setZuoweiNumber(Integer zuoweiNumber) {
        this.zuoweiNumber = zuoweiNumber;
    }
    /**
	 * 设置：已购座位
	 */
    public String getSelected() {
        return selected;
    }


    /**
	 * 获取：已购座位
	 */

    public void setSelected(String selected) {
        this.selected = selected;
    }
    /**
	 * 设置：票价
	 */
    public Double getBashiNewMoney() {
        return bashiNewMoney;
    }


    /**
	 * 获取：票价
	 */

    public void setBashiNewMoney(Double bashiNewMoney) {
        this.bashiNewMoney = bashiNewMoney;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getBashiDelete() {
        return bashiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setBashiDelete(Integer bashiDelete) {
        this.bashiDelete = bashiDelete;
    }
    /**
	 * 设置：简介
	 */
    public String getBashiContent() {
        return bashiContent;
    }


    /**
	 * 获取：简介
	 */

    public void setBashiContent(String bashiContent) {
        this.bashiContent = bashiContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
