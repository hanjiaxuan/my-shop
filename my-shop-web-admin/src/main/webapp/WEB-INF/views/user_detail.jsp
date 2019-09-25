<%--
  Created by IntelliJ IDEA.
  User: han
  Date: 2019/9/6
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../includes/header.jsp"></jsp:include>
    <title>我的商城 | 用户详情</title>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="box-body form-horizontal">

    <div class="form-group">
        <table id="dataTable" class="table">
            <tbody>
                <tr>
                    <td>邮箱：</td>
                    <td>${tbUser.email}</td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td>${tbUser.username}</td>
                </tr>
                <tr>
                    <td>手机：</td>
                    <td>${tbUser.phone}</td>
                </tr>
                <tr>
                    <td>创建时间：</td>
                    <td><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                </tr>
                <tr>
                    <td>更新时间：</td>
                    <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                </tr>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>
