<template>
	<view>
		<view class="cu-chat">
			<view v-for="(item,index) in list" v-bind:key="index">
				<view v-if="item.chatIssue" class="cu-item self">
					<text>{{item.issueTime}}</text>
					<view class="main">
						<view class="content bg-green shadow">
							<text>{{item.chatIssue}}</text>
						</view>
					</view>
					<view class="cu-avatar radius" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big107000.jpg);"></view>
					<!-- <view class="date">2018年3月23日 13:23</view> -->
				</view>
				<view v-if="item.chatReply" class="cu-item">
					<view class="cu-avatar radius" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big143004.jpg);"></view>
					<view class="main">
						<view class="content shadow">
							<text>{{item.chatReply}}</text>
						</view>
					</view>
					<text>{{item.replyTime}}</text>
					<!-- <view class="date "> 13:23</view> -->
				</view>
			</view>
		</view>

		<view class="cu-bar foot input">
			<input v-model="chatIssue" class="solid-bottom" :adjust-position="false" :focus="false" maxlength="300" cursor-spacing="10"></input>
			<button @tap="onSendTap" class="cu-btn bg-green shadow">发送</button>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 定时器
				inter: {},
				list: [],
				chatIssue: ''
			};
		},
		onLoad() {
			let _this = this;
			let inter = setInterval(function() {
				_this.init();
			}, 5000)
			this.inter = inter
		},
		onUnload() {
			if (this.inter) {
				// 清除定时器
				clearInterval(this.inter);
			}
		},
		methods: {
			addTimes(m){return m<10?'0'+m:m },
			async onSendTap() {
				let time = new Date();let y = time.getFullYear();let m = time.getMonth() + 1;let d = time.getDate();let h = time.getHours();let mm = time.getMinutes();let s = time.getSeconds();
				await this.$api.save('chat', {
					chatIssue: this.chatIssue,
					chatTypes: 1,
					zhuangtaiTypes: 1,
					issueTime:( y + '-' + this.addTimes(m) + '-' + this.addTimes(d) + ' ' + this.addTimes(h) + ':' + this.addTimes(mm) + ':' + this.addTimes(s))
				});
				this.chatIssue = '';
				this.init();
			},
			async init() {
				let res = await this.$api.page('chat', null);
				this.list = res.data.list;
			}
		}
	}
</script>

<style>
	page {
		padding-bottom: 100upx;
	}
</style>
