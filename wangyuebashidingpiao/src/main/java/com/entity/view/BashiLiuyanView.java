package com.entity.view;

import com.entity.BashiLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("bashi_liuyan")
public class BashiLiuyanView extends BashiLiuyanEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 bashi
			/**
			* 往返站点
			*/
			private String bashiName;
			/**
			* 巴士类型
			*/
			private Integer bashiTypes;
				/**
				* 巴士类型的值
				*/
				private String bashiValue;
			/**
			* 照片
			*/
			private String bashiPhoto;
			/**
			* 积分
			*/
			private Double bashiNewJifen;
			/**
			* 发车时间
			*/
			@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
			@DateTimeFormat
			private Date bashiTime;
			/**
			* 座位数量
			*/
			private Integer zuoweiNumber;
			/**
			* 已购座位
			*/
			private String selected;
			/**
			* 票价
			*/
			private Double bashiNewMoney;
			/**
			* 逻辑删除
			*/
			private Integer bashiDelete;
			/**
			* 简介
			*/
			private String bashiContent;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 头像
			*/
			private String yonghuPhoto;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 积分
			*/
			private Double yonghuNewJifen;
			/**
			* 假删
			*/
			private Integer yonghuDelete;

	public BashiLiuyanView() {

	}

	public BashiLiuyanView(BashiLiuyanEntity bashiLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, bashiLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







				//级联表的get和set bashi
					/**
					* 获取： 往返站点
					*/
					public String getBashiName() {
						return bashiName;
					}
					/**
					* 设置： 往返站点
					*/
					public void setBashiName(String bashiName) {
						this.bashiName = bashiName;
					}
					/**
					* 获取： 巴士类型
					*/
					public Integer getBashiTypes() {
						return bashiTypes;
					}
					/**
					* 设置： 巴士类型
					*/
					public void setBashiTypes(Integer bashiTypes) {
						this.bashiTypes = bashiTypes;
					}


						/**
						* 获取： 巴士类型的值
						*/
						public String getBashiValue() {
							return bashiValue;
						}
						/**
						* 设置： 巴士类型的值
						*/
						public void setBashiValue(String bashiValue) {
							this.bashiValue = bashiValue;
						}
					/**
					* 获取： 照片
					*/
					public String getBashiPhoto() {
						return bashiPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setBashiPhoto(String bashiPhoto) {
						this.bashiPhoto = bashiPhoto;
					}
					/**
					* 获取： 积分
					*/
					public Double getBashiNewJifen() {
						return bashiNewJifen;
					}
					/**
					* 设置： 积分
					*/
					public void setBashiNewJifen(Double bashiNewJifen) {
						this.bashiNewJifen = bashiNewJifen;
					}
					/**
					* 获取： 发车时间
					*/
					public Date getBashiTime() {
						return bashiTime;
					}
					/**
					* 设置： 发车时间
					*/
					public void setBashiTime(Date bashiTime) {
						this.bashiTime = bashiTime;
					}
					/**
					* 获取： 座位数量
					*/
					public Integer getZuoweiNumber() {
						return zuoweiNumber;
					}
					/**
					* 设置： 座位数量
					*/
					public void setZuoweiNumber(Integer zuoweiNumber) {
						this.zuoweiNumber = zuoweiNumber;
					}
					/**
					* 获取： 已购座位
					*/
					public String getSelected() {
						return selected;
					}
					/**
					* 设置： 已购座位
					*/
					public void setSelected(String selected) {
						this.selected = selected;
					}
					/**
					* 获取： 票价
					*/
					public Double getBashiNewMoney() {
						return bashiNewMoney;
					}
					/**
					* 设置： 票价
					*/
					public void setBashiNewMoney(Double bashiNewMoney) {
						this.bashiNewMoney = bashiNewMoney;
					}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getBashiDelete() {
						return bashiDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setBashiDelete(Integer bashiDelete) {
						this.bashiDelete = bashiDelete;
					}
					/**
					* 获取： 简介
					*/
					public String getBashiContent() {
						return bashiContent;
					}
					/**
					* 设置： 简介
					*/
					public void setBashiContent(String bashiContent) {
						this.bashiContent = bashiContent;
					}

























				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}
					/**
					* 获取： 积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}
					/**
					* 获取： 假删
					*/
					public Integer getYonghuDelete() {
						return yonghuDelete;
					}
					/**
					* 设置： 假删
					*/
					public void setYonghuDelete(Integer yonghuDelete) {
						this.yonghuDelete = yonghuDelete;
					}



}
