









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
 * 巴士购票订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/bashiOrder")
public class BashiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(BashiOrderController.class);

    @Autowired
    private BashiOrderService bashiOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private BashiService bashiService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private JifenjiluService jifenjiluService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = bashiOrderService.queryPage(params);

        //字典表数据转换
        List<BashiOrderView> list =(List<BashiOrderView>)page.getList();
        for(BashiOrderView c:list){
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
        BashiOrderEntity bashiOrder = bashiOrderService.selectById(id);
        if(bashiOrder !=null){
            //entity转view
            BashiOrderView view = new BashiOrderView();
            BeanUtils.copyProperties( bashiOrder , view );//把实体数据重构到view中

                //级联表
                BashiEntity bashi = bashiService.selectById(bashiOrder.getBashiId());
                if(bashi != null){
                    BeanUtils.copyProperties( bashi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setBashiId(bashi.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(bashiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody BashiOrderEntity bashiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bashiOrder:{}",this.getClass().getName(),bashiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            bashiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        bashiOrder.setInsertTime(new Date());
        bashiOrder.setCreateTime(new Date());
        bashiOrderService.insert(bashiOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BashiOrderEntity bashiOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,bashiOrder:{}",this.getClass().getName(),bashiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            bashiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<BashiOrderEntity> queryWrapper = new EntityWrapper<BashiOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BashiOrderEntity bashiOrderEntity = bashiOrderService.selectOne(queryWrapper);
        if(bashiOrderEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      bashiOrder.set
            //  }
            bashiOrderService.updateById(bashiOrder);//根据id更新
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
        bashiOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<BashiOrderEntity> bashiOrderList = new ArrayList<>();//上传的东西
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
                            BashiOrderEntity bashiOrderEntity = new BashiOrderEntity();
//                            bashiOrderEntity.setBashiOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            bashiOrderEntity.setBashiId(Integer.valueOf(data.get(0)));   //巴士 要改的
//                            bashiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            bashiOrderEntity.setBuyZuowei(data.get(0));                    //购买的座位 要改的
//                            bashiOrderEntity.setBashiOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            bashiOrderEntity.setBashiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            bashiOrderEntity.setBashiOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            bashiOrderEntity.setInsertTime(date);//时间
//                            bashiOrderEntity.setCreateTime(date);//时间
                            bashiOrderList.add(bashiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("bashiOrderUuidNumber")){
                                    List<String> bashiOrderUuidNumber = seachFields.get("bashiOrderUuidNumber");
                                    bashiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> bashiOrderUuidNumber = new ArrayList<>();
                                    bashiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("bashiOrderUuidNumber",bashiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<BashiOrderEntity> bashiOrderEntities_bashiOrderUuidNumber = bashiOrderService.selectList(new EntityWrapper<BashiOrderEntity>().in("bashi_order_uuid_number", seachFields.get("bashiOrderUuidNumber")));
                        if(bashiOrderEntities_bashiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(BashiOrderEntity s:bashiOrderEntities_bashiOrderUuidNumber){
                                repeatFields.add(s.getBashiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        bashiOrderService.insertBatch(bashiOrderList);
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
        PageUtils page = bashiOrderService.queryPage(params);

        //字典表数据转换
        List<BashiOrderView> list =(List<BashiOrderView>)page.getList();
        for(BashiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BashiOrderEntity bashiOrder = bashiOrderService.selectById(id);
            if(bashiOrder !=null){


                //entity转view
                BashiOrderView view = new BashiOrderView();
                BeanUtils.copyProperties( bashiOrder , view );//把实体数据重构到view中

                //级联表
                    BashiEntity bashi = bashiService.selectById(bashiOrder.getBashiId());
                if(bashi != null){
                    BeanUtils.copyProperties( bashi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setBashiId(bashi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(bashiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody BashiOrderEntity bashiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,bashiOrder:{}",this.getClass().getName(),bashiOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            BashiEntity bashiEntity = bashiService.selectById(bashiOrder.getBashiId());
            if(bashiEntity == null){
                return R.error(511,"查不到该物品");
            }

            if(false){
            }
            List<String> buyZuoweiList = new ArrayList<>(Arrays.asList(bashiOrder.getBuyZuowei().split(",")));//购买的座位
            List<String> selectedList = null;
            List<String> allList = new ArrayList<>();
            allList.addAll(buyZuoweiList);
            if(StringUtils.isNotBlank(bashiEntity.getSelected())){
                selectedList = Arrays.asList(bashiEntity.getSelected().split(","));//已经被购买的座位   selectedList.addAll(buyZuoweiList);
                allList.addAll(selectedList);
            }
            String s= "";
            for(String a:allList){
                s+=(a+",");
            }
            bashiEntity.setSelected(s.substring(0, s.length() - 1));
            if(selectedList != null && selectedList.size()>0){
                buyZuoweiList.retainAll(selectedList);//判断当前购买list包含已经被购买的list中是否有重复的  有的话 就保留
                if(buyZuoweiList != null && buyZuoweiList.size()>0 ){
                    return R.error(511,buyZuoweiList.toString()+" 的座位已经被使用");
                }
                else if(bashiEntity.getBashiNewMoney() == null){
                    return R.error(511,"物品价格不能为空");
                }
            }



            //计算所获得积分
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - bashiEntity.getBashiNewMoney()*(bashiOrder.getBuyZuowei().split(",").length);//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            bashiOrder.setBashiOrderTypes(1); //设置订单状态为已支付
            bashiOrder.setBashiOrderTruePrice(bashiEntity.getBashiNewMoney()*(bashiOrder.getBuyZuowei().split(",").length)); //设置实付价格
                bashiOrder.setBashiOrderPaymentTypes(1);
                bashiOrder.setInsertTime(new Date());
                bashiOrder.setCreateTime(new Date());
                bashiOrderService.insert(bashiOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + (bashiEntity.getBashiNewJifen() * bashiOrder.getBuyZuowei().split(",").length)); //设置现积分
            yonghuService.updateById(yonghuEntity);

            JifenjiluEntity jifenjiluEntity = new JifenjiluEntity();
            jifenjiluEntity.setYonghuId(yonghuEntity.getId());
            jifenjiluEntity.setJifenjiluName("购买往返："+bashiEntity.getBashiName()+" 巴士的"+bashiOrder.getBuyZuowei()+"座位");
            jifenjiluEntity.setJifenjiluNumber(bashiEntity.getBashiNewJifen() * bashiOrder.getBuyZuowei().split(",").length);
            jifenjiluEntity.setJifenTypes(2);
            jifenjiluEntity.setInsertTime(new Date());
            jifenjiluEntity.setCreateTime(new Date());
            jifenjiluService.insert(jifenjiluEntity);
            //添加积分记录
            bashiService.updateById(bashiEntity);
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

        JifenjiluEntity jifenjiluEntity = new JifenjiluEntity();
        if("用户".equals(role)){
            BashiOrderEntity bashiOrder = bashiOrderService.selectById(id);
            Integer buyNumber = bashiOrder.getBuyZuowei().split(",").length;
            Integer bashiOrderPaymentTypes = bashiOrder.getBashiOrderPaymentTypes();
            Integer bashiId = bashiOrder.getBashiId();
            if(bashiId == null)
                return R.error(511,"查不到该物品");
            BashiEntity bashiEntity = bashiService.selectById(bashiId);
            if(bashiEntity == null)
                return R.error(511,"查不到该物品");
            Double bashiNewMoney = bashiEntity.getBashiNewMoney();
            if(bashiNewMoney == null)
                return R.error(511,"物品价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;


            //判断是什么支付方式 1代表余额 2代表积分
            if(bashiOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = bashiEntity.getBashiNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - (bashiEntity.getBashiNewJifen() * bashiOrder.getBuyZuowei().split(",").length)); //设置现积分

                jifenjiluEntity.setYonghuId(yonghuEntity.getId());
                jifenjiluEntity.setJifenjiluName("退往返："+bashiEntity.getBashiName()+" 巴士中"+bashiOrder.getBuyZuowei()+"座位的票");
                jifenjiluEntity.setJifenjiluNumber(bashiEntity.getBashiNewJifen() * bashiOrder.getBuyZuowei().split(",").length);
                jifenjiluEntity.setJifenTypes(1);
                jifenjiluEntity.setInsertTime(new Date());
                jifenjiluEntity.setCreateTime(new Date());
            }

            String selected = bashiEntity.getSelected();
            if(StringUtil.isEmpty(selected)){
                bashiEntity.setSelected("");
            }else{
                List<String> selectedList = new ArrayList<>(Arrays.asList(bashiEntity.getSelected().split(",")));//已经选择的
                String buyZuowei = bashiOrder.getBuyZuowei();
                if(StringUtil.isNotEmpty(buyZuowei)){
                    String[] buyZuoweiSplit = buyZuowei.split(",");
                    for(String s:buyZuoweiSplit){
                        selectedList.remove("s");
                    }
                }
                if(selectedList != null && selectedList.size() > 0 ){
                    bashiEntity.setSelected(StringUtils.join(selectedList, ","));
                }else{
                    bashiEntity.setSelected("");
                }
            }



            jifenjiluService.insert(jifenjiluEntity);//积分退款记录
            bashiOrder.setBashiOrderTypes(2);//设置订单状态为退款
            bashiOrderService.updateById(bashiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            bashiService.updateById(bashiEntity);//更新订单中物品的信息
            return R.ok();
        }else{
            return R.error(511,"您没有权限退款");
        }
    }

















}
