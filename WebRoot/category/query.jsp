<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/public/head.jspf"%>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			url : 'category_queryJoinAccount.action',	//url地址改为请求categoryAction  
			loadMsg : 'Loading......',
			queryParams : {
				type : ''	//type参数，这里不需要传具体的type，因为我们要显示所有的type  
			},
			fitColumns : true,
			striped : true,
			owrap : true,
		 	//singleSelect : true,
			pagination : true,
			rowStyler : function(index, row) {
				console.info("index" + index + "," + row)
				if (index % 2 == 0) {
					return 'background-color:#fff;';
				} else {
					return 'background-color:#ff0;';
				}
			},
			toolbar : [{
				iconCls : 'icon-add',
				text : '添加类别',
				handler : function() {
					parent.$("#win").window({ //因为<div>放在了aindex.jsp中，所以这里创建窗口要先调用parent  
            			title:"添加类别",  
            			width:350,  
            			height:170,  
            			content:'<iframe src="send_category_save.action" frameborder="0" width="100%" height="100%"/>'  
        			});
				}
			},'-', {
				iconCls : 'icon-edit',
				text : '更新类别',
				handler : function() {
					//判断是否有选中行记录，使用getSelections获取选中的所有行  
        			var rows = $("#dg").datagrid("getSelections");  
        			if(rows.length == 0) {  
            			//弹出提示信息  
            			$.messager.show({ //语法类似于java中的静态方法，直接对象调用  
                			title:'错误提示',  
                			msg:'至少要选择一条记录',  
                			timeout:2000,  
                			showType:'slide',  
            			});  
        			}else if(rows.length != 1) {  
            			//弹出提示信息  
            			$.messager.show({ //语法类似于java中的静态方法，直接对象调用  
                			title:'错误提示',  
                			msg:'每次只能更新一条记录',  
                			timeout:2000,  
                			showType:'slide',  
            			});  
        			} else{  
            			//1. 弹出更新的页面  
            			parent.$("#win").window({  
                			title:"更新类别",  
                			width:350,  
                			height:250,  
                			content:'<iframe src="send_category_update.action" frameborder="0" width="100%" height="100%"/>'  
            			});  
        			}  
				}
			},'-', {
				iconCls : 'icon-remove',
				text : '删除类别',
				handler : function() {
					//判断是否有选中行记录，使用getSelections获取选中的所有行  
					var rows = $("#dg").datagrid("getSelections");
					//返回被选中的行，如果没有任何行被选中，则返回空数组  
					if (rows.length == 0) {
						//弹出提示信息  
						$.messager.show({ //语法类似于java中的静态方法，直接对象调用  
							title : '错误提示',
							msg : '至少要选择一条记录',
							timeout : 2000,
							showType : 'slide',
						});
					} else {
						//提示是否确认删除，如果确认则执行删除的逻辑  
						$.messager.confirm(
							'删除的确认对话框',
							'您确定要删除此项吗？',
							function(r) {
								if (r) {
									//1. 从获取的记录中获取相应的的id,拼接id的值，然后发送后台1,2,3,4  
									var ids = "";
									for ( var i = 0; i < rows.length; i++) {
										ids += rows[i].id + ",";
									}
									ids = ids.substr(0,ids.lastIndexOf(","));
									//2. 发送ajax请求  
									$.post("category_deleteByIds.action",{
										ids : ids
									},
									function(result) {
										if (result == "true") {
											//将刚刚选中的记录删除，要不然会影响后面更新的操作  
											$("#dg").datagrid("uncheckAll");
											//刷新当前页，查询的时候我们用的是load，刷新第一页，reload是刷新当前页  
											$("#dg").datagrid("reload");//不带参数默认为上面的queryParams  
										} else {
											$.messager.show({
											title : '删除异常',
											msg : '删除失败，请检查操作',
											timeout : 2000,
											showType : 'slide',
											});
										}
									},"text");
								}
							});
					}
				}
			},'-',{ //查询按钮不是LinkButton，它有语法，但是也支持解析HTML标签  
				text : "<input id='ss' name='serach' />"
			} ],
			frozenColumns : [ [ {
				field : 'checkbox',
				checkbox : true
			}, {
				field : 'id',
				title : '编号',
				width : 200
				} //这里的field字段要和json数据中的一样               
			] ],
			columns : [ [{
				field : 'type',
				title : '类别名称',
				width : 100, //字段type  
				formatter : function(value, row, index) {
					return "<span title=" +　value + ">" + value + "</span>";
				}
			}, {
				field : 'hot',
				title : '热卖',
				width : 100, //字段hot  
				formatter : function(value, row, index) {
					if (value) { //如果是hot，该值为true，value是boolean型变量  
						return "<input type='checkbox' checked='checked' disabled='true'"; //勾选  
					} else {
						return "<input type='checkbox' disable='true'"; //不勾选  
					}
				}
			}, {
				field : 'account.login',
				title : '所属管理员',
				width : 200, //account.login管理员登录名  
				formatter : function(value, row, index) {
					if (row.account != null && row.account.login != null) {
							return row.account.login; //如果登录名不为空，显示登录名  
					} else {
						return "此类别没有管理员";
					}
				}
			} ] ]
		});
		//把普通的文本框转化为查询搜索文本框  
		$('#ss').searchbox({
			//触发查询事件  
			searcher : function(value, name) { //value表示输入的值  
				//alert(value + "," + name)  
				//获取当前查询的关键字，通过DataGrid加载相应的信息，使用load加载和显示第一页的所有行。  
				//如果指定了参数，它将取代'queryParams'属性。通常可以通过传递一些参数执行一次查询，通过调用这个方法会向上面url指定的action去发送请求，从服务器加载新数据。  
				$('#dg').datagrid('load', {
					type : value
				});
			},
		prompt : '请输入搜索关键字'
		});
	});
</script>
</head>

<body>
	<table id="dg"></table>
</body>
</html>
