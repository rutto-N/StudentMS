<%--
  Created by IntelliJ IDEA.
  User: Tee
  Date: 12/11/2021
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lecturer</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<nav id="comp-topnav" class="topnav"></nav>
<div class="row ">
    <div class="col-md-3">
<%--        <img src="img/bg.jpg" class="img-fluid " alt="...">--%>
    </div>
    <div class=" card col-md-6">
        <div id="showErrorMsg" class="text-danger"></div>
        <div id="componentRender"></div>
    </div>


</div>

<script src="js/library.js"></script>
<script src="js/lecturer-nav.js"></script>
<%--<script src="js/student.js"></script>--%>
<script src="js/save-marks.js"></script>

</body>
</html>
