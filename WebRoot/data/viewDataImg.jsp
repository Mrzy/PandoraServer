<%@page import="cn.zmdx.locker.entity.Data_img_table"%>
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
	function addViews(){
		var params = {"id": ${dataImgTable.id}};  
		var actionUrl = "<%=request.getContextPath()%>/locker!addViews.action";  
		$.ajax({  
			  url : actionUrl,  
		      type : "post", 
		      data : params,  
		      dataType : "json",  
		      cache : false,  
		      error : function(textStatus, errorThrown) {  
		      },  
		      success : function(data, textStatus) {
		      	if(data.result=='success'){
		      	}else{
		      	}
		    }  
		});
	}
	String.prototype.httpHtml = function() {
	   var reg = /(http:\/\/|https:\/\/)((\w|=|\?|\.|\/|&|-)+)/g;
	   var replaceStr = '<a href="$1$2">$1$2</a>';
	   return this.replace(reg,replaceStr);
	}
	function replaceHTTP(){
		var spans = document.getElementsByTagName("pre");
		for(var i = 0;i<spans.length;i++){
			spans[i].innerHTML = spans[i].innerHTML.httpHtml();
		}
	}
</script>
<body id="activity-detail" class="zh_CN " onload="addViews()">
	<div class="rich_media">
	<div id="title" class="rich_media_meta_list" style="text-align: left;width: 100%;height:30%;background-color: 
	<%
	if(request.getAttribute("dataImgTable")!=null&&!"".equals(request.getAttribute("dataImgTable"))){
		Data_img_table dataImgTable=(Data_img_table)request.getAttribute("dataImgTable");
		   switch(dataImgTable.getId()%5){
		   case 0:%>#e85c4f<% break;
		   case 1:%>#26a69a<% break;
		   case 2:%>#4fa9e8<% break;
		   case 3:%>#9e4fe8<% break;
		   case 4:%>#e8894f<% break;
		   }
	}
	%>">
				<!-- <font color="#9b9b9b" style="padding-left: 2px;"> <c:forEach
						items="${taglist }" var="tag" varStatus="vss">
		    			${tag[1] }<c:if test="${fn:length(taglist)!=vss.count }">,</c:if>
					</c:forEach> -->
					<p class="rich_media_title" style="text-align: left;color: #ffffff;padding-left: 15px;padding-right: 15px;padding-top: 25px;line-height: 34px;padding-bottom: 15px;"
									id="activity-name">${dataImgTable.title }</p>
					<p style="float: left;margin-top:-2px;color: #fff;padding-left: 17px;font-size: 12px;padding-bottom: 5px;">${dataImgTable.collect_website } </p> 
					<p style="text-align: right;font-size: 12px;margin-top:-2px;color: #fff;padding-right: 15px;padding-bottom: 5px;">
						<img alt="" src="<%=request.getContextPath()%>/data/images/eye.png" style="width: 16px;margin-bottom: -1.2px;">
						<span style="color: #fff;padding-left: 3px;">${dataImgTable.views}</span>
					</p>
			</div>
		<div class="rich_media_inner">
			<div id="page-content">
				<div id="img-content">
					<div class="rich_media_content" id="js_content" style="word-wrap: break-word;word-break:break-all;">
						<c:forEach items="${list }" var="obj" varStatus="vs">
							<c:if test="${vs.count==1 }">
								<!-- <p style="text-align: center;margin-top: 10px;">
									<img alt="" src="${dataImgTable.url}" style="width: 100%;">
								</p> -->
								
								<p style="margin-top: 12px;">
									<span
										style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;padding-top: 100px;">${obj[2]}</span>
								</p>
							</c:if>
							<c:if test="${vs.count!=1 }">
								<p style="text-align: center;margin-top: 12px;">
									<img style="text-align: left;width: 100%;margin-top: 20px;"
										alt="" src="${obj[1] }">
								</p>
								<p style="margin-top: 7px;">
									<span
										style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">${obj[2]}</span>
								</p>
							</c:if>
						</c:forEach>
					</div>
				<div style="height: 24px;"></div>
			</div>
		 	</div>
		</div>
	</div>
</body>
</html>