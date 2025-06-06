<template>
	<view class="content">
		<view
			:style='{"boxShadow":"0 0 2rpx #006633","backgroundColor":"rgba(247, 247, 247, 1)","borderColor":"rgba(84, 170, 111, 1)","margin":"0 0 0px 0","borderWidth":"0 0 0 0px","borderStyle":"solid","height":"88rpx"}'
			v-if="tableName=='yonghu'" class="cu-form-group">
			<view class="title"
				:style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>用户名
			</view>
			<input style="padding: 0 30upx"
				:style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"68rpx"}'
				v-model="ruleForm.username" placeholder="用户名"></input>
		</view>
		<view
			:style='{"boxShadow":"0 0 2rpx #006633","backgroundColor":"rgba(247, 247, 247, 1)","borderColor":"rgba(84, 170, 111, 1)","margin":"0 0 0px 0","borderWidth":"0 0 0 0px","borderStyle":"solid","height":"88rpx"}'
			v-if="tableName=='yonghu'" class="cu-form-group">
			<view class="title"
				:style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>姓名
			</view>
			<input style="padding: 0 30upx"
				:style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"68rpx"}'
				v-model="ruleForm.yonghuName" placeholder="姓名"></input>
		</view>
		<view v-if="tableName=='yonghu'"
			:style='{"boxShadow":"0 0 0px rgba(0,0,0,.3)","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(51, 153, 153, 1)","margin":"0 0 10rpx 0","borderWidth":"2rpx","borderStyle":"solid","height":"108rpx"}'
			class="cu-form-group select">
			<view :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'
				class="title">性别</view>
			<picker @change="yonghusexTypesChange" :value="yonghusexTypesIndex" :range="yonghusexTypesOptions" range-key="indexName">
				<view style="padding: 0 30upx" :style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"68rpx"}'
					class="uni-input picker-select-input">{{ruleForm.sexValue?ruleForm.sexValue:"请选择性别"}}</view>
			</picker>
		</view>
		<view
			:style='{"boxShadow":"0 0 2rpx #006633","backgroundColor":"rgba(247, 247, 247, 1)","borderColor":"rgba(84, 170, 111, 1)","margin":"0 0 0px 0","borderWidth":"0 0 0 0px","borderStyle":"solid","height":"88rpx"}'
			v-if="tableName=='yonghu'" @tap="yonghuyonghuPhotoTap" class="cu-form-group">
			<view class="title"
				:style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>头像
			</view>
			<image
				:style='{"width":"76rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"0","textAlign":"left","height":"76rpx"}'
				v-if="ruleForm.yonghuPhoto" class="avator" :src="ruleForm.yonghuPhoto" mode=""></image>
			<image
				:style='{"width":"76rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"0","textAlign":"left","height":"76rpx"}'
				v-else class="avator" src="../../static/center/face.jpeg" mode=""></image>
		</view>
		<view
			:style='{"boxShadow":"0 0 2rpx #006633","backgroundColor":"rgba(247, 247, 247, 1)","borderColor":"rgba(84, 170, 111, 1)","margin":"0 0 0px 0","borderWidth":"0 0 0 0px","borderStyle":"solid","height":"88rpx"}'
			v-if="tableName=='yonghu'" class="cu-form-group">
			<view class="title"
				:style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>联系电话
			</view>
			<input style="padding: 0 30upx"
				:style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"68rpx"}'
				v-model="ruleForm.yonghuPhone" placeholder="联系电话"></input>
		</view>
		<view
			:style='{"boxShadow":"0 0 2rpx #006633","backgroundColor":"rgba(247, 247, 247, 1)","borderColor":"rgba(84, 170, 111, 1)","margin":"0 0 0px 0","borderWidth":"0 0 0 0px","borderStyle":"solid","height":"88rpx"}'
			v-if="tableName=='yonghu'" class="cu-form-group">
			<view class="title"
				:style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(84, 170, 111, 1)","textAlign":"left"}'>身份证
			</view>
			<input style="padding: 0 30upx"
				:style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(84, 170, 111, 1)","borderRadius":"0px","color":"rgba(84, 170, 111, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"68rpx"}'
				v-model="ruleForm.yonghuIdNumber" placeholder="身份证"></input>
		</view>

		<view class="btn">
			<view class="box" :style="{width: 'auto'}">
				<button @tap="update()" class="cu-btn lg"
					:style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"rgba(153, 0, 51, 1)","borderColor":"rgba(153, 0, 51, 1)","borderRadius":"8rpx","color":"#fff","borderWidth":"0","width":"auto","fontSize":"32rpx","borderStyle":"solid","height":"80rpx"}'>保存</button>
			</view>
		
			<view class="box" :style="{width: 'auto'}">
				<button @tap="logout()" class="cu-btn lg"
					:style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"rgba(84, 170, 111, 1)","borderColor":"#E6A23C","borderRadius":"8rpx","color":"#fff","borderWidth":"0px","width":"auto","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'>退出登录</button>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				ruleForm: {},
				tableName: "",
				yonghusexTypesOptions: [],
				yonghusexTypesIndex: 0,
			}
		},
		async onLoad() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			this.ruleForm = res.data;
			this.tableName = table;
			// 自定义下拉框值
			if (this.tableName == 'yonghu') {
				let params = {
					page: 1,
					limit: 100,
					dicCode: 'sex_types',
				}
				let res = await this.$api.page(`dictionary`, params);
				
				this.yonghusexTypesOptions = res.data.list
			}
			this.styleChange()
		},
		methods: {
			// 下拉变化
			yonghusexTypesChange(e) {
				this.yonghusexTypesIndex = e.target.value
				this.ruleForm.sexValue = this.yonghusexTypesOptions[this.yonghusexTypesIndex].indexName
				this.ruleForm.sexTypes = this.yonghusexTypesOptions[this.yonghusexTypesIndex].codeIndex
			},
			styleChange() {
				this.$nextTick(() => {
					// document.querySelectorAll('.cu-form-group .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.userInfoForm.list.input.backgroundColor
					// })
				})
			},
			// 获取uuid
			getUUID() {
				return new Date().getTime();
			},
			logout() {
				uni.setStorageSync('token', '');
				this.$utils.jump('../login/login');
			},
			// 注册
			async update() {
					if ((!this.ruleForm.username) && `yonghu` == this.tableName) {
						this.$utils.msg(`用户名不能为空`);
						return
					}
					if ((!this.ruleForm.yonghuName) && `yonghu` == this.tableName) {
						this.$utils.msg(`姓名不能为空`);
						return
					}
					if (`yonghu` == this.tableName && this.ruleForm.yonghuPhone && (!this.$validate.isMobile(this
							.ruleForm.yonghuPhone))) {
						this.$utils.msg(`联系电话应输入手机格式`);
						return
					}
					if (`yonghu` == this.tableName && this.ruleForm.yonghuIdNumber && (!this.$validate.checkIdCard(this
							.ruleForm.yonghuIdNumber))) {
						this.$utils.msg(`联系电话应输入手机格式`);
						return
					}
					
					let table = uni.getStorageSync("nowTable");
					await this.$api.update(table, this.ruleForm);
					this.$utils.msgBack('修改成功');;
				}

				,
			yonghuyonghuPhotoTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.yonghuPhoto = _this.$base.url + 'upload/' + res.file;
					_this.$forceUpdate();
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
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

	.avator {
		width: 110upx;
		height: 110upx;
		border-radius: 50%;
		margin-left: 30upx;
	}

	.cu-form-group.active {
		justify-content: space-between;
	}

	.cu-btn {
		width: 100%;
	}

	.right-input {
		flex: 1;
		text-align: left;
		line-height: 88rpx;
	}

	.btn {
		display: flex;
		align-items: center;
		justify-content: center;
		flex-wrap: wrap;
		padding: 20upx 0;
	}

	.box {
		width: auto;
		padding: 0 10upx;
		box-sizing: border-box;
		margin-bottom: 20upx;
	}

	.cu-btn {
		width: 100% !important;
	}

	.picker-select-input {
		line-height: 88rpx;
	}
</style>
