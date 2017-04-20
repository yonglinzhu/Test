<%--
  Created by Myeclipse 2014.
  User: zale
  Date: 2017?3?30?
  Time: ??11:48:07
  Version: 1.0
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生管理</title>
<link rel="stylesheet" href="style/mainCss/admin_base.css" />
<link rel="stylesheet" href="style/mainCss/admin_page.css" />
<link rel="stylesheet" href="style/pagingSrc/kkpager_blue.css" />
<link rel="stylesheet" href="style/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="style/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="style/mainJs/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="style/bootstrap/js/bootstrap.min.js"></script>
<script
	src="style/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="style/pagingSrc/kkpager.min.js" type="text/javascript"></script>
<style type="text/css">
.btn-addStudent {
	margin-left: 29px;
}
</style>
</head>
<body style="background: #f6f5fa;" onload="initDate()">

	<!--content S-->
	<div class="superCtab">
		<div class="ctab-Main">

			<div class="ctab-Mian-cont">
				<div class="Mian-cont-btn clearfix">
					<div class="btn-addStudent">
						<a href="#" class="btn btn-success" data-toggle="modal"
							data-target="#myModal">学生添加</a>
					</div>
				</div>
				<div class="Mian-cont-wrap" style="margin-top: 10px;">
					<div class="defaultTab-T">
						<table border="0" cellspacing="0" cellpadding="0"
							class="defaultTable">
							<tbody>
								<tr>
									<th class="t_1">ID</th>
									<th class="t_1">学生名称</th>
									<th class="t_3">生日</th>
									<th class="t_1">平均分</th>
									<th class="t_2">备注</th>
									<th class="t_3">操作</th>
								</tr>
							</tbody>
						</table>
					</div>
					<table border="0" cellspacing="0" cellpadding="0"
						class="defaultTable defaultTable2">
						<tbody>
							<c:forEach items="${studentlist}" var="student">
								<tr>
									<td class="t_1">${student.id }</td>
									<td class="t_1">${student.name }</td>
									<td class="t_3"><fmt:formatDate
											value="${student.birthday }" pattern="yyyy-MM-dd" /></td>
									<td class="t_1">${student.avgscore }</td>
									<td class="t_2">${student.description }</td>
									<td class="t_3">
										<div class="btn">
											<a href="javascript:;" onclick="initUpdateDate('${student.id }','${student.name }','<fmt:formatDate
											value="${student.birthday }" pattern="yyyy-MM-dd" />','${student.avgscore }','${student.description }')" class="modify" data-toggle="modal"
												data-target="#updateStudentmodel">修改</a><a
												href="javascript:;" onclick="delStudentDate('${student.id }')" class="delete" data-toggle="modal"
												data-target="#delStudentmodel">删除</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!--pages S-->
					<div class="pageSelect">
						<div id="kkpager" style="float: right; margin-right: 15px;"></div>
					</div>
					<!--pages E-->
				</div>

			</div>
		</div>
	</div>
	<!--main-->
	<input type="hidden" value="${theNewsPages}" id="hPages">

	<!-- 模态框（Modal）添加学生 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加一个学生</h4>
				</div>
				<div class="modal-body">
					<form id="addStudentForm" action="updateStudent" method="post"
						style="width: 70%; margin: auto;">
						<div class="form-group">
							<label for="exampleInputEmail1">填写姓名</label> <input type="text"
								class="form-control" name="name" id="exampleInputEmail1"
								placeholder="如：张三">
						</div>
						<div class="form-group input-append date form_datetime">
							<label for="exampleInputEmail1">填写生日</label> <input type="text"
								class="form-control form_datetime" name="birthdayitem"
								id="datetimeEnd" readonly>
						</div>
						<script type="text/javascript">
							$("#datetimeEnd").datetimepicker({
								format : 'yyyy-mm-dd',
								minView : 'month',
								language : 'zh-CN',
								autoclose : true,
								startDate : "1900-01-01",
								endDate : new Date()
							}).on(
									"click",
									function() {
										$("#datetimeEnd").datetimepicker(
												"setStartDate",
												$("#datetimeStart".val()))
									});
						</script>
						<div class="form-group">
							<label for="exampleInputEmail1">填写平均分</label> <input type="text"
								class="form-control" name="avgscore" id="exampleInputEmail1"
								placeholder="如：100">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">填写备注</label>
							<textarea class="form-control" rows="3" name="description"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" onclick="add_info()" class="btn btn-primary">确认提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- 模态框（Modal）修改学生 -->
	<div class="modal fade" id="updateStudentmodel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改学生信息</h4>
				</div>
				<div class="modal-body">
					<form id="updateStudentForm" action="updateStudent" method="post"
						style="width: 70%; margin: auto;">
						<!-- 定位学生的ID标识 -->
						<input type="hidden" id="idUpdate" name="id"/>
						<!-- 定位update学生信息的ID标识 -->
						<input type="hidden" value="YES" name="updateFlag"/>
						<div class="form-group">
							<label for="exampleInputEmail1">填写姓名</label> <input type="text"
								class="form-control"  name="name" id="updateName"
								placeholder="如：张三"/>
						</div>
						<div class="form-group input-append date form_datetime">
							<label for="exampleInputEmail1">填写生日</label> <input type="text"
								class="form-control form_datetime" name="birthdayitem"
								id="datetimeUpdate" readonly>
						</div>
						<script type="text/javascript">
							$("#datetimeUpdate").datetimepicker({
								format : 'yyyy-mm-dd',
								minView : 'month',
								language : 'zh-CN',
								autoclose : true,
								startDate : "1900-01-01",
								endDate : new Date()
							}).on(
									"click",
									function() {
										$("#datetimeUpdate").datetimepicker(
												"setStartDate",
												$("#datetimeStart".val()))
									});
						</script>
						<div class="form-group">
							<label for="exampleInputEmail1">填写平均分</label> <input type="text"
								class="form-control" name="avgscore" id="avgUpdate"
								placeholder="如：100">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">填写备注</label>
							<textarea id="desUpdate" class="form-control" rows="3" name="description"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" onclick="update_info()" class="btn btn-primary">确认更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- 模态框（Modal）删除学生确认框 -->
	<div class="modal fade" id="delStudentmodel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除学生询问</h4>
				</div>
				<div class="modal-body">
					<form id="delStudentForm" action="delStudent" method="post"
						style="width: 70%; margin: auto;">
						<!-- 定位学生的ID标识 -->
						
						<span>是否删除此学生？ID:<input type="text" id="idDel" name="id"/></span>
				    </form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" onclick="del_student()" class="btn btn-danger">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
<script>
	//添加入库操作
	function add_info() {
		$("#addStudentForm").submit();
		$('#myModal').modal('hide');

	}
	//修改学生信息入库操作
	function update_info() {
		$("#updateStudentForm").submit();
		$('#updateStudentmodel').modal('hide');

	}
	//删除学生信息入库操作
	function del_student() {
		$("#delStudentForm").submit();
		$('#updateStudentmodel').modal('hide');

	}
</script>
<script type="text/javascript">
	function getParameter(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	//init
	$(function() {
		var pageTatal = ${pagetotal};
		var pageNum = ${pageNum};
		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.generPageHtml({
			// 当前页
			pno : pageNum,
			//总页码
			total : pageTatal,
			//总数据条数
			totalRecords : 12,
			//链接前部
			hrefFormer : 'InitStudentInfo',
			//链接尾部
			hrefLatter : '',
			getLink : function(n) {
				return this.hrefFormer + this.hrefLatter + "?pageNum=" + n;
			}
		/*
		,lang				: {
			firstPageText			: '首页',
			firstPageTipText		: '首页',
			lastPageText			: '尾页',
			lastPageTipText			: '尾页',
			prePageText				: '上一页',
			prePageTipText			: '上一页',
			nextPageText			: '下一页',
			nextPageTipText			: '下一页',
			totalPageBeforeText		: '共',
			totalPageAfterText		: '页',
			currPageBeforeText		: '当前第',
			currPageAfterText		: '页',
			totalInfoSplitStr		: '/',
			totalRecordsBeforeText	: '共',
			totalRecordsAfterText	: '条数据',
			gopageBeforeText		: '&nbsp;转到',
			gopageButtonOkText		: '确定',
			gopageAfterText			: '页',
			buttonTipBeforeText		: '第',
			buttonTipAfterText		: '页'
		}*/
		});
	});
	/* 设置修改学生的信息 */
	function initUpdateDate(id,name,birth,avg,des){
		
		$("#idUpdate").val(id);
		$("#updateName").val(name);
		$("#datetimeUpdate").val(birth);
		$("#avgUpdate").val(avg);
		$("#desUpdate").val(des);
	}
	/* 设置删除表示ID */
	function delStudentDate(id){
		$("#idDel").val(id);
	}
</script>

</html>