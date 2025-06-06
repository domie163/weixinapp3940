package com.dao;

import com.entity.BashiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BashiView;

/**
 * 巴士信息 Dao 接口
 *
 * @author 
 */
public interface BashiDao extends BaseMapper<BashiEntity> {

   List<BashiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
