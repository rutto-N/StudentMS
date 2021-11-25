<html>
<title>Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="css/styles.css">

<body  style="overflow: auto;">
<nav id="comp-topnav" class="topnav"></nav>
<div class="row">
    <div class="col-md-3">
<%--        <img src="img/bg.jpg" class="img-fluid " alt="...">--%>
    </div>
    <div class="card col-md-6">
        <div id="showErrorMsg"></div>
        <div id="componentRender"></div>
    </div>


</div>


<% session.invalidate();%>

<script src="js/library.js"></script>
<%--<script src="js/nav-bar.js"></script>--%>
<%--<script src="js/department.js"></script>--%>
<%--<script src="js/course.js"></script>--%>
<script src="js/login.js"></script>
<%--<script src="js/yearofstudy.js"></script>--%>
<%--<script src="js/semester.js"></script>--%>
<%--<script src="js/unit.js"></script>--%>
<%--<script src="js/classroom.js"></script>--%>
<%--<script src="js/student.js"></script>--%>
<%--<script src="js/session.js"></script>--%>

</body>
</html>
