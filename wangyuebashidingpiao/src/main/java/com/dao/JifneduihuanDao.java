package com.dao;

import com.entity.JifneduihuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JifneduihuanView;

/**
 * 积分兑换 Dao 接口
 *
 * @author 
 */
public interface JifneduihuanDao extends BaseMapper<JifneduihuanEntity> {

   List<JifneduihuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
