package com.dao;

import com.entity.BashiLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BashiLiuyanView;

/**
 * 评论 Dao 接口
 *
 * @author 
 */
public interface BashiLiuyanDao extends BaseMapper<BashiLiuyanEntity> {

   List<BashiLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
