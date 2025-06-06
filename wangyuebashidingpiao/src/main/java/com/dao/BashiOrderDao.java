package com.dao;

import com.entity.BashiOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BashiOrderView;

/**
 * 巴士购票订单 Dao 接口
 *
 * @author 
 */
public interface BashiOrderDao extends BaseMapper<BashiOrderEntity> {

   List<BashiOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
