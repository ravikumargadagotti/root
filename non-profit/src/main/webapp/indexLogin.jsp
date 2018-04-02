<!DOCTYPE html>
<html lang="en" >

<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home Office</title>

    <!-- favicon -->
    <link href="images/favicon.png" type="image/x-icon" rel="shortcut icon">
    <!-- css class -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
     <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/javascript.js"></script>
<script type="text/javascript" src="js/smoothscroll.js"></script>
<script src="js/bootstrap.min.js"></script>
   
  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

      <link rel="stylesheet" href="css/style.css">

  
</head>

<body data-spy="scroll" data-target=".navbar-fixed-top">
<!-- Navbar -->
<nav class="navbar navbar-default navbar-fixed-top" style="background: black;">
    <div class="container-fluid">
        <div class="navbar-header page-scroll" >
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="index.jsp" >
                <img alt="Brand" src="images/logo.png">
            </a>
            <p class="navbar-text" data-sr='wait 2s, then enter top and hustle 40px over 1.5s'></p>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right nav nav-pills">
                <li class="active"><a class="page-scroll" href="index.jsp#start">Home</a></li>
                <li><a class="page-scroll" href="index.jsp#about">About Us</a></li>
                <li><a class="page-scroll" href="index.jsp#what_we_do">What We Do</a></li>
                <li><a class="page-scroll" href="index.jsp#works">Works</a></li>
                <li><a class="page-scroll" href="index.jsp#team">Team</a></li>             
                <li><a class="page-scroll" href="indexLogin.jsp">Login/Register</a></li>            
            </ul>
        </div>
    </div>
</nav>
<!--[navbar-end]-->
  
<!-- Mixins-->
<!-- Pen Title-->
<div class="pen-title">
  <h1></h1><span><i class=''></i><a></a></span>
</div>
<div class="container">
  <div class="card"></div>
  <div class="card">
    <h1 class="title">Login</h1>
    <form method="post" action="userLogin">
      <div class="input-container">
        <input type="text" id="loginUserName" name="loginUserName" required="required"/>
        <label for="loginUserName">Username</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input type="password" id="loginPassword" name="loginPassword" required="required"/>
        <label for="loginPassword">Password</label>
        <div class="bar"></div>
      </div>
      <div class="button-container">
        <button><span>Go</span></button>
      </div>
      <div class="footer" style="background: transparent;"><a href="#">Forgot your password?</a></div>
    </form>
  </div>
  <div class="card alt">
    <div class="toggle"></div>
    <h1 class="title">Register
      <div class="close" style="left:400px;right:0px;"></div>
    </h1>
    <form method="post" action="userCredentials">
      <div class="input-container">
        <input type="text" id="userName" name="userName" required="required"/>
        <label for="userName">Username</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input type="password" id="password" name="password" required="required"/>
        <label for="password">Password</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input type="password" id="some" name="samePassword" required="required"/>
        <label for="some">Repeat Password</label>
        <div class="bar"></div>
      </div>
      <div class="button-container">
        <button type="submit"><span>Next</span></button>
      </div>
    </form>
  </div>
</div>
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

  

    <script  src="js/index.js"></script>
</body>

</html>
