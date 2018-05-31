1.修改 WEB-INF/classes/kop.properties中
themeid，nginxserver两个参数为实际环境值

2.修改WEB-INF\conf\spring-config\ws\ws.esb.plugins.http.xml下
各服务为实际环境ip和端口

3.最新SDK已经提交到kdmallweb工程下lib目录中，SDK配置如下：
--clientID配置
clientId=kdmallwebnative

--clientSecret配置
clientSecret=secrets

--http连接时间
CONNECTTIMEOUT=25000

--http数据读取时间
READTIMEOUT=25000

--http默认编码集
DEFAULTCHAESET=UTF-8

--SDK向服务器发出请求地址
url=http://192.168.10.116:99/koauth2/

--回调地址
RedirectUri=http://192.168.205.232:8989/newproject.html?type=openapi

scope=read

--日志文档，A1为日志文件输出，A2为控制台输出
log4j.rootLogger=info,A1,A2

--设置日志文件输出地址
log4j.appender.A1.File=D\:/kosp_logs_new/
只需以上这些配置，其余配置都可删除，如果需要更加详细的说明文档，请到kingt\trunk\src\ifsp\kingdom.api.sdk\可用的SDK包下查看

SDK错误代码			错误描述
invalid_request			请求缺少某个必需参数，包含一个不支持的参数或参数值，或者格式不正确
invalid_return_code		请求返回错误码，返回Koauth2服务器的信息，或者网络问题，比如connect  time  out,
				readtime out,connect refuse 等。
Signature encryption failed	在生成sign签名失败。
invalid_params			参数不对，或者参数格式不对。
invalid_config			配置文件有误，或者缺失配置文件
详细的错误信息请到http://192.168.10.24:8991/apidoc_index.html?type=apperror查看
