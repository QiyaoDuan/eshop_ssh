<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/public/head.jspf"%>
</head>

<body>
	<a href="send_main_aindex.action">直接到后台EasyUI版</a>
	<a href="send_main_aindex.action">测试到后台</a>
	<!-- 下面两种写法都可以访问 -->
	<a href="${pageContext.request.contextPath }/category_update.action?id=3&type=ggb&hot=false">访问update</a>
	<a href="category_save.action?id=3&type=haha&hot=true">测试ModelDriven</a>
	<a href="category_query.action">查询所有类别</a>
	<br />
	<!-- 产品列表 -->
    <c:forEach items="${applicationScope.bigList}" var="list">
     <div class="products_list products_slider clear" >
     	<!-- 显示类别名称 -->
         <h2 class="sub_title">${list[0].category.type}</h2>
         <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango" >
             <c:forEach items="${list }" var="product" >
              <div style="float: left;" >
              	<a href="${shop}/product_get.action?id=${product.id}" class="product_image"><img src="${shop}/images/${product.pic}" /></a>
                  <div class="product_info">
                      <h3><a href="#">商品名称：${product.name }</a></h3>
                      <small>简单描述：${product.remark}</small> </div>
                  <div class="price_info"> 
                      <a href="${shop}/sorder_addSorder.action?product.id=${product.id}"><button class="add_cart">加入购物车</button></a>
                      <button class="price_add" title="" type="button">
                      	<span class="pr_price">￥${product.price}</span>
                      </button>
                  </div>
              </div>
             </c:forEach>
         </ul>
     </div>
    </c:forEach>
</body>
</html>