<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
   <head>
		<title>描述列表页</title>
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
   			<div class="panel panel-default">
   				<div class="panel-heading text-center">
   					<h2>秒杀列表</h2>
   				</div>
   				<div>
   					<table class="table table-hover">
   						<thead>
   							<tr>
   								<td>名称</td>
   								<td>库存</td>
   								<td>开始时间</td>
   								<td>结束时间</td>
   								<td>创建时间</td>
   								<td>详情页</td>
   							</tr>
   						</thead>
   						<tbody>
   							<c:forEach var= "sk" items="${seckillList}">
	   							<tr>
	   								<td>${sk.name}</td>
	   								<td>${sk.number}</td>
	   								<td>
	   									<fmt:formatDate value = "${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
	   								<td>
	   									<fmt:formatDate value = "${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	   								</td>
	   								<td>
	   									<fmt:formatDate value = "${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	   								</td>
	   								<td>
	   									<a class="btn btn-info" href="/hears/seckill/${sk.seckillId}/detail" target="_blank">link</a>
									</td>
	   							</tr>
   							</c:forEach>
   						</tbody>
   					</table>
   				</div>
   			</div>
   		</div>
      
   </body>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	 
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>