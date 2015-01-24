<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/data/images/pandoraLogo.ico"
	type="image/x-icon" />
<title>${dataImgTable.title }</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/data/css.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/data/jquery-1.7.2.min.js"></script>
</head>
<script type="text/javascript">
	$().ready(function() {
		// 		document.getElementById("div1").style.left=(document.body.scrollLeft + document.body.clientHeight/2);
		// 		document.getElementById("div1").style.top=(document.body.scrollTop + document.body.clientWidth/2);
		// 		document.getElementById("div1").style.top=(document.body.offsetHeight-200)/2;
		// 		alert(document.getElementById("div1").style.left+"---"+document.getElementById("div1").style.top)
	});
	function hiddenDiv() {
		document.getElementById("div1").style.display = "none";
		document.getElementById("bg").style.display = "none";
	}
</script>
<style type="text/css">
pre {
white-space: pre-wrap;
word-wrap: break-word;
}
</style>
<body id="activity-detail" class="zh_CN " onload="hiddenDiv()" style="background-color: black;">
	<div class="rich_media " style="">
		<!-- <div id="div1" class="div1"></div>
		<div id="bg" class="bg"></div> -->
		<div style="padding: 2px;padding-top:4px;">
		<div style="padding: 2px;background-color: #DCD5C2;border-radius: 8px;">
		<div class="rich_media_inner" style="border-radius: 8px;padding-left: 2px; padding-right: 2px;">
			<div class="rich_media_meta_list" style="text-align: left;">
				<font color="#9b9b9b" style="padding-left: 10px;"> <c:forEach
						items="${taglist }" var="tag" varStatus="vss">
		    			${tag[1] }<c:if test="${fn:length(taglist)!=vss.count }">,</c:if>
					</c:forEach>
					<p style="float: right;padding-right: 5px">&nbsp;&nbsp;&nbsp;&nbsp;来自： <a class="rich_media_meta link nickname" href="javascript:void(0);" id="post-user">${dataImgTable.collect_website }</a>
					</p> </font>
			</div>
			<div id="page-content" style="clear: both;">
				<div id="img-content">
					<div class="rich_media_content" id="js_content" >
						<c:forEach items="${list }" var="obj" varStatus="vs">
							<c:if test="${vs.count==1 }">
								<p style="text-align: center;margin-top: 10px;">
									<img alt="" src="${obj[1] }" style="width: 100%;">
								</p>
								<h2 class="rich_media_title"
									style="text-align: center;margin-top: 15px;color: #7e695b;padding-left: 25px;padding-right: 30px;line-height: 30px;"
									id="activity-name">${dataImgTable.title }</h2>
								<p style="margin-top: 12px;padding-left: 7px;">
									<span
										style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;padding-top: 100px;"><pre>${obj[2]
										}</pre></span>
								</p>
							</c:if>
							<c:if test="${vs.count!=1 }">
								<p style="text-align: center;margin-top: 12px;">
									<img style="text-align: left;width: 100%;margin-top: 20px;"
										alt="" src="${obj[1] }">
								</p>
								<p style="margin-top: 7px;padding-left: 7px;">
									<span
										style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;"><pre>${obj[2]}</pre></span>
								</p>
							</c:if>
						</c:forEach>
						<p style="margin-top: 7px;padding-left: 7px;">
									<span
										style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;"><pre style="font-size: 12px;color: #624925;text-align: center;">爱生活，爱美文，欢迎发送您的文章或推荐美文给我们，邮箱地址：<span style="color: #E55252">zhangcl@hdlocker.com</span>  美文锁屏期待您的投稿。</pre></span>
								</p>
					</div>
				<div style="height: 24px;"></div>
			</div>
		 	</div>
		</div>
		</div>
		</div>
	</div>
</body>
</html>