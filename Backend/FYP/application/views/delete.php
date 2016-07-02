<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
  <!-- page content -->
                <div class="right_col" role="main">
                    <div class="">

                        <div class="page-title">
                            <div class="title_left">
                                <h3>
                                    Swayam 
                                    <small>
                                        Sewak
                                    </small>
                                </h3>
                            </div>

                                    </div>
                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                            <li><a class="close-link"><i class="fa fa-close"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <br /> 


    <div align="left">
    <table class="table table-bordered table-striped">
    <thead>
         <tr>
          <td><strong>Id</strong></td>
          <td><strong>Organization Name</strong></td>
          <td><strong>Number of Volunteers</strong></td>
          <td><strong>Emergency level</strong></td>
          <td><strong>Disaster type</strong></td>
          
          <td><strong>Contact</strong></td>
          <td><strong>Disaster Adress</strong></td>
          
          <td><strong>Reports</strong></td>
          <td><strong>Status</strong></td>
          
        </tr>
     </thead>
     <tbody>
     <?php
	 $a = 1;
	 foreach($data as $row){?>
     <tr>
         <td><?php echo $row->Id;?></td>
         <td><?php echo $row->Org_Name;?></td>
         <td><?php echo $row->Num_Of_Volunteers;?></td>
         <td><?php echo $row->Emergency_Level;?></td>
         <td><?php echo $row->Disaster_Type;?></td>
        
        <td><?php echo $row->Org_ContactNumber;?></td>
        <td><?php echo $row->Disaster_Address;?></td>
        
        <td><?php echo $row->Num_Of_Reports;?></td>
        <td><?php echo $row->Status;?></td>
        
        <?php $stat = $row->Status;?>
        

         <td>
         	
            <form style="float:left" onSubmit="return confirm('are you sure?')" method="post" action="<?php echo base_url('teacher_controller/changestatus'); ?>">
            	<input type="hidden" name="Id" value="<?php echo $row->Id;?>"/>
            <?php if ($stat=="Active") 
            {
                echo '<button class="btn btn-success" name="submittodelete"  value="submit">Deactivate</button>';
            }
            else
            {
                echo '<button class="btn btn-danger" name="submittodelete"  value="submit">Activate</button>';
            }
        
        ?>
                

            </form>
            <br style="clear:both;"/>
         </td>
      </tr>    
     <?php }?>  
     </tbody>
   </table>

   </div>
</script>

