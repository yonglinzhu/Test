<%--
  Created by IntelliJ IDEA.
  User: zale
  Date: 2017/5/15
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8"
         contentType="text/html; charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>学生信息管理系统</title>
    <meta name="description" content="Static &amp; Dynamic Tables"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/font-awesome/4.5.0/css/font-awesome.min.css"/>
    <!-- page specific plugin styles -->
    <!-- text fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts.googleapis.com.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace.min.css" class="ace-main-stylesheet"
          id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-part2.min.css"
          class="ace-main-stylesheet"/>
    <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${pageContext.request.contextPath}/assets/js/ace-extra.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-datepicker3.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-timepicker.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/daterangepicker.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-colorpicker.min.css"/>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/pagingSrc/kkpager_blue.css"/>
    <script src="${pageContext.request.contextPath}/pagingSrc/kkpager.min.js"
            type="text/javascript"></script>
    <!--[if lte IE 8]>
    <script src="${pageContext.request.contextPath}/assets/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
    <script src="js/user-defined.js"></script>
    <![endif]-->
</head>

<body class="no-skin">
<div id="navbar" class="navbar navbar-default          ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="javascript:;" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    学生信息管理系统
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo"
                             src="${pageContext.request.contextPath}/assets/images/avatars/user.jpg"
                             alt="Jason's Photo"/>
                        <span class="user-info">
									<small>Welcome,</small>
									Jason
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-cog"></i>
                                Settings
                            </a>
                        </li>

                        <li>
                            <a href="profile.html">
                                <i class="ace-icon fa fa-user"></i>
                                Profile
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-power-off"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.navbar-container -->
</div>

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.loadState('main-container')
        } catch (e) {
        }
    </script>

    <div id="sidebar" class="sidebar responsive  ace-save-state">
        <script type="text/javascript">
            try {
                ace.settings.loadState('sidebar')
            } catch (e) {
            }
        </script>

        <ul class="nav nav-list">
            <li class="active">
                <a href="goStudentManager" class="">
                    <i class="menu-icon fa fa-list"></i>
                    <span class="menu-text"> 学生信息管理 </span>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="">
                <a href="goGradeManager" class="">
                    <i class="menu-icon fa fa-list"></i>
                    <span class="menu-text"> 班级信息管理 </span>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="">
                <a href="goSubjectManager" class="">
                    <i class="menu-icon fa fa-list"></i>
                    <span class="menu-text"> 学科信息管理</span>
                </a>
                <b class="arrow"></b>
            </li>
        </ul><!-- /.nav-list -->

        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
               data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>
    </div>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <div style="margin-left: 20px;">
                    <a href="#add-student" role="button" class="btn btn-xs btn-success" data-toggle="modal">添加学生 </a>
                </div>
                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input"
                                           id="nav-search-input" autocomplete="off"/>
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- /.nav-search -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <table id="simple-table" class="table  table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class="detail-col">详情</th>
                                        <th>学号</th>
                                        <th>姓名</th>
                                        <th class="hidden-480">性别</th>

                                        <th>所在班级</th>
                                        <th>选修科目数</th>
                                        <th>平均分</th>

                                        <th class="center">操作</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <%--没有数据 - 提示信息--%>
                                    <c:if test="${pageResult.result == null || pageResult.result.size() ==0}">
                                        <tr>
                                            <td style="color: red;text-align: center" colspan="8">
                                                暂无任何学生信息，请添加！
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach items="${pageResult.result}" var="student">
                                        <tr>
                                            <td class="center">
                                                <div class="action-buttons">
                                                    <a href="#" class="green bigger-140 show-details-btn"
                                                       title="Show Details">
                                                        <i class="ace-icon fa fa-angle-double-down"></i>
                                                        <span class="sr-only">Details</span>
                                                    </a>
                                                </div>
                                            </td>

                                            <td><c:out value="${student.number}"/></td>
                                            <td><c:out value="${student.name}"/></td>
                                            <c:choose>
                                                <c:when test="${student.sex.name() == 'Male'}">
                                                    <td class="hidden-480">男</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="hidden-480">女</td>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:forEach items="${student_grade}" var="grade">
                                                <c:if test="${student.grade.id == grade.id && grade.state != '0'}">
                                                    <td><c:out value="${student.grade.classname}"/></td>
                                                </c:if>
                                                <c:if test="${student.grade.id == grade.id && grade.state == '0'}">
                                                    <td>暂无班级</td>
                                                </c:if>
                                            </c:forEach>
                                            <td><c:out value="${student.subjects.size()}"/></td>
                                            <td><c:out value="${student.average}"/></td>

                                            <td class="center">
                                                <div class="hidden-sm hidden-xs btn-group">
                                                    <a href="goEntryScore?id=<c:out value="${student.id}"/>"
                                                       class="btn btn-xs btn-warning" title="录入已选课程分数">
                                                        <i class="ace-icon fa fa-flag bigger-120"></i>
                                                    </a>
                                                    <a href="goAddSubject?id=<c:out value="${student.id}"/>"
                                                       class="btn btn-xs btn-success" title="选修课程">
                                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                                    </a>

                                                    <a href="#update-student" data-toggle="modal"
                                                       class="btn btn-xs btn-info"
                                                       title="修改该学生信息"
                                                       onclick="updateStudentInfo(${student.id},'${student.name}','${student.birthday}','${student.sex}',${student.grade.id})">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a>

                                                    <a href="deleteStudent?id=<c:out value="${student.id}"/>"
                                                       class="btn btn-xs btn-danger" title="删除该学生信息">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </a>
                                                </div>

                                                <div class="hidden-md hidden-lg">
                                                    <div class="inline pos-rel">
                                                        <button class="btn btn-minier btn-primary dropdown-toggle"
                                                                data-toggle="dropdown" data-position="auto">
                                                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                        </button>

                                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                            <li>
                                                                <a href="#" class="tooltip-info" data-rel="tooltip"
                                                                   title="View">
                                                                <span class="blue">
                                                                    <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                                                </span>
                                                                </a>
                                                            </li>

                                                            <li>
                                                                <a href="#" class="tooltip-success" data-rel="tooltip"
                                                                   title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                                                </a>
                                                            </li>

                                                            <li>
                                                                <a href="#" class="tooltip-error" data-rel="tooltip"
                                                                   title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <!--详情-->
                                        <tr class="detail-row">
                                            <td colspan="8">
                                                <div class="table-detail">
                                                    <div class="row">
                                                        <!-- /.col -->
                                                        <div class="col-xs-12 col-sm-2">
                                                            <div class="text-center">
                                                                <form action="" method="post"
                                                                      enctype="multipart/form-data">
                                                                    <div class="col-sm-9 big-photo"
                                                                         style="margin-top: 45px;">
                                                                        <a href="#upload-picture"
                                                                           onclick="uploadInitStudentId(${student.id})"
                                                                           data-toggle="modal"
                                                                           id="upload-view-div">
                                                                            <img id="" border="0"
                                                                                 src="pictureView?id=<c:out value="${student.id}"/>"
                                                                                 width="90" height="90">
                                                                        </a>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-7">
                                                            <div class="space visible-xs"></div>

                                                            <div class="profile-user-info profile-user-info-striped">
                                                                <div class="profile-info-row">
                                                                    <div class="profile-info-name"> 姓名</div>

                                                                    <div class="profile-info-value">
                                                                        <span><c:out value="${student.name}"/></span>
                                                                    </div>
                                                                </div>

                                                                <div class="profile-info-row">
                                                                    <div class="profile-info-name"> 性别</div>
                                                                    <c:choose>
                                                                        <c:when test="${student.sex.name() == 'Male'}">
                                                                            <div class="profile-info-value">
                                                                                <span>男</span>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="profile-info-value">
                                                                                <span>女</span>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>

                                                                <div class="profile-info-row">
                                                                    <div class="profile-info-name"> 学号</div>

                                                                    <div class="profile-info-value">
                                                                        <span><c:out value="${student.number}"/></span>
                                                                    </div>
                                                                </div>

                                                                <div class="profile-info-row">
                                                                    <div class="profile-info-name"> 序号</div>

                                                                    <div class="profile-info-value">
                                                                        <span><c:out value="${student.id}"/></span>
                                                                    </div>
                                                                </div>

                                                                <div class="profile-info-row">
                                                                    <div class="profile-info-name"> 生日</div>

                                                                    <div class="profile-info-value">
                                                                        <span><c:out value="${student.birthday}"/></span>
                                                                    </div>
                                                                </div>

                                                                <div class="profile-info-row">
                                                                    <div class="profile-info-name"> 所在班级</div>

                                                                    <div class="profile-info-value">
                                                                        <c:forEach items="${student_grade}"
                                                                                   var="grade">
                                                                            <c:if test="${student.grade.id == grade.id && grade.state != '0'}">
                                                                                <span>${student.grade.classname}</span>
                                                                            </c:if>
                                                                            <c:if test="${student.grade.id == grade.id && grade.state == '0'}">
                                                                                <span>暂无班级</span>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-3">
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <%--END 详情--%>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div><!-- /.span -->
                        </div><!-- /.row -->
                        <!--弹出层 图片上传学生-->
                        <div id="upload-picture" class="modal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">
                                            &times;
                                        </button>
                                        <h4 class="blue bigger">头像上传</h4>
                                    </div>
                                    <form class="form-horizontal" role="form" action="uploadPicture"
                                          method="post" enctype="multipart/form-data">
                                        <div class="modal-body" style="margin-left: 60px">
                                            <div class="col-sm-9 big-photo">
                                                <div id="preview" style="margin-left: 50%">
                                                    <img id="imghead" border="0"
                                                         src="${pageContext.request.contextPath}/assets/images/avatars/avatar5.png"
                                                         width="90" height="90"
                                                         onclick="$('#previewImg').click();">
                                                    <br/>
                                                </div>

                                                <input type="file" name="file" onchange="previewImage(this)"
                                                       style="display: none;" id="previewImg">
                                            </div>
                                            <input type="hidden" name="id" id="upload-studentId" value="">
                                            <button style="margin-right: 48%;margin-top: 10px"
                                                    class="pull-right btn btn-sm btn-primary btn-white btn-round"
                                                    type="submit">
                                                提交
                                            </button>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-sm" style="display: none"
                                                    data-dismiss="modal">

                                            </button>

                                            <button type="submit" style="display: none"
                                                    class="btn btn-sm btn-primary">
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                        <!--End 弹出层-->
                        <!--弹出层 添加学生-->
                        <div id="add-student" class="modal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="blue bigger">添加学生</h4>
                                    </div>
                                    <form class="form-horizontal" role="form" action="saveStudent" method="post">
                                        <div class="modal-body" style="margin-left: 60px">
                                            <div class="form-group">
                                                <label>姓名：</label>
                                                <input type="text" name="name" placeholder="填写学生姓名"/>
                                            </div>
                                            <div class="form-group">
                                                <label>性别：</label>
                                                <label>
                                                    <input name="sex" checked type="radio" value="Male" class="ace"/>
                                                    <span class="lbl"> 男</span>
                                                </label>
                                                <label>
                                                    <input name="sex" type="radio" value="Female" class="ace"/>
                                                    <span class="lbl"> 女</span>
                                                </label>
                                            </div>
                                            <div class="form-group">
                                                <label>生日：</label>
                                                <input class="date-picker" name="birthday" placeholder="选择生日日期"
                                                       id="id-date-picker-1" type="text"
                                                       data-date-format="yyyy-mm-dd"/>
                                            </div>
                                            <div class="form-group">
                                                <label>班级：</label>
                                                <select class="" name="grade.id" style="width: 190px;"
                                                        id="form-field-select-1">
                                                    <option value="0"></option>
                                                    <c:forEach items="${student_grade}" var="grade">
                                                        <c:if test="${grade.state != '0'}">
                                                            <option value="<c:out value="${grade.id}"/>"><c:out value="${grade.classname}"/></option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button class="btn btn-sm" data-dismiss="modal">
                                                <i class="ace-icon fa fa-times"></i>
                                                取消
                                            </button>

                                            <button type="submit" class="btn btn-sm btn-primary">
                                                <i class="ace-icon fa fa-check"></i>
                                                保存
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div><!-- PAGE CONTENT ENDS -->
                        <!--End 弹出层-->
                        <!--弹出层 修改学生信息-->
                        <div id="update-student" class="modal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="blue bigger">修改学生信息</h4>
                                    </div>
                                    <form class="form-horizontal" name="updateform" role="form" action="updateStudent"
                                          method="post">
                                        <%--学生ID--%>
                                        <input type="hidden" name="id" id="update-id">
                                        <div class="modal-body" style="margin-left: 60px">
                                            <div class="form-group">
                                                <label>姓名：</label>
                                                <input type="text" id="update-name" name="name" placeholder="填写学生姓名"/>
                                            </div>
                                            <div class="form-group">
                                                <label>性别：</label>
                                                <label>
                                                    <input id="update-sex-male" name="sex" checked type="radio"
                                                           value="Male" class="ace"/>
                                                    <span class="lbl"> 男</span>
                                                </label>
                                                <label>
                                                    <input name="sex" id="update-sex-female" type="radio" value="Female"
                                                           class="ace"/>
                                                    <span class="lbl"> 女</span>
                                                </label>
                                            </div>
                                            <div class="form-group">
                                                <label>生日：</label>
                                                <input class="date-picker" id="update-birthday" name="birthday"
                                                       placeholder="选择生日日期"
                                                       id="id-date-picker-2" type="text"
                                                       data-date-format="yyyy-mm-dd"/>
                                            </div>
                                            <div class="form-group">
                                                <label>班级：</label>
                                                <input type="hidden" id="update-grade-id" class="" name="grade.id">
                                                <select class="" name="grade_id" style="width: 190px;"
                                                        id="form-field-select-2">
                                                    <option value="0"></option>
                                                    <c:forEach items="${student_grade}" var="grade">
                                                        <c:if test="${grade.state != '0'}">
                                                            <option value="<c:out value="${grade.id}"/>"><c:out value="${grade.classname}"/></option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button class="btn btn-sm" data-dismiss="modal">
                                                <i class="ace-icon fa fa-times"></i>
                                                取消
                                            </button>

                                            <button onclick="updateInitGradeId()" type="submit"
                                                    class="btn btn-sm btn-primary">
                                                <i class="ace-icon fa fa-check"></i>
                                                保存
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div><!-- PAGE CONTENT ENDS -->
                        <!--End 弹出层-->
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- div.table-responsive -->

                                <!-- div.dataTables_borderWrap -->
                                <div style="display: none">
                                    <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
                <!--pages S-->
                <div class="pageSelect">
                    <div id="kkpager" style="float: right;margin-right: 15px;"></div>
                </div>
                <!--pages E-->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/buttons.flash.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/buttons.print.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/buttons.colVis.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables.select.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/daterangepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap-colorpicker.min.js"></script>
<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script>
    function updateStudentInfo(id, name, birthday, sex, gradeId) {
        $("#update-id").val(id);
        $("#update-name").val(name);
        $("#update-birthday").val(birthday);
        document.updateform.grade_id.value = gradeId;// 班级ID
        if (sex == "女") {
            $("#update-sex-female").attr("checked", "checked");
        } else {
            $("#update-sex-male").attr("checked", "checked");
        }
    }
    function updateInitGradeId() {
        $("#update-grade-id").val($("#form-field-select-2").val());
    }
    /*图片上传-初始化学生ID*/
    function uploadInitStudentId(id) {
        $("#upload-studentId").val(id);
    }
</script>
<script type="text/javascript">
    //    确认选修课程
    function selectLesson(id) {
        var disabled = confirm("确认禁用文章？");
        if (disabled) {
            $.ajax({
                cache: true,
                type: "POST",
                url: "${webroot}/platform/article/disabledArticle.do",
                data: {
                    'id': articleId
                },
                async: false,
                dataType: "json",
                error: function (data) {
                    layer.msg(data.msg);
                    return false;
                },
                success: function (data) {
                    console.log(data);
                    if (data.code != 0) {
                        layer.msg(data.msg);
                    } else {
                        $('#article_table').bootstrapTable('refresh');
                    }
                }
            });
        }
    }
    jQuery(function ($) {
        /*头像上传*/
        $('#upload-picture').on('shown.bs.modal', function () {
            if (!ace.vars['touch']) {
                $(this).find('.chosen-container').each(function () {
                    $(this).find('a:first-child').css('width', '50px');
                    $(this).find('.chosen-drop').css('width', '50px');
                    $(this).find('.chosen-search input').css('width', '50px');
                });
            }
        });
        $('#update-picture').on('shown.bs.modal', function () {
            if (!ace.vars['touch']) {
                $(this).find('.chosen-container').each(function () {
                    $(this).find('a:first-child').css('width', '210px');
                    $(this).find('.chosen-drop').css('width', '210px');
                    $(this).find('.chosen-search input').css('width', '200px');
                });
            }
        });
        /*add student*/
        $('#add-student').on('shown.bs.modal', function () {
            if (!ace.vars['touch']) {
                $(this).find('.chosen-container').each(function () {
                    $(this).find('a:first-child').css('width', '180px');
                    $(this).find('.chosen-drop').css('width', '180px');
                    $(this).find('.chosen-search input').css('width', '170px');
                });
            }
        });
        /*update student*/
        $('#update-student').on('shown.bs.modal', function () {
            if (!ace.vars['touch']) {
                $(this).find('.chosen-container').each(function () {
                    $(this).find('a:first-child').css('width', '180px');
                    $(this).find('.chosen-drop').css('width', '180px');
                    $(this).find('.chosen-search input').css('width', '170px');
                });
            }
        });

        /*date*/
        //datepicker plugin
        //link
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        })
        //show datepicker when clicking on the icon
            .next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        //or change it into a date range picker
        $('.input-daterange').datepicker({autoclose: true});


        //to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
        $('input[name=date-range-picker]').daterangepicker({
            'applyClass': 'btn-sm btn-success',
            'cancelClass': 'btn-sm btn-default',
            locale: {
                applyLabel: 'Apply',
                cancelLabel: 'Cancel',
            }
        })
        //initiate dataTables plugin
        var myTable =
            $('#dynamic-table')
            //.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
                .DataTable({
                    bAutoWidth: false,
                    "aoColumns": [
                        {"bSortable": false},
                        null, null, null, null, null,
                        {"bSortable": false}
                    ],
                    "aaSorting": [],


                    //"bProcessing": true,
                    //"bServerSide": true,
                    //"sAjaxSource": "http://127.0.0.1/table.php"	,

                    //,
                    //"sScrollY": "200px",
                    //"bPaginate": false,

                    //"sScrollX": "100%",
                    //"sScrollXInner": "120%",
                    //"bScrollCollapse": true,
                    //Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
                    //you may want to wrap the table inside a "div.dataTables_borderWrap" element

                    //"iDisplayLength": 50


                    select: {
                        style: 'multi'
                    }
                });

        $.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';

        new $.fn.dataTable.Buttons(myTable, {
            buttons: [
                {
                    "extend": "colvis",
                    "text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
                    "className": "btn btn-white btn-primary btn-bold",
                    columns: ':not(:first):not(:last)'
                },
                {
                    "extend": "copy",
                    "text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "csv",
                    "text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "excel",
                    "text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "pdf",
                    "text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "print",
                    "text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
                    "className": "btn btn-white btn-primary btn-bold",
                    autoPrint: false,
                    message: 'This print was produced using the Print button for DataTables'
                }
            ]
        });
        myTable.buttons().container().appendTo($('.tableTools-container'));

        //style the message box
        var defaultCopyAction = myTable.button(1).action();
        myTable.button(1).action(function (e, dt, button, config) {
            defaultCopyAction(e, dt, button, config);
            $('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
        });

        var defaultColvisAction = myTable.button(0).action();
        myTable.button(0).action(function (e, dt, button, config) {

            defaultColvisAction(e, dt, button, config);

            if ($('.dt-button-collection > .dropdown-menu').length == 0) {
                $('.dt-button-collection')
                    .wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
                    .find('a').attr('href', '#').wrap("<li />")
            }
            $('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
        });

        setTimeout(function () {
            $($('.tableTools-container')).find('a.dt-button').each(function () {
                var div = $(this).find(' > div').first();
                if (div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
                else $(this).tooltip({container: 'body', title: $(this).text()});
            });
        }, 500);

        myTable.on('select', function (e, dt, type, index) {
            if (type === 'row') {
                $(myTable.row(index).node()).find('input:checkbox').prop('checked', true);
            }
        });
        myTable.on('deselect', function (e, dt, type, index) {
            if (type === 'row') {
                $(myTable.row(index).node()).find('input:checkbox').prop('checked', false);
            }
        });

        /////////////////////////////////
        //table checkboxes
        $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);

        //select/deselect all rows according to table header checkbox
        $('#dynamic-table > thead > tr > th input[type=checkbox], #dynamic-table_wrapper input[type=checkbox]').eq(0).on('click', function () {
            var th_checked = this.checked;//checkbox inside "TH" table header

            $('#dynamic-table').find('tbody > tr').each(function () {
                var row = this;
                if (th_checked) myTable.row(row).select();
                else  myTable.row(row).deselect();
            });
        });

        //select/deselect a row when the checkbox is checked/unchecked
        $('#dynamic-table').on('click', 'td input[type=checkbox]', function () {
            var row = $(this).closest('tr').get(0);
            if (this.checked) myTable.row(row).deselect();
            else myTable.row(row).select();
        });

        $(document).on('click', '#dynamic-table .dropdown-toggle', function (e) {
            e.stopImmediatePropagation();
            e.stopPropagation();
            e.preventDefault();
        });

        //And for the first simple table, which doesn't have TableTools or dataTables
        //select/deselect all rows according to table header checkbox
        var active_class = 'active';
        $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function () {
            var th_checked = this.checked;//checkbox inside "TH" table header

            $(this).closest('table').find('tbody > tr').each(function () {
                var row = this;
                if (th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
                else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
            });
        });

        //select/deselect a row when the checkbox is checked/unchecked
        $('#simple-table').on('click', 'td input[type=checkbox]', function () {
            var $row = $(this).closest('tr');
            if ($row.is('.detail-row ')) return;
            if (this.checked) $row.addClass(active_class);
            else $row.removeClass(active_class);
        });

        /********************************/
        //add tooltip for small view action buttons in dropdown menu
        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});

        //tooltip placement on right or left
        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('table')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }

        /***************/
        $('.show-details-btn').on('click', function (e) {
            e.preventDefault();
            $(this).closest('tr').next().toggleClass('open');
            $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
        });
        /***************/

    })
</script>
<script>
    //图片上传预览    IE是用了滤镜。
    function previewImage(file) {
        var MAXWIDTH = 90;
        var MAXHEIGHT = 90;
        var div = document.getElementById('preview');
        if (file.files && file.files[0]) {
            div.innerHTML = '<img id=imghead onclick=$("#previewImg").click()>';
            var img = document.getElementById('imghead');
            img.onload = function () {
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width = rect.width;
                img.height = rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top + 'px';
            }
            var reader = new FileReader();
            reader.onload = function (evt) {
                img.src = evt.target.result;
            }
            reader.readAsDataURL(file.files[0]);
        }
        else //兼容IE
        {
            var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
            div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
        }
    }
    function clacImgZoomParam(maxWidth, maxHeight, width, height) {
        var param = {top: 0, left: 0, width: width, height: height};
        if (width > maxWidth || height > maxHeight) {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;

            if (rateWidth > rateHeight) {
                param.width = maxWidth;
                param.height = Math.round(height / rateWidth);
            } else {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }
        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }
    //init
    $(function () {
        var totalPage = ${pageResult.total};
        var pageNo = ${pageResult.currentPage};
        if (!pageNo) {
            pageNo = 1;
        }
        //生成分页
        //有些参数是可选的，比如lang，若不传有默认值
        kkpager.generPageHtml({
            pno: pageNo,
            //总页码
            total: totalPage,
            //总数据条数
            totalRecords: 54,
            //链接前部
            hrefFormer: 'goStudentManager',
            //链接尾部
            hrefLatter: '.action',
            getLink: function (n) {
                return this.hrefFormer + this.hrefLatter + "?pageIndex=" + n;
            }
        });
    });
</script>
</body>
</html>
