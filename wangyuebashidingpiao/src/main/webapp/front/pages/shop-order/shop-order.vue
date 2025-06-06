<template>
	<view>
		<scroll-view  scroll-x class="bg-white nav">
			<view class='flex text-center'>
				<view class="cu-item flex-sub text-df " @click="navselect(0)"
				 :class="[currentIndex == 0 ? 'text-red cur' : '']">
					全部
				</view>
				<view class="cu-item flex-sub text-df " @click="navselect(nav.codeIndex)" v-for="(nav,index) in navlist" :key="index"
				 :class="[currentIndex == nav.codeIndex ? 'text-red cur' : '']">
					{{nav.indexName}}
				</view>
			</view>
		</scroll-view>
		<mescroll-uni :top="88" @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
			<view v-for="(item,index) in list" :key="index">
				<view class="bg-white margin-sm padding-sm solids-bottom radius text-content">
					<view class="flex justify-between">
						<view class="text-bold" style="flex-shrink: 0;">
							<text class="text-blue">{{ item.bashiOrderTypes == 1?item.bashiOrderValue:'' }}</text>
							<text class="text-red">{{ item.bashiOrderTypes == 2?item.bashiOrderValue:'' }}</text>
							<text class="text-green">{{ item.bashiOrderTypes == 3?item.bashiOrderValue:'' }}</text>
							
						</view>
						<view><text class="icon-goods padding-right-sm"></text><text class="text-bold solids-bottom">{{ item.bashiOrderUuidNumber }}</text></view>
					</view>
					<view class="bg-white margin-xs padding-xs  radius text-content">
						<view class="flex padding-top-sm solids-top">
							<image :src="item.bashiPhoto" style="flex-shrink: 0;" mode="aspectFill" class=" cover radius solids"></image>
							<view class="text-content padding-left" style="flex-grow: 1;">
								<view>{{ item.bashiName }}</view>
								<view>{{ item.bashiValue }}</view>
								<view >{{ item.buyZuowei }} x ￥{{ item.bashiNewMoney }}</view>
							</view>
						</view>
						<view class="flex justify-end">合计{{ item.bashiOrderTruePrice }}元</view>
						<view class="flex justify-end"></view>
					</view>
					<view class="flex justify-end"></view>
					<view class="flex justify-end" style="font-size: 24upx;">
						<view v-if="item.bashiOrderTypes == 1">
							<view @tap="onCancelTap(item)" class="round cu-btn lines-grey mid  margin-right-sm">退款</view>
						</view>
						<view v-if="item.bashiOrderTypes == 1">
							<view @tap="onChatTap(item.id,3)" class="round cu-btn lines-grey mid  margin-right-sm">使用</view>
						</view>
						<view v-if="item.bashiOrderTypes == 3">
							<view @tap="onChatTapA" class="round cu-btn lines-grey mid  margin-right-sm">查看二维码</view>
						</view>
					</view>
				</view>
			</view>
			
			<uni-popup class="popup-content" ref="popup" type="center">
				<form class="popup-form">
					<view class="uni-flex uni-column" style="background-color: #FFFFFF;border-radius: 10rpx;width: 500rpx;">
						<view class="flex-item flex-item-V">
							<image style="margin: 20rpx 25rpx 20rpx 25rpx; width: 450rpx;"
								src='http://localhost:8080/wangyuebashidingpiao/img/ewm.jpg' mode="aspectFill"></image>
						</view>
						<view class="flex-item flex-item-V" style="text-align: right;background: #FFFFFF;">
							<button @tap="onCloseWinTap" style="margin: 0 37% 10rpx 50rpx;" class="cu-btn bg-cyan">关闭</button>
						</view>
					</view>
				</form>
			</uni-popup>
			
		
			<!-- http://localhost:8080/wangyuebashidingpiao/img/ewm.jpg -->
		</mescroll-uni>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				navlist: [
				],
				currentIndex: 0,
				pages: 1,
				list: [],
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					page: {
						// num: 0, // 当前页码,默认0,回调之前会加1,即callback(page)会从1开始
						// size: 5 // 每页数据的数量
					},
					noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				bashiLiuyanText: '',
				user: ''
			}
		},
		async onShow() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table)
			this.user = res.data
			this.hasNext = true
			
			let params = {
				page: 1,
				limit: 100,
				dicCode: 'order_types',
			}
			let res1 = await this.$api.page(`dictionary`, params);
			
			this.navlist = res1.data.list
			
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		methods: {
			onCloseWinTap() {
				this.$refs.popup.close()
			},
			onChatTapA () {
				this.$refs.popup.open()
			},
			navselect(nav) {
				this.currentIndex = nav
				this.bashiOrderTypes = nav
				this.hasNext = true
				// 重新加载数据
				if (this.mescroll) this.mescroll.resetUpScroll()
			},
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let params = {
					page: mescroll.num,
					limit: mescroll.size,
				}
				if (this.bashiOrderTypes) {
					params['bashiOrderTypes'] = this.bashiOrderTypes
				}
				let res = await this.$api.page('bashiOrder', params);
				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			async onCancelTap(order) {
				let _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认退款',
					success: async function(res) {
						var value = {
								key:'id',
								val:order.id,
							}
						debugger
						/* await _this.$api.aaaaa("bashiOrder", "refund",value); */
						await _this.$api.requestCondition("bashiOrder", "refund",value);
						_this.$utils.msgBack('退款成功');
						if (_this.mescroll) _this.mescroll.resetUpScroll()
					}
				});
			},
		
			async onChatTap(orderId,orderTypes){
				let _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认使用',
					success: async function(res) {
						var value = {
								id:orderId,
								bashiOrderTypes:orderTypes,
							}
						var returnVal = await _this.$api.update("bashiOrder",value);
						_this.$utils.msgBack('订单已完成');
						if (_this.mescroll) _this.mescroll.resetUpScroll()
					}
				});
			}
			
		}
	}
</script>

<style>
	.cover {
		width: 150upx;
		height: 150upx;
	}
</style>
