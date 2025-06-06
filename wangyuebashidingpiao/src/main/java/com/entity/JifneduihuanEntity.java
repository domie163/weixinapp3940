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
 * 积分兑换
 *
 * @author 
 * @email
 */
@TableName("jifneduihuan")
public class JifneduihuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JifneduihuanEntity() {

	}

	public JifneduihuanEntity(T t) {
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
     * 物品名称
     */
    @TableField(value = "jifneduihuan_name")

    private String jifneduihuanName;


    /**
     * 物品类型
     */
    @TableField(value = "jifneduihuan_types")

    private Integer jifneduihuanTypes;


    /**
     * 物品照片
     */
    @TableField(value = "jifneduihuan_photo")

    private String jifneduihuanPhoto;


    /**
     * 积分
     */
    @TableField(value = "jifneduihuan_new_money")

    private Double jifneduihuanNewMoney;


    /**
     * 逻辑删除
     */
    @TableField(value = "jifneduihuan_delete")

    private Integer jifneduihuanDelete;


    /**
     * 简介
     */
    @TableField(value = "jifneduihuan_content")

    private String jifneduihuanContent;


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
	 * 设置：物品名称
	 */
    public String getJifneduihuanName() {
        return jifneduihuanName;
    }


    /**
	 * 获取：物品名称
	 */

    public void setJifneduihuanName(String jifneduihuanName) {
        this.jifneduihuanName = jifneduihuanName;
    }
    /**
	 * 设置：物品类型
	 */
    public Integer getJifneduihuanTypes() {
        return jifneduihuanTypes;
    }


    /**
	 * 获取：物品类型
	 */

    public void setJifneduihuanTypes(Integer jifneduihuanTypes) {
        this.jifneduihuanTypes = jifneduihuanTypes;
    }
    /**
	 * 设置：物品照片
	 */
    public String getJifneduihuanPhoto() {
        return jifneduihuanPhoto;
    }


    /**
	 * 获取：物品照片
	 */

    public void setJifneduihuanPhoto(String jifneduihuanPhoto) {
        this.jifneduihuanPhoto = jifneduihuanPhoto;
    }
    /**
	 * 设置：积分
	 */
    public Double getJifneduihuanNewMoney() {
        return jifneduihuanNewMoney;
    }


    /**
	 * 获取：积分
	 */

    public void setJifneduihuanNewMoney(Double jifneduihuanNewMoney) {
        this.jifneduihuanNewMoney = jifneduihuanNewMoney;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getJifneduihuanDelete() {
        return jifneduihuanDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setJifneduihuanDelete(Integer jifneduihuanDelete) {
        this.jifneduihuanDelete = jifneduihuanDelete;
    }
    /**
	 * 设置：简介
	 */
    public String getJifneduihuanContent() {
        return jifneduihuanContent;
    }


    /**
	 * 获取：简介
	 */

    public void setJifneduihuanContent(String jifneduihuanContent) {
        this.jifneduihuanContent = jifneduihuanContent;
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
        return "Jifneduihuan{" +
            "id=" + id +
            ", jifneduihuanName=" + jifneduihuanName +
            ", jifneduihuanTypes=" + jifneduihuanTypes +
            ", jifneduihuanPhoto=" + jifneduihuanPhoto +
            ", jifneduihuanNewMoney=" + jifneduihuanNewMoney +
            ", jifneduihuanDelete=" + jifneduihuanDelete +
            ", jifneduihuanContent=" + jifneduihuanContent +
            ", createTime=" + createTime +
        "}";
    }
}
