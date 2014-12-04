<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>    
	<head>        
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/data/images/pandoraLogo.ico" type="image/x-icon" />
	<title>${dataImgTable.title }</title>        
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/data/css.css"/>
	</head>    
<body id="activity-detail" class="zh_CN ">           
	
    <div class="rich_media " style="">                
    	<div class="rich_media_inner">            
    		<div class="rich_media_meta_list" style="text-align: right;">                                             
    			<font color="white">来自：</font> <a class="rich_media_meta link nickname" href="javascript:void(0);" id="post-user">${dataImgTable.collect_website }</a>            
    		</div>            
    		<div id="page-content">                
    			<div id="img-content">
    				<div class="rich_media_content" id="js_content">
    					<c:forEach items="${list }" var="obj" varStatus="vs">
    						<c:if test="${vs.count==1 }">
	    						<p style="text-align: center;margin-top: 10px;"><img alt="" src="${obj[1] }" style="width: 98%;"></p>
	    						<h2 class="rich_media_title" style="text-align: center;margin-top: 15px;color: white;" id="activity-name">${dataImgTable.title }</h2>
	    						<p style="margin-top: 12px;color: white;"><span style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;padding-top: 100px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${obj[2] }</span></p>
    						</c:if>
    						<c:if test="${vs.count!=1 }">
	    						<p style="text-align: center;margin-top: 12px;"><img style="text-align: left;width: 98%;margin-top: 20px;" alt="" src="${obj[1] }"></p>
	    						<p style="color: white;margin-top: 7px;"><span style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${obj[2] }</span></p>
    						</c:if>
						</c:forEach>
    					
    				</div>                    
				</div>                                
			</div>            
		</div>    
	</div>                
	</body>
</html>