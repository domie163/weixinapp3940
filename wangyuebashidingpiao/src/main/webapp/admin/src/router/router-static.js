import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

         import users from '@/views/modules/users/list'
        import bashi from '@/views/modules/bashi/list'
        import bashiLiuyan from '@/views/modules/bashiLiuyan/list'
        import bashiOrder from '@/views/modules/bashiOrder/list'
        import chat from '@/views/modules/chat/list'
        import dictionary from '@/views/modules/dictionary/list'
        import jifenjilu from '@/views/modules/jifenjilu/list'
        import jifneduihuan from '@/views/modules/jifneduihuan/list'
        import news from '@/views/modules/news/list'
        import yonghu from '@/views/modules/yonghu/list'
        import config from '@/views/modules/config/list'
        import dictionaryBashi from '@/views/modules/dictionaryBashi/list'
        import dictionaryBashiOrder from '@/views/modules/dictionaryBashiOrder/list'
        import dictionaryJifen from '@/views/modules/dictionaryJifen/list'
        import dictionaryJifneduihuan from '@/views/modules/dictionaryJifneduihuan/list'
        import dictionaryNews from '@/views/modules/dictionaryNews/list'
        import dictionarySex from '@/views/modules/dictionarySex/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryBashi',
        name: '巴士类型名称',
        component: dictionaryBashi
    }
    ,{
        path: '/dictionaryBashiOrder',
        name: '订单类型',
        component: dictionaryBashiOrder
    }
    ,{
        path: '/dictionaryJifen',
        name: '类型',
        component: dictionaryJifen
    }
    ,{
        path: '/dictionaryJifneduihuan',
        name: '物品类型名称',
        component: dictionaryJifneduihuan
    }
    ,{
        path: '/dictionaryNews',
        name: '新闻类型名称',
        component: dictionaryNews
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型名称',
        component: dictionarySex
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/bashi',
        name: '巴士信息',
        component: bashi
      }
    ,{
        path: '/bashiLiuyan',
        name: '评论',
        component: bashiLiuyan
      }
    ,{
        path: '/bashiOrder',
        name: '巴士购票订单',
        component: bashiOrder
      }
    ,{
        path: '/chat',
        name: '客服聊天',
        component: chat
      }
    ,{
        path: '/dictionary',
        name: '字典表',
        component: dictionary
      }
    ,{
        path: '/jifenjilu',
        name: '积分记录',
        component: jifenjilu
      }
    ,{
        path: '/jifneduihuan',
        name: '积分兑换',
        component: jifneduihuan
      }
    ,{
        path: '/news',
        name: '新闻信息',
        component: news
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
