#### 简介
这个项目是图书rfid厂商更新排架号到图星lsp系统里面。 基本逻辑是 rfid新建一个视图，视图结构在sql文件夹中，
然后在yml配置文件中，添加秘钥等信息。项目配置了mysql,sqlserver,oracle,等数据库，默认是mysql 修改成其它数据库
注意修改mapper文件中的sql
##### 接口使用
项目有三个接口
* /api/back/shelf/test  post接口 用来测试 是否正常  使用查看 swagger  /swagger-ui.html
* /api/back/shelf/init  get请求 初始更新所有排架号
* 自带了一个定时任务，每天晚上11点  更新最近两天更新的排架号