package com.entity.view;

import com.entity.JifneduihuanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 积分兑换
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jifneduihuan")
public class JifneduihuanView extends JifneduihuanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 物品类型的值
		*/
		private String jifneduihuanValue;



	public JifneduihuanView() {

	}

	public JifneduihuanView(JifneduihuanEntity jifneduihuanEntity) {
		try {
			BeanUtils.copyProperties(this, jifneduihuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 物品类型的值
			*/
			public String getJifneduihuanValue() {
				return jifneduihuanValue;
			}
			/**
			* 设置： 物品类型的值
			*/
			public void setJifneduihuanValue(String jifneduihuanValue) {
				this.jifneduihuanValue = jifneduihuanValue;
			}












}
