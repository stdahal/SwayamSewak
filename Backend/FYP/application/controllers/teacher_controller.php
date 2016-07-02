<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Teacher_controller extends CI_Controller 
{
	function __construct() 
    {
      parent::__construct();

      $this->load->model('teacher');
      

    }
    
    public function index()
    {
      redirect('teacher_controller/delete');
    }
    
          
    public function delete()
        {
          $this->load->view('_templates/header');
      		$list1 = $this->teacher->getusers();
          $value=array(
          'data'=>$list1
          )  ;   
          $this->load->view('delete' ,$value);
          $this->load->view('_templates/footer');
             
    	  }

    public function changestatus()
      {
        
              $Id = $this->input->post('Id');
                
              $Status = $this->teacher->getstatus($Id);

              
              
              if ($Status['status'] == 'Active')
              {
                 
                 $Statu = "Deactive";
                 
                 $this->teacher->changestatus($Statu, $Id);
              redirect('Teacher_controller/delete'); 
              }
              elseif ($Status['status'] == 'Deactive') 
              {
                $Statu = "Active"; 
                $this->teacher->changestatus($Statu, $Id);
              redirect('Teacher_controller/delete'); 
                
              }
              else
              {
                echo ("The post does not exists");
              }
              // redirect('Teacher_controller/delete'); 
             
                  
           }
      //     else
      //     {
      //       echo "hey hey hey!!!";  
      //     }
      // }
       

     
     


}
?>