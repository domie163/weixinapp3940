









package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 巴士信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/bashi")
public class BashiController {
    private static final Logger logger = LoggerFactory.getLogger(BashiController.class);

    @Autowired
    private BashiService bashiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("bashiDeleteStart",1);params.put("bashiDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = bashiService.queryPage(params);

        //字典表数据转换
        List<BashiView> list =(List<BashiView>)page.getList();
        for(BashiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BashiEntity bashi = bashiService.selectById(id);
        if(bashi !=null){
            //entity转view
            BashiView view = new BashiView();
            BeanUtils.copyProperties( bashi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody BashiEntity bashi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bashi:{}",this.getClass().getName(),bashi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        Wrapper<BashiEntity> queryWrapper = new EntityWrapper<BashiEntity>()
            .eq("bashi_name", bashi.getBashiName())
            .eq("bashi_types", bashi.getBashiTypes())
            .eq("zuowei_number", bashi.getZuoweiNumber())
            .eq("selected", bashi.getSelected())
            .eq("bashi_delete", bashi.getBashiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BashiEntity bashiEntity = bashiService.selectOne(queryWrapper);
        if(bashiEntity==null){
            bashi.setBashiDelete(1);
            bashi.setCreateTime(new Date());
            bashiService.insert(bashi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BashiEntity bashi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,bashi:{}",this.getClass().getName(),bashi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<BashiEntity> queryWrapper = new EntityWrapper<BashiEntity>()
            .notIn("id",bashi.getId())
            .andNew()
            .eq("bashi_name", bashi.getBashiName())
            .eq("bashi_types", bashi.getBashiTypes())
            .eq("zuowei_number", bashi.getZuoweiNumber())
            .eq("selected", bashi.getSelected())
            .eq("bashi_delete", bashi.getBashiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BashiEntity bashiEntity = bashiService.selectOne(queryWrapper);
        if("".equals(bashi.getBashiPhoto()) || "null".equals(bashi.getBashiPhoto())){
                bashi.setBashiPhoto(null);
        }
        if(bashiEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      bashi.set
            //  }
            bashiService.updateById(bashi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<BashiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            BashiEntity bashiEntity = new BashiEntity();
            bashiEntity.setId(id);
            bashiEntity.setBashiDelete(2);
            list.add(bashiEntity);
        }
        if(list != null && list.size() >0){
            bashiService.updateBatchById(list);
        }
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<BashiEntity> bashiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            BashiEntity bashiEntity = new BashiEntity();
//                            bashiEntity.setBashiName(data.get(0));                    //往返站点 要改的
//                            bashiEntity.setBashiTypes(Integer.valueOf(data.get(0)));   //巴士类型 要改的
//                            bashiEntity.setBashiPhoto("");//照片
//                            bashiEntity.setBashiNewJifen(data.get(0));                    //积分 要改的
//                            bashiEntity.setBashiTime(new Date(data.get(0)));          //发车时间 要改的
//                            bashiEntity.setZuoweiNumber(Integer.valueOf(data.get(0)));   //座位数量 要改的
//                            bashiEntity.setSelected(data.get(0));                    //已购座位 要改的
//                            bashiEntity.setBashiNewMoney(data.get(0));                    //票价 要改的
//                            bashiEntity.setBashiDelete(1);//逻辑删除字段
//                            bashiEntity.setBashiContent("");//照片
//                            bashiEntity.setCreateTime(date);//时间
                            bashiList.add(bashiEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        bashiService.insertBatch(bashiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = bashiService.queryPage(params);

        //字典表数据转换
        List<BashiView> list =(List<BashiView>)page.getList();
        for(BashiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BashiEntity bashi = bashiService.selectById(id);
            if(bashi !=null){


                //entity转view
                BashiView view = new BashiView();
                BeanUtils.copyProperties( bashi , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody BashiEntity bashi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,bashi:{}",this.getClass().getName(),bashi.toString());
        Wrapper<BashiEntity> queryWrapper = new EntityWrapper<BashiEntity>()
            .eq("bashi_name", bashi.getBashiName())
            .eq("bashi_types", bashi.getBashiTypes())
            .eq("zuowei_number", bashi.getZuoweiNumber())
            .eq("selected", bashi.getSelected())
            .eq("bashi_delete", bashi.getBashiDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BashiEntity bashiEntity = bashiService.selectOne(queryWrapper);
        if(bashiEntity==null){
            bashi.setBashiDelete(1);
            bashi.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      bashi.set
        //  }
        bashiService.insert(bashi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



}
