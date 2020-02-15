<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">

<head>
    <!--- Basic Page Needs  -->
    <meta charset="utf-8">
    <title>Zeedapp || App Landing Html Template</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Mobile Specific Meta  -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- CSS -->
    <style>
        <%@include file="/WEB-INF/views/assets/css/style.css" %>
    </style>
    <style>
        <%@include file="/WEB-INF/views/assets/css/responsive.css" %>
    </style>
    <style>
        <%@include file="/WEB-INF/views/assets/css/bootstrap.min.css" %>
    </style>
    <style>
        <%@include file="/WEB-INF/views/assets/css/owl.carousel.min.css" %>
    </style>
    <style>
        <%@include file="/WEB-INF/views/assets/css/font-awesome.min.css" %>
    </style>
    <style>
        <%@include file="/WEB-INF/views/assets/css/slicknav.min.css" %>
    </style>


    <!-- Favicon -->
    <link rel="shortcut icon" type="image/png" href="assets/img/icon/favicon.ico">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<!-- preloader area start -->
<div id="preloader">
    <div class="spinner"></div>
</div>
<!-- preloader area end -->
<!-- header area start -->
<header id="header">
    <div class="header-area">
        <div class="container">
            <div class="row">
                <div class="menu-area">

                </div>
                <div class="col-md-9 hidden-xs hidden-sm">
                    <div class="main-menu">
                        <nav class="nav-menu">
                            <ul style="border: #8a6d3b;border-width: 23px">
                                <li class="active"><a href="#home"><h4>Новинки</h4></a></li>
                                <li><a href="#feature"><h4> В топе</h4></a></li>
                                <li><a href="#screenshot" style="text-align: right"><h5 align="right">Войти</h5></a></li>
                                <li style="text-align: right"><a href="#screenshot"><h5 align="right">Регистрация</h5></a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 visible-sm visible-xs">
                    <div class="mobile_menu"></div>
                </div>
            </div>
        </div>
    </div>
    </div>
</header>
<!-- header area end -->
<!-- slider area start -->
<section class="slider-area" id="home">
    <div class="container">
        <div class="col-md-6 col-sm-6 hidden-xs">

        </div>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <div class="row">
                <div class="slider-inner text-right">
                    <h2>Hi</h2>
                    <h5>welcome!</h5>
                    <a class="expand-video" href="https://www.youtube.com/watch?v=8qs2dZO6wcc"><i
                            class="fa fa-play"></i>Watch the video</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- slider area end -->
<!-- service area start -->
<div class="service-area">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12">
                <div class="service-single">
                    <img src="assets/img/service/service-img1.png" alt="service image">
                    <h2>Call service</h2>
                    <p>Take The initative to call</p>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12 col-6">
                <div class="service-single">
                    <img src="assets/img/service/service-img2.png" alt="service image">
                    <h2>Active warning</h2>
                    <p>Timely detection of accidents</p>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12 col-6">
                <div class="service-single">
                    <img src="assets/img/service/service-img3.png" alt="service image">
                    <h2>Care plan</h2>
                    <p>The care content is pushed</p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- service area end -->
<!-- about area start -->
<div class="about-area ptb--100">
    <div class="container">
        <!---->
        <div class="section-title">
            <h2>About App</h2>
            <p>Nemo enim ipsam voluptatem quia voluptas sit </p>
        </div>
    </div>
</div>
</body>

</html>
