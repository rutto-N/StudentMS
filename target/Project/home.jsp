<%--
  Created by IntelliJ IDEA.
  User: Tee
  Date: 26/10/2021
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
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
    <div class=" card col-md-6">
        <div id="showErrorMsg" class="text-danger"></div>
        <div id="componentRender"></div>
    </div>


</div>

<script src="js/library.js"></script>
<script src="js/nav-bar.js"></script>
<script src="js/department.js"></script>
<script src="js/course.js"></script>
<script src="js/yearofstudy.js"></script>
<script src="js/semester.js"></script>
<script src="js/unit.js"></script>
<script src="js/student.js"></script>
<script src="js/session.js"></script>
<script src="js/academicYear.js"></script>
<script src="js/reporting.js"></script>
<script src="js/lecturer.js"></script>
<script src="js/assign-units.js"></script>
<script src="js/save-marks.js"></script>
<script src="js/open-close-year.js"></script>
<script src="js/logout.js"></script>
</body>
</html>
