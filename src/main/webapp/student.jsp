<%--
  Created by IntelliJ IDEA.
  User: Tee
  Date: 26/10/2021
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="css/styles.css">


</head>
<body  style="overflow: auto;">
<nav id="comp-topnav" class="topnav"></nav>
<div class="row ">
    <div class=" col-md-3">
<%--        <img src="img/bg.jpg" class="img-fluid " alt="...">--%>
    </div>
    <div class="card col-md-6">
        <div id="showErrorMsg" class="text-danger"></div>
        <div id="componentRender"></div>

    </div>

</div>

<script src="js/library.js"></script>
<script src="js/student-nav.js"></script>
<script src="js/reporting.js"></script>
<script src="js/active-enrolments.js"></script>
<script src="js/grades.js"></script>

</body>
</html>
