const base = {
    get() {
        return {
            url : "http://localhost:8080/wangyuebashidingpiao/",
            name: "wangyuebashidingpiao",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/wangyuebashidingpiao/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "网约巴士订票平台"
        } 
    }
}
export default base
