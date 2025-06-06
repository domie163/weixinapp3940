package com.entity.model;

import com.entity.JifneduihuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 积分兑换
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JifneduihuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 物品名称
     */
    private String jifneduihuanName;


    /**
     * 物品类型
     */
    private Integer jifneduihuanTypes;


    /**
     * 物品照片
     */
    private String jifneduihuanPhoto;


    /**
     * 积分
     */
    private Double jifneduihuanNewMoney;


    /**
     * 逻辑删除
     */
    private Integer jifneduihuanDelete;


    /**
     * 简介
     */
    private String jifneduihuanContent;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：物品名称
	 */
    public String getJifneduihuanName() {
        return jifneduihuanName;
    }


    /**
	 * 设置：物品名称
	 */
    public void setJifneduihuanName(String jifneduihuanName) {
        this.jifneduihuanName = jifneduihuanName;
    }
    /**
	 * 获取：物品类型
	 */
    public Integer getJifneduihuanTypes() {
        return jifneduihuanTypes;
    }


    /**
	 * 设置：物品类型
	 */
    public void setJifneduihuanTypes(Integer jifneduihuanTypes) {
        this.jifneduihuanTypes = jifneduihuanTypes;
    }
    /**
	 * 获取：物品照片
	 */
    public String getJifneduihuanPhoto() {
        return jifneduihuanPhoto;
    }


    /**
	 * 设置：物品照片
	 */
    public void setJifneduihuanPhoto(String jifneduihuanPhoto) {
        this.jifneduihuanPhoto = jifneduihuanPhoto;
    }
    /**
	 * 获取：积分
	 */
    public Double getJifneduihuanNewMoney() {
        return jifneduihuanNewMoney;
    }


    /**
	 * 设置：积分
	 */
    public void setJifneduihuanNewMoney(Double jifneduihuanNewMoney) {
        this.jifneduihuanNewMoney = jifneduihuanNewMoney;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJifneduihuanDelete() {
        return jifneduihuanDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setJifneduihuanDelete(Integer jifneduihuanDelete) {
        this.jifneduihuanDelete = jifneduihuanDelete;
    }
    /**
	 * 获取：简介
	 */
    public String getJifneduihuanContent() {
        return jifneduihuanContent;
    }


    /**
	 * 设置：简介
	 */
    public void setJifneduihuanContent(String jifneduihuanContent) {
        this.jifneduihuanContent = jifneduihuanContent;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
