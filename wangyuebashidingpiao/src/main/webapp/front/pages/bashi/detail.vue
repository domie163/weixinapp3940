<template>
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback"
		:bottom="100">
		<view class="container">
			<view>
				<swiper class="swiper" :indicator-dots='".swiper-pagination"==null?false:true'
					:autoplay='autoplaySwiper' :circular='true' indicator-active-color='#000000'
					indicator-color='rgba(0, 0, 0, .3)' :duration='1000' :interval='intervalSwiper'
					:vertical='"horizontal"=="horizontal"?false:true'>
					<swiper-item v-for="(swiper,index) in swiperList" :key="index">
						<image mode="aspectFill" :src="swiper"></image>
					</swiper-item>
				</swiper>
			</view>
			<view class="detail-content">

				<view class="price detail-list-item priceFavor"
					:style='{"boxShadow":"0 0 2rpx rgba(0,0,0,.8)","margin":"0 ","borderColor":"#fff","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0","borderStyle":"solid","height":"68rpx"}'>
					<view class="text" :style='{"color":"#333","textAlign":"left","fontSize":"28rpx"}'>¥{{detail.bashiNewMoney}}
					</view>
				</view>


				<view class="name shop-title"
					:style='{"boxShadow":"0 0 6rpx rgba(0,0,0,.3)","margin":"0 0 20rpx 0","borderColor":"#fff","backgroundColor":"#fff","color":"rgba(84, 170, 111, 1)","borderRadius":"0","borderWidth":"0","fontSize":"36rpx","borderStyle":"solid"}'>
					往返地点：{{detail.bashiName}}
				</view>

				<view class="detail-list-item"
					:style='{"boxShadow":"0 0 4rpx 0 #006633 insta","margin":"0 60rpx 0 60rpx","borderColor":"rgba(218, 218, 218, 1)","backgroundColor":"#fff","borderRadius":"0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"68rpx"}'>
					<view class="lable"
						:style='{"width":"auto","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>
						类型：</view>
					<view class="text" :style='{"color":"rgba(84, 170, 111, 1)","textAlign":"center","fontSize":"24rpx"}'>
						{{detail.bashiValue}}</view>
				</view>
				<view class="detail-list-item"
					:style='{"boxShadow":"0 0 4rpx 0 #006633 insta","margin":"0 60rpx 0 60rpx","borderColor":"rgba(218, 218, 218, 1)","backgroundColor":"#fff","borderRadius":"0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"68rpx"}'>
					<view class="lable"
						:style='{"width":"auto","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>
						购买所得积分：</view>
					<view class="text" :style='{"color":"rgba(84, 170, 111, 1)","textAlign":"center","fontSize":"24rpx"}'>
						{{detail.bashiNewJifen}}</view>
				</view>
				<view class="detail-list-item"
					:style='{"boxShadow":"0 0 4rpx 0 #006633 insta","margin":"0 60rpx 0 60rpx","borderColor":"rgba(218, 218, 218, 1)","backgroundColor":"#fff","borderRadius":"0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"68rpx"}'>
					<view class="lable"
						:style='{"width":"auto","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>
						发车时间：</view>
					<view class="text" :style='{"color":"rgba(84, 170, 111, 1)","textAlign":"center","fontSize":"24rpx"}'>
						{{detail.bashiTime}}</view>
				</view>
				<view class="detail-list-item"
					:style='{"boxShadow":"0 0 4rpx 0 #006633 insta","margin":"0 60rpx 0 60rpx","borderColor":"rgba(218, 218, 218, 1)","backgroundColor":"#fff","borderRadius":"0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"68rpx"}'>
					<view class="lable"
						:style='{"width":"auto","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>
						座位总数：</view>
					<view class="text" :style='{"color":"rgba(84, 170, 111, 1)","textAlign":"center","fontSize":"24rpx"}'>
						{{detail.zuoweiNumber}}</view>
				</view>
			</view>
			
			
			<view class="time-content"
				:style='{"boxShadow":"0 0 2rpx rgba(0,0,0,.8)","margin":"0","borderColor":"#fff","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0","borderStyle":"solid","height":"auto"}'>
				<view class="header" :style='{"color":"#333","fontSize":"28rpx"}'>
					简介
				</view>
				<view class="content" style="line-height: 50upx;letter-spacing: 5upx;"
					:style='{"color":"#333","fontSize":"28rpx"}'>
					<rich-text :nodes="detail.bashiContent"></rich-text>
				</view>
			</view>
			
			<view class="seat-list">
				<view v-for="(item,index) in zuoweiNumberList " v-bind:key="index" class="seat-item">
					<image @click="$utils.msg('该座位已被预定')" v-if="item.select" class="seat-icon" src="../../static/restaurant-detail/select.png"></image>
					<image @click="selectSeat(item)" v-else-if="!item.active" class="seat-icon" src="../../static/restaurant-detail/unselect.png"></image>
					<image @click="unselectSeat(item)" v-else class="seat-icon" src="../../static/restaurant-detail/now.png"></image>
					<text>{{item.name}}</text>
				</view>
			</view>

			<view class="time-content"
				:style='{"boxShadow":"0 0 6rpx rgba(0,0,0,.3)","margin":"0 0 0px 0","borderColor":"red","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0","borderStyle":"solid"}'>
				<view class="comoment-header"
					:style='{"borderColor":"rgba(0,0,0,.2)","backgroundColor":"#fff","borderRadius":"0","borderStyle":"solid","borderWidth":"0 0 2rpx 0"}'>
					<view :style='{"color":"rgba(84, 170, 111, 1)","fontSize":"32rpx"}'>
						评论
					</view>
					<view
						:style='{"borderColor":"red","backgroundColor":"#fff","color":"rgba(84, 170, 111, 1)","borderRadius":"0","borderWidth":"0","width":"auto","fontSize":"24rpx","borderStyle":"solid","isBtn":true,"height":"60rpx"}'
						@click="onCommentTap" class="btn-comment-content" style="display: flex;align-items: center;">
						<view class="cuIcon-add"></view>添加评论
					</view>
				</view>

				<view class="cu-list comment" style="margin-top: 20upx;">
					<view
						:style='{"borderColor":"rgba(0,0,0,.2)","backgroundColor":"#fff","borderRadius":"0","borderStyle":"dashed","borderWidth":"0 0 2rpx 0"}'
						v-for="(item,index) in commentList" v-bind:key="index" class="cu-item comment-item">
						<view class="content">
							<view :style='{"color":"rgba(84, 170, 111, 1)","textAlign":"left","fontSize":"28rpx"}'
								class="text-grey">{{item.yonghuName}}
								<view style="float: right"
									class="text-gray text-df">{{item.insertTime}}</view>	
							</view>
							<view :style='{"color":"#666","textAlign":"left","fontSize":"24rpx"}'
								class="text-gray text-content text-df content">
								{{item.bashiLiuyanText}}
							</view>
							<view v-if="item.replyText" :style='{"color":"#666","textAlign":"left","fontSize":"24rpx"}'
								class="text-gray text-content text-df content">
								回复:{{item.replyText}}
							</view>
						</view>
					</view>

				</view>

			</view>

			<view class="bottom-content cu-bar bg-white tabbar border shop">
				<view style="text-align: left;display: flex;">
				</view>
				<view style="text-align: right;">
					<button @tap="onSubmitTap" style="margin-right: 10upx;min-width: 180upx;"
						class="btn-submit cu-btn round bg-red"
						:style="{width:'auto',borderRadius:'40rpx',height:'80rpx',fontSize:'28rpx',color:'rgba(239, 239, 239, 1)',backgroundColor:btnColor[2],borderColor:btnColor[2]}">预定</button>
				
				</view>
			</view>
			
		</view>

	</mescroll-uni>
</template>

<script>
	export default {
		data() {
			return {
				autoplaySwiper: false ? true : false,
				intervalSwiper: false ? $template2.front.base.swiper.autoplay.delay : 5000,
				btnColor: ['#409eff', '#67c23a', '#909399', '#e6a23c', '#f56c6c', '#356c6c', '#351c6c', '#f093a9',
					'#a7c23a', '#104eff', '#10441f', '#a21233', '#503319'
				],
				id: '',
				detail: {},
				swiperList: [],
				commentList: [],
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				user: {},
				bashiCollectionFlag: 0,
				//订座
				zuoweiNumberList: [],
				thumbsupFlag: 0,
				crazilyFlag: 0,
				count: 0,
				timer: null
			}
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad(options) {
			this.id = options.id;
		},
		async onShow(options) {
			// 渲染数据
			this.init();
			
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			this.btnColor = this.btnColor.sort(() => {
				return (0.5 - Math.random());
			});
			
			let cleanType = uni.getStorageSync('bashiLiuyanState')
			if (cleanType) {
				uni.removeStorageSync('bashiLiuyanState')
				this.mescroll.num = 1
				this.upCallback(this.mescroll)
			}
			
		},
		destroyed: function() {
			//window.clearInterval(this.inter);
		},
		methods: {
			// 获取详情
			async init() {
				let res = await this.$api.info('bashi', this.id);
				this.detail = res.data;
				// 轮播图片
				this.swiperList = this.detail.bashiPhoto ? this.detail.bashiPhoto.split(",") : [];
				for (let i = 1; i <= this.detail.zuoweiNumber; i++) {
					this.zuoweiNumberList.push({
						name: `${i}号`,
						select: false,
						active: false
					});
				}
				if(this.detail.selected != null && this.detail.selected !='' && this.detail.selected.split(',').length>0){
					for (let i = 0; i < this.detail.selected.split(',').length; i++) {
						this.zuoweiNumberList[this.detail.selected.split(',')[i] - 1].select = true;
					}
				}
			},
			// 选座座位
			selectSeat(item) {
				item.active = true;
			},
			// 取消选择座位
			unselectSeat(item) {
				item.active = false;
			},
			async onSubmitTap() {
				let _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认预定',
					success: async function(res) {
						if (res.confirm) {
							let activeZuoweiList = [];
							for (let i = 0; i < _this.zuoweiNumberList.length; i++) {
								if (_this.zuoweiNumberList[i].active) {
									activeZuoweiList.push(_this.zuoweiNumberList[i].name.replace('号', ''));
								}
							}
							if (activeZuoweiList.length == 0) {
								_this.$utils.msg('请选择要预定的座位');
								return false;
							}
							let data = {
								orderid: _this.$utils.genTradeNo(),
								bashiId: _this.detail.id,
								selected: activeZuoweiList.toString(),
							}
							uni.setStorageSync('orderGoods', data);
							_this.$utils.jump('/pages/shop-order-confirm/shop-order-confirm');
						}
					}
				});
			},
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},

			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				mescroll.resetUpScroll()
			},

			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let res = await this.$api.list('bashiLiuyan', {
					page: mescroll.num,
					limit: mescroll.size,
					bashiId: this.id
				});
				// 如果是第一页数据置空
				if (mescroll.num == 1) this.commentList = [];
				this.commentList = this.commentList.concat(res.data.list);
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);

			},

			onChatTap() {
				this.$utils.jump('../chat/chat')
			},
			// 下载
			download(url) {
				let _this = this;
				url = _this.$base.url + url;
				uni.downloadFile({
					url: url,
					success: (res) => {
						if (res.statusCode === 200) {
							_this.$utils.msg('下载成功');
							window.open(url);
						}
					}
				});
			},
			// 
			onCartTabTap() {
				this.$utils.tab('../shop-cart/shop-cart')
			},
			// 添加评论
			async onCommentTap() {
				this.$utils.jump(`../bashiLiuyan/add-or-update?bashiId=${this.id}`)
			},
			
		}
	}
</script>

<style lang="scss">
	.container {
		// padding-bottom: 80upx;
	}

	.container:after {
		position: fixed;
		top: 0;
		right: 0;
		left: 0;
		bottom: 0;
		content: '';
		background-attachment: fixed;
		background-size: cover;
		background-position: center;
	}

	.swiper {
		width: 100%;
		height: 450upx;

		image {
			width: 100%;
		}
	}

	.detail-content {
		font-size: 28upx;
		color: #888888;
		// padding: 10upx 20upx;
		background: #FFFFFF;
		line-height: 45upx;

		.name {
			font-size: 38rpx;
			font-weight: blod;
			// margin-bottom: 20upx;
			padding-top: 20upx;
		}

		.price {
			font-size: 33rpx;
			color: red;
			// margin-top: 20upx;
		}
	}

	.time-content {
		background: #FFFFFF;
		padding: 30upx;
		font-size: 30upx;
		border-radius: 20upx;
		margin-top: 20upx;

		.header {
			font-size: 33upx;
			margin-bottom: 20upx;
		}
	}

	.bottom-content {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
	}

	.comoment-header {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding-bottom: 12upx;

		.btn-comment-content {
			color: #DD514C;
			font-weight: bold;
			padding: 0 12upx;
		}
	}

	.comment-item {
		margin-bottom: 10upx;
		border-bottom: 1px solid #EEEEEE;
		padding-bottom: 20upx;

		.content {
			margin-left: 20upx;
			margin-top: 20upx;
		}
	}

	.icon {
		width: 50upx;
		height: 50upx;
	}

	.popup-content {
		.popup-form {
			background: #FFFFFF;
			margin-top: 20upx;
		}

		.btn-content {
			text-align: right;
			background: #FFFFFF;

			button {
				margin-left: 20upx;
				margin-right: 20upx;
				margin-bottom: 20upx;
			}
		}
	}

	.detail-list-item {
		padding: 0 24upx;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		height: 68upx;
		border-bottom: 2upx solid #efefef;
		background-color: #fff;

		.lable {
			font-size: 28upx;
			color: #000;
		}

		.text {
			flex: 1;
			font-size: 24upx;
			color: #FF00FF;
			text-align: left;
		}

		&.favor {
			.el-icon-star-on {
				color: inherit;
				font-size: inherit;
			}
		}
	}

	.detail-content .shop-title {
		padding: 20upx 24upx;
		box-sizing: border-box;
		line-height: 1.4;
	}

	.priceFavor {
		height: 96upx !important;
	}

	.priceFavor .text {
		font-size: 64upx !important;
		color: red !important;
	}

	.priceFavor .icon {
		font-size: 56upx !important;
		text-align: right !important;
		color: red !important;
	}
	
	//订座样式
	.seat-list {
		display: flex;
		align-items: center;
		flex-wrap: wrap;
		background: #FFFFFF;
		margin: 20upx;
		border-radius: 20upx;
		padding: 20upx;
		font-size: 30upx;
	
		.seat-item {
			width: 33.33%;
			display: flex;
			align-items: center;
			flex-direction: column;
			margin-bottom: 20upx;
	
			.seat-icon {
				width: 50upx;
				height: 50upx;
				margin-bottom: 10upx;
			}
		}
	}
</style>
