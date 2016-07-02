<link rel="stylesheet" type="text/css" href="<?php echo base_url('application/assets/css/bootstrap.min.css');?> " />
<!DOCTYPE html>
<html lang="en">

       <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Yomari</title>

        <!-- Bootstrap core CSS -->

        <link href="<?php echo base_url('application/assets/css/bootstrap.min.css'); ?>" rel="stylesheet">
        <link href="<?php echo base_url('application/assets/fonts/css/font-awesome.min.css'); ?>" rel="stylesheet">
        <link href="<?php echo base_url('application/assets/css/animate.min.css'); ?>" rel="stylesheet">

       

        <!-- Custom styling plus plugins -->
         <link href="<?php echo base_url('application/assets/css/custom.css'); ?>" rel="stylesheet">
          <link href="<?php echo base_url('application/assets/css/icheck/flat/green.css'); ?>" rel="stylesheet">
           <link href="<?php echo base_url('application/assets/css/animate.min.css'); ?>" rel="stylesheet">

        <link href="<?php echo base_url('application/assets/css/custom.css'); ?>" rel="stylesheet">
        <link href="<?php echo base_url('application/assets/css/icheck/flat/green.css'); ?>" rel="stylesheet">

        <script src="application/assets/js/jquery.min.js"></script>
        



    </head>


    <body class="nav-md">

        <div class="container body">


            <div class="main_container">

                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">

                        <div class="navbar nav_title" style="border: 0;">
                            <a href="<?php echo base_url('teacher_controller'); ?>" class="site_title"><i class="fa fa-paw"></i> <span>Yomari</span></a>
                        </div>
                        <div class="clearfix"></div>


                        <!-- menu prile quick info -->
                        <!-- /menu prile quick info -->

                        <br />

                        <!-- sidebar menu -->
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

                            <div class="menu_section">
                                <h3>Welcome,</h3>
                                <ul class="nav side-menu">                                
                                  
                                    
                                     <li><a href="<?php echo base_url('teacher_controller/delete'); ?>"><i class="fa fa-home"></i>Manage Post<span></span></a></li>
                                     
                                     
                                   
                                 
                                    
                                </ul>
                            </div>
                            

                        </div>
                        <!-- /sidebar menu -->

                       
                    </div>
                </div>

                <!-- top navigation -->
                <div class="top_nav">

                    <div class="nav_menu">
                        <nav class="" role="navigation">
                            <div class="nav toggle">
                                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                            </div>

                            <ul class="nav navbar-nav navbar-right">
                               <li class="">
          
                                    <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
      
                                        </li>
                                    </ul>
                                     <li><a href="<?php echo base_url('login/logout'); ?>"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                                        </li>
                                </li>

                            </ul>
                        </nav>
                    </div>
                    

            </div>
