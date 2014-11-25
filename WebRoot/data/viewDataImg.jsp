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
	<title>${dataImgTable.title }</title>        
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/data/css.css"/>
	</head>    
<body id="activity-detail" class="zh_CN ">           
	
    <div class="rich_media ">                
    	<div class="rich_media_inner">            
    		<h2 class="rich_media_title" id="activity-name">                ${dataImgTable.title }             </h2>            
    		<div class="rich_media_meta_list">                
    			<em id="post-date" class="rich_media_meta text"><fmt:formatDate value="${dataImgTable.collect_time }" pattern="yyyy-MM-dd"/></em>                                
    			<a class="rich_media_meta link nickname" href="javascript:void(0);" id="post-user">${dataImgTable.collect_website }</a>            
    		</div>            
    		<div id="page-content">                
    			<div id="img-content">
    				<div class="rich_media_content" id="js_content">
    					<c:forEach items="${list }" var="obj" varStatus="vs">
	    					<p style=""><img style="text-align: left;" alt="" src="${obj[1] }"></p>
	    					<p style=""><span style="max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; color: rgb(192, 80, 77);">${vs.count }、${obj[2] }</span></p>
						</c:forEach>
    					
    				</div>                    
				</div>                                
			</div>            
		</div>    
	</div>                
	</body>
</html>