<template >
    <div class="div">
        <el-container>
            <el-aside style="width: 400px; margin-top: 20px">
            <el-popover
                    placement="bottom"
                    width="400"
                    trigger="click">
                <b style="text-align: center;">使用说明</b>
                <div style="text-align: left; margin: 0">
                    <p>1、点击个人中心，可以在里面添加自己闲置的书籍</p>
                    <p>2、添加后可在图书馆中供其他同学查看</p>
                    <p>3、想要的书籍请自行联系同学，进行交易</p>
                    <p>4、请不要随意压低、抬高价格，同学之间互相帮助</p>
                    <p>5、这个网站只供校园内部使用，希望解决书籍资源浪费的问题</p>
                    <p>6、第一次登陆后就可以修改密码了</p>
                    <p>7、卖出去的书籍请同学们自行删除信息</p>
                </div>
                <el-link slot="reference" type="primary" class="specification" icon="el-icon-present">使用说明</el-link>
            </el-popover>
            <div class="block">
                <el-timeline>
                    <el-card class="card" shadow="always">
                        <h4>好好学习，好好生活</h4>
                        <p>加油</p>
                    </el-card>
                    <el-card class="foot" shadow="always">
                        <h4>此间有书</h4>
                        <p>实现书籍的循环利用</p>
                    </el-card>
                </el-timeline>
            </div>
            </el-aside>
            <el-main class="main">
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Index",
        components: {},
        data() {
            return {
                visible: false
            };
        }
    }
</script>

<style scoped>
    .specification{
        position: absolute;
        left: 2%;
        margin: 10px;
    }
    .card{
        margin-top:40px;
        margin-left:20px;
        margin-right: 20px;
        margin-bottom: 420px;
    }
    .foot{
        margin:20px;
    }
    .div{
        height: 100%;
        width: 100%;
        background-size: 100%;
    }
    .main{
        background:url("../../../public/img/background/2.jpg");
        background-size: 100% ;
        right: 0;
        height: 1000px;
    }
</style>
