<link rel="stylesheet" type="text/css" href="<?php echo base_url('application/assets/css/bootstrap.min.css');?> " />
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
   <title>Swyam Sewak</title>
   <link href="<?php echo base_url('application/assets/css/bootstrap.min.css'); ?>" rel="stylesheet">
 </head>
 <body align="center">
      <h1>Login</h1>
        <!-- <?php echo validation_errors(); ?> -->
        <form method="post" action="<?php echo base_url('verifyLogin'); ?>"
         <label for="username">Username:</label>
         <input type="text" size="20" id="username" name="username" required/>
         <br/>
         <label for="password">Password:</label>
         <input type="password" size="20" id="passowrd" name="password" required/>
         <br/>
          <p>
            <div class="form-group">
            <button type="submit" name="submit_signup" value="Login" class="btn btn-success">Login</button>
            </div>
          </p>
   </form>
 </body>
</html>
</html>