<template>
	<view class="content">
		<form class="app-update-pv">
			<view
				:style='{"boxShadow":"0 0 0px rgba(0,0,0,.3)","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(227, 228, 227, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none","height":"308rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
					class="title">评论内容</view>
				<textarea
					:style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"280rpx"}'
					v-model="ruleForm.shouhouCommentbackText" placeholder="评论内容"></textarea>
			</view>
			<view class="btn">
				<button
					:style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"rgba(84, 170, 111, 1)","borderColor":"#409EFF","borderRadius":"8rpx","color":"rgba(255, 255, 255, 1)","borderWidth":"0","width":"70%","fontSize":"32rpx","borderStyle":"solid","height":"80rpx"}'
					@tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>


	</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";

	export default {
		data() {
			return {
				cross: '',
				ruleForm: {
					xinxifabuId: '',
					userid: '',
					nickname: '',
					shouhouCommentbackText: '',
					reply: '',
				},
				// 登陆用户信息
				user: {},
				ro: {
					xinxifabuId: false,
					yonghuId: false,
					nickname: false,
					shouhouCommentbackText: false,
					reply: false,
				},
			}
		},
		components: {
			wPicker
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad(options) {
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;

			// 如果有登陆，获取登陆后保存的yonghuId
			this.ruleForm.yonghuId = uni.getStorageSync("userid")
			if (options.xinxifabuId) {
				// 如果上一级页面传递了xinxifabuId，获取改xinxifabuId数据信息
				this.ruleForm.xinxifabuId = options.xinxifabuId;
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`xinxifabuLiuyan`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if (options.cross) {
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj) {
					if (o == 'xinxifabuId') {
						this.ruleForm.xinxifabuId = obj[o];
						this.ro.xinxifabuId = true;
						continue;
					}
					if (o == 'yonghuId') {
						this.ruleForm.yonghuId = obj[o];
						this.ro.yonghuId = true;
						continue;
					}
					if (o == 'nickname') {
						this.ruleForm.nickname = obj[o];
						this.ro.nickname = true;
						continue;
					}
					if (o == 'shouhouCommentbackText') {
						this.ruleForm.shouhouCommentbackText = obj[o];
						this.ro.shouhouCommentbackText = true;
						continue;
					}
					if (o == 'reply') {
						this.ruleForm.reply = obj[o];
						this.ro.reply = true;
						continue;
					}
				}
			}
			this.styleChange()
		},
		methods: {
			styleChange() {
				this.$nextTick(() => {
					// document.querySelectorAll('.app-update-pv .cu-form-group .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.addUpdateForm.input.shouhouCommentbackText.backgroundColor
					// })
				})
			},

			// 多级联动参数
			getUUID() {
				return new Date().getTime();
			},
			async onSubmitTap() {
				//跨表计算判断
				if ((!this.ruleForm.shouhouCommentbackText)) {
					this.$utils.msg(`评论内容不能为空`);
					return
				}
				await this.$api.add(`xinxifabuLiuyan`, this.ruleForm);
				uni.setStorageSync('xinxifabuLiuyanState', true);
				this.$utils.msgBack('提交成功');
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			toggleTab(str) {
				this.$refs[str].show();
			}
		}
	}
</script>

<style lang="scss" scoped>
	.container {
		padding: 20upx;
	}

	.content:after {
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

	textarea {
		border: 1upx solid #EEEEEE;
		border-radius: 20upx;
		padding: 20upx;
	}

	.title {
		width: 180upx;
	}

	.avator {
		width: 150upx;
		height: 60upx;
	}

	.right-input {
		flex: 1;
		text-align: left;
		padding: 0 24upx;
	}

	.cu-form-group.active {
		justify-content: space-between;
	}

	.btn {
		display: flex;
		align-items: center;
		justify-content: center;
		flex-wrap: wrap;
		padding: 20upx 0;
	}

	.cu-form-group {
		padding: 0 24upx;
		background-color: transparent;
		min-height: inherit;
	}

	.cu-form-group+.cu-form-group {
		border: 0;
	}

	.cu-form-group uni-input {
		padding: 0 30upx;
	}

	.uni-input {
		padding: 0 30upx;
	}

	.cu-form-group uni-textarea {
		padding: 30upx;
		margin: 0;
	}

	.cu-form-group uni-picker::after {
		line-height: 68rpx;
	}

	.select .uni-input {
		line-height: 68rpx;
	}

	.input .right-input {
		line-height: 68rpx;
	}
</style>
