# RabbitBot_RE 兔叽
-----
RabbitBot_RE前身为 [RabbitBot](https://github.com/MikuNyanya/RabbitBot) 很可惜，它没有挺过来(        
但有幸遇到 [mirai](https://github.com/mamoe/mirai) ，兔叽还有复活的机会(°∀°)ﾉ

    请注意，该项目不是群聊机器人框架，是建立在mirai框架上的一个完整的群聊机器人
    很多功能都是为自家群量身定制的，项目中部分功能需要去申请自己的token，比如 Saucenao搜图 微博消息获取等等
    但非常欢迎学习功能实现代码，也非常欢迎提出更优的实现方法

基于 `java` 语言和 `mirai` 框架的群聊机器人，使用 `maven` 进行项目管理，加入了 `spring` `quartz`等技术  

### 程序包含:
* `mirai` 
* `maven` 用于管理项目
* `spring`  用于...其实我是馋spring的自动注入，可太爽了
* `quartz`  用于定时任务
* `lombok`  用于简化代码
* `junit` 用于单元测试
* `logback` 用于输出日志，以及日志数据持久化
* `Pixiv` 涩图是人类进步的阶梯(x) 自研解析pixiv页面，不依赖第三方Api，更加稳定可靠
* `还有兔子` \兔子万岁/

### 设计理念
 `清爽`   我不喜欢发出去的消息总是花里胡哨，简约实用才是好的，我也很希望代码上也能做到清爽...     
 `多变`   大部分回复并非一成不变，采用随机回复，让兔叽看起来不那么死板      
 `应景`   不能见到关键词就无脑回复，太low了，尽可能的要回复合适当前场景才行       
 `实用`   只会回复垃圾信息不能叫实用，主动推送微博内容，让群友不错过任何一个steam喜加一，这就叫实用      
 `随机`   乐趣源于随机，随机发言，随机涩图，这些就变成了一种值得期待的惊喜...当然这并不适用于所有人，但谁让兔叽是我养的呢     
 `自动`   把群友想看的东西推到他们面前，而不是想起来的时候还要去手动触发     
 `兔子`   可爱的兔子+100 可爱的铃仙+1000 工程学+8 社会学+3

### 功能列表
* 指令模式代码构造
* 远程热更新业务配置参数
* 微博消息推送
* 根据图片id，tag，作者名称，获取pixiv图片
* 获取pixiv日榜
* 随机涩图
* Saucenao搜图(暂时只支持 `pixiv` 和 `danbooru` 图源)
* 群复读(添加了复读概率)
* 关键词回复
* 问候反馈
* 扭蛋功能
* 随机高强度密码
* 当前天气状况
* 整点报时
* 摩斯电码转化
* 生成二维码

### 计划
- [x] 自行解析pixiv，不再依靠第三方api
- [x] 解析pixiv排行榜
- [ ] 研究pixiv登录问题
- [ ] pixiv根据tag搜索
- [ ] pixiv根据user搜索
- [ ] 接入tracemoe
- [ ] 每日色图功能
- [x] 鸽了，咕咕咕

### 指令
    RabbitBot_RE加入了指令模式，可以精准定位指令，并且杜绝日常聊天时频繁触发关键词的风险
|指令<br/>"()"为必填参数"[]"为可选参数|示例|详情|
|----|----|-----|
|.help|.help|帮助列表，不过不完善，毕竟把东西都写上去太臃肿了|
|.config (action) (configName) (configValue)|.config set r18 off|配置相关指令，可以实现远程热更新配置参数；<br/>这是控制自己业务自定义的参数|
|.r|.r|生成一个1-100的随机数|
|.rpwd [长度]|.rpwd 10|随机一个(看起来)强度很高的密码，默认长度为6|
|.扭蛋|.扭蛋|随机抽取一个神奇的东西|
|.pid (pixivImgId)|.pid 59294081|根据pixiv图片id搜索图片|
|.ptag (pixivImgTag)|.ptag 初音ミク|根据pixiv图片tag搜索图片，结果是随机的，但分数越高的作品权重越高|
|.搜图 (图片)|.搜图 `这里是你的图片`|接入Saucenao的搜图功能|
|.来点色图|.来点色图|随机出现一张涩图，图片都源于pixiv|
|.say|.say|随机发言，是些日常语句|
|.cls|.清屏<br/>.cls|回复一个很高的空白消息，可以帮助所有人快速清理屏幕，比如上班摸鱼时突然有人发涩图的时候|
|.morse (action) (value)|.摩斯 编码 r<br>.morse encode r<br>.摩斯 解码 .-.<br/>.morse decode .-.|摩斯电码相关，编码，解码|

### 其他
[mirai事件列表一览](https://github.com/mamoe/mirai/blob/dev/mirai-core-api/src/commonMain/kotlin/event/events/README.md)

### 日志
2020年12月20日  
淦，新疆这边老鼠好大只啊，身体都快成球了  
mirai作者终于修复了登录问题，又可以继续开心的码代码了  
HttpsUtil代码整理  
ImageUtil针对http和https做兼容  
色图功能接入自己的pixiv接口

2020年12月18日<br/>
imjad的pixiv接口又炸了...寄人篱下啊...果然还是自己的东西好用<br/>
全面重写pixiv相关功能，自行逆向Api，准备完全替代掉imjad的接口<br/>
pixiv和danbooru业务代码下沉<br/>
其他api的token和key整合进properties配置文件
定时任务并入spring配置文件

2020年12月16日  
指令注册改为自动注册，省了不少事情<br/>
尝试修复了一下搜图，应该可以用了<br/>

2020年12月15日  
整体项目代码上传

-----
### IntelliJ IDEA
>[IDEA](https://www.jetbrains.com/idea/) 是 [jetbrains](https://www.jetbrains.com/) 出品 java语言开发的集成环境

感谢IDEA提供的开源项目 [申请免费授权](https://www.jetbrains.com/shop/eform/opensource?product=ALL) 渠道  
它真好用.jpg

-----
    在本项目开发过程中，没有任何兔子受到伤害
踌躇半晌，还是决定把代码上传到gayhub了<br/>
本来酷Q挂掉后，我已经失去热情了，企鹅这手太恶心人了<br/>
草草的迁移到mirai，功能也失去了不少，也没兴趣研究了<br/>
中间就这么搁置了几个月<br/>
只能说，新疆恶劣的环境，让我认识到了什么叫美好生活<br/>
于是我决定修复RabbitBot，代码也开始认真写起来了<br/>
还有很多旧功能没修复，还有很多新功能没实现，还有很多问题没解决<br/>
但我都会搞定的，嗯