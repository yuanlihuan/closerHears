<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>欢迎您登录</title>
<link href="css/account/style.css" rel='stylesheet' type='text/css' media="all"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta name="keywords" content="App Loction Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements"./>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</script>
<script src="js/jquery/jquery.min.js"></script>
<!--webfonts-->
<!--<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
//webfonts-->
</head>
<body>
	<h1>天涯咫尺</h1>
		<div class="app-location">
			<h2>欢迎小伙伴</h2>
			<div class="line"><span></span></div>
			<!-- <div class="location"><img src="picture/account/images/location.png" class="img-responsive" alt="" /></div> -->
			<form>
				<input id = "userName" type="text" class="text" value="用户名" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '用户名';}" >
				<input id = "password" type="password" value="密码" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '密码';}">
				<div class="submit">
					<input type="submit" onClick="accountLogin()" value="登录" >
				</div>
				<div class="clear"></div>
				<div class="new">
					<h3><a href="/hears/forgetPassword.action">忘记密码 ?</a></h3>
					<h4><a href="/hears/accountRegister.action">立即注册</a></h4>
					<div class="clear"></div>
				</div>
			</form>
		</div>
	<!--start-copyright-->
   		<!--<div class="copy-right">
				<p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
		</div>
	//end-copyright-->
	<script type="text/javascript">
			
		function accountLogin() {
			debugger;
			$.ajax({
				url: 'accountLogin.action',
				type: 'post',
				data: {userName:$('#userName').val(), password:$('#password').val()},
				dataType: 'json',
				success: function(data){
					if(data.status == 'success') {
						window.location.href = data.url;		
					} else {
						alert(data.message);
					}
				}
			});			
		}
	</script>
</body>
</html>