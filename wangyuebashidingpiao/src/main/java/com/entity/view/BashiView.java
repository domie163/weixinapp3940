package com.entity.view;

import com.entity.BashiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 巴士信息
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("bashi")
public class BashiView extends BashiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 巴士类型的值
		*/
		private String bashiValue;



	public BashiView() {

	}

	public BashiView(BashiEntity bashiEntity) {
		try {
			BeanUtils.copyProperties(this, bashiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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












}
