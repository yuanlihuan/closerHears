<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
   <head>
		<title>描述详情页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- 引入 Bootstrap -->
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
		<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
		<!--[if lt IE 9]>
		   <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		   <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		<![endif]-->
   </head>
   <body>
   		<div class="container">
   			<div class="panel panel-default text-center">
   				<div class="panel-heading">
   				<h1>${seckill.name}</h1>
   				</div>
   			</div>
   			<div class="panel-boby">
   				<h2 class="text-danger">
   					<!-- 显示time标签 -->
   					<span class="glyphicon glyphicon-time"></span>
   					<!-- 展示倒计时 -->
   					<span class="glyphicon" id="seckill-box"></span>
   				</h2>
   			</div>
   		</div>
   		<!-- 模态框（Modal） -->
		<div class="modal fade" id="killPhoneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							填写你的手机号码
						</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-8 col-xs-offset-2">
								<input type ="text" name="killPhone" id="killPhoneKey"
								placeholder="填写手机号" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<span id="killPhoneMessage" class="glyphicon"></span>
						<button type="button" id="killPhoneBtn" class="btn btn-primary">
							提交更改
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
      

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	 
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- CDN jquery的cookie插件 -->
	<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
	<!-- CDN jquery的countDown插件 -->
	<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/hears/seckill/detail.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(function(){
			seckill.detail.init({
				seckillId:${seckill.seckillId},
				startTime:${seckill.startTime.time},//毫秒时间
				endTime:${seckill.endTime.time}
			});
		});
	</script>
   </body>
</html>