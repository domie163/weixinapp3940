<template>
	<view class="content">
		<form class="app-update-pv">

			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"88rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
					class="title">信息标题</view>
				<input
					:style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"28rpx","borderStyle":"solid","height":"68rpx"}'
					:disabled="ro.xinxifabuName" v-model="ruleForm.xinxifabuName" placeholder="信息标题"></input>
			</view>
			<view
				:style='{"boxShadow":"0 0 0px rgba(0,0,0,.3)","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(218, 220, 219, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"108rpx"}'
				class="cu-form-group" @tap="xinxifabuPhotoTap">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
					class="title">图片</view>
				<view class="right-input" style="padding:0;textAlign:left">
					<image
						:style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
						class="avator" v-if="ruleForm.xinxifabuPhoto" :src="ruleForm.xinxifabuPhoto" mode="aspectFill">
					</image>
					<image
						:style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
						class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
				</view>
			</view>
			<view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"88rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
					class="title">类型</view>
				<picker @change="xinxifabuTypesChange" :value="xinxifabuTypesIndex" :range="xinxifabuTypesOptions" range-key="indexName">
					<view
						:style='{"borderColor":"rgba(84, 170, 111, 1)","backgroundColor":"rgba(248, 248, 248, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"80rpx"}'
						class="uni-input">{{ruleForm.xinxifabuTypes?ruleForm.xinxifabuValue:"请选择类型"}}</view>
				</picker>
			</view>
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"88rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
					class="title">地点</view>
				<input
					:style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"28rpx","borderStyle":"solid","height":"68rpx"}'
					:disabled="ro.xinxifabuAddress" v-model="ruleForm.xinxifabuAddress" placeholder="地点"></input>
			</view>
			<view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"88rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
					class="title">宠物类型</view>
				<picker @change="chongwuTypesChange" :value="chongwuTypesIndex" :range="chongwuTypesOptions" range-key="indexName">
					<view
						:style='{"borderColor":"rgba(84, 170, 111, 1)","backgroundColor":"rgba(248, 248, 248, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"80rpx"}'
						class="uni-input">{{ruleForm.chongwuTypes?ruleForm.chongwuValue:"请选择宠物类型"}}</view>
				</picker>
			</view>
			
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"88rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
					class="title">时间</view>
				<input
					:style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"28rpx","borderStyle":"solid","height":"68rpx"}'
					v-model="ruleForm.xinxifabuTime" placeholder="时间"
					@tap="toggleTab('xinxifabuTime')"></input>
			</view>
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"210rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
					class="title">详情</view>
					<textarea :style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"28rpx","borderStyle":"solid","height":"200rpx"}'
					 :disabled="ro.xinxifabuContent" v-model="ruleForm.xinxifabuContent" placeholder="详情"/>
			</view>
			
			<view class="btn">
				<button
					:style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"rgba(84, 170, 111, 1)","borderColor":"#409EFF","borderRadius":"8rpx","color":"rgba(255, 255, 255, 1)","borderWidth":"0","width":"70%","fontSize":"32rpx","borderStyle":"solid","height":"80rpx"}'
					@tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>

		<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="xinxifabuTimeConfirm"
			ref="xinxifabuTime" themeColor="#333333"></w-picker>

	</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";

	export default {
		data() {
			return {
				cross: '',
				ruleForm: {
				    xinxifabuName: '',
				    xinxifabuTypes: '',
				    yonghuId: '',
				    xinxifabuPhoto: '',
				    xinxifabuAddress: '',
				    chongwuTypes: '',
				    xinxifabuTime: '',
				    xinxiTypes: 1,
				    xinxifabuContent: '',
				},
				// 登陆用户信息
				user: {},
				ro:{
				    xinxifabuName: false,
				    xinxifabuTypes: false,
				    yonghuId: false,
				    xinxifabuPhoto: false,
				    xinxifabuAddress: false,
				    chongwuTypes: false,
				    xinxifabuTime: false,
				    xinxiTypes: false,
				    xinxifabuContent: false,
				},
				xinxifabuTypesOptions : [],
				xinxifabuTypesIndex : 0,
				
				chongwuTypesOptions : [],
				chongwuTypesIndex : 0,
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
			
			let paramsA = {
				page: 1,
				limit: 100,
				dicCode: 'xinxifabu_types',
			}
			let resA = await this.$api.page(`dictionary`, paramsA);
			this.xinxifabuTypesOptions = resA.data.list
			
			let paramsB = {
				page: 1,
				limit: 100,
				dicCode: 'chongwu_types',
			}
			let resB = await this.$api.page(`dictionary`, paramsB);
			this.chongwuTypesOptions = resB.data.list


			
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				let res = await this.$api.info(`xinxifabu`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if (options.cross) {
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj) {
					if (o == 'xinxifabuName') {
						this.ruleForm.xinxifabuName = obj[o];
						this.ro.xinxifabuName = true;
						continue;
					}
					if (o == 'shipin') {
						this.ruleForm.shipin = obj[o];
						this.ro.shipin = true;
						continue;
					}
					if (o == 'xinxifabuPhoto') {
						this.ruleForm.xinxifabuPhoto = obj[o];
						this.ro.xinxifabuPhoto = true;
						continue;
					}
					if (o == 'xinxifabuTime') {
						this.ruleForm.xinxifabuTime = obj[o];
						this.ro.xinxifabuTime = true;
						continue;
					}
					if (o == 'yonghuzhanghao') {
						this.ruleForm.yonghuzhanghao = obj[o];
						this.ro.yonghuzhanghao = true;
						continue;
					}
					if (o == 'yonghuxingming') {
						this.ruleForm.yonghuxingming = obj[o];
						this.ro.yonghuxingming = true;
						continue;
					}
					if (o == 'userid') {
						this.ruleForm.userid = obj[o];
						this.ro.userid = true;
						continue;
					}
				}
			}
			this.styleChange()
		},
		methods: {
			// 下拉变化
			xinxifabuTypesChange(e) {
				this.xinxifabuTypesIndex = e.target.value
				this.ruleForm.xinxifabuValue = this.xinxifabuTypesOptions[this.xinxifabuTypesIndex].indexName
				this.ruleForm.xinxifabuTypes = this.xinxifabuTypesOptions[this.xinxifabuTypesIndex].codeIndex
			},
			chongwuTypesChange(e) {
				this.chongwuTypesIndex = e.target.value
				this.ruleForm.chongwuValue = this.chongwuTypesOptions[this.chongwuTypesIndex].indexName
				this.ruleForm.chongwuTypes = this.chongwuTypesOptions[this.chongwuTypesIndex].codeIndex
			},
			
			styleChange() {
				this.$nextTick(() => {
					// document.querySelectorAll('.app-update-pv .cu-form-group .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.addUpdateForm.input.content.backgroundColor
					// })
				})
			},

			// 日长控件选择日期时间
			xinxifabuTimeConfirm(val) {
				console.log(val)
				this.ruleForm.xinxifabuTime = val.result;
				this.$forceUpdate();
			},


			xinxifabuPhotoTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.xinxifabuPhoto = 'http://localhost:8080/wangyuebashidingpiao/upload/' + res.file;
					_this.$forceUpdate();
					_this.$nextTick(() => {
						_this.styleChange()
					})
				});
			},

			getUUID() {
				return new Date().getTime();
			},
			async onSubmitTap() {
				if ((!this.ruleForm.xinxifabuName)) {
					this.$utils.msg(`信息标题不能为空`);
					return
				}
				if ((!this.ruleForm.xinxifabuTypes)) {
					this.$utils.msg(`类型不能为空`);
					return
				}
				if ((!this.ruleForm.xinxifabuPhoto)) {
					this.$utils.msg(`图片不能为空`);
					return
				}
				if ((!this.ruleForm.xinxifabuAddress)) {
					this.$utils.msg(`地址不能为空`);
					return
				}
				if ((!this.ruleForm.chongwuTypes)) {
					this.$utils.msg(`宠物类型不能为空`);
					return
				}
				if ((!this.ruleForm.xinxifabuTime)) {
					this.$utils.msg(`时间不能为空`);
					return
				}
				if ((!this.ruleForm.xinxifabuContent)) {
					this.$utils.msg(`详情不能为空`);
					return
				}
				if (this.ruleForm.id) {
					await this.$api.update(`xinxifabu`, this.ruleForm);
				} else {
					await this.$api.save(`xinxifabu`, this.ruleForm);
				}
				this.$utils.msgBack('提交成功');
			},
			optionsChange(e) {
				this.index = e.target.value
			},
			bindDateChange(e) {
				this.date = e.target.value
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
