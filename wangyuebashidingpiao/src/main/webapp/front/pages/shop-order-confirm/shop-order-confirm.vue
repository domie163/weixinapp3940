<template>
	<view class="content">
		<form>
			<view class="cu-form-group">
				<view class="title">购买清单</view>
			</view>
			<view class="cu-form-group">
				<image class="avator" :src="bashiInfo.bashiPhoto"></image>
				<view class="title" style="width: 75%;">
					<view style="margin-top: -50rpx;">{{bashiInfo.bashiName}}</view>
					<view >
						{{selected}}号
						<text style="margin-left: 30upx;color: red;">￥{{bashiInfo.bashiNewMoney}}</text>
					</view>
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">总价</view>
				<view>
					<text>￥{{maxNewMnuey}}</text>
				</view>
			</view>
		</form>
		<view class="padding" style="text-align: center;">
			<button  @tap="onSubmitTap()" class="bg-red lg">确认支付</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {},
				orderGoods: {},
				bashiInfo: {},
				selected: "",
				maxNewMnuey: 0,
			}
		},
		async onLoad(options) {
			let _this = this
			// 获取订单信息，座位信息
			_this.orderGoods = uni.getStorageSync('orderGoods');
			let aaa = await _this.$api.info("bashi",_this.orderGoods.bashiId)
			_this.bashiInfo = aaa.data
			_this.selected = _this.orderGoods.selected
			_this.maxNewMnuey = ((_this.orderGoods.selected.split(",")).length * _this.bashiInfo.bashiNewMoney)
			uni.removeStorageSync('orderGoods')
		},
		async onShow() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table)
			this.user = res.data
			
		},
		methods: {
			async onSubmitTap() {
				let _this = this;
				let order = {
					bashiOrderUuidNumber:_this.$utils.genTradeNo(),
					yonghuId: this.user.id,
					bashiId: this.bashiInfo.id,
					buyZuowei: this.selected
				}
				 await _this.$api.add('bashiOrder', order);
				 _this.$utils.msg('支付成功');
				 _this.$utils.tab('/pages/shop-order/shop-order');
			},
		}
	}
</script>

<style lang="scss">
	.avator {
		width: 150upx;
		height: 150upx;
		margin: 20upx 0;
	}
</style>
