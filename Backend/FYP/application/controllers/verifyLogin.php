<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
 
class VerifyLogin extends CI_Controller {
 
 function __construct()
 {
   parent::__construct();
   $this->load->model('users','',TRUE);
 }
 
 function index()
 {
   //This method will have the credentials validation
   $this->load->library('form_validation');
 
   $this->form_validation->set_rules('username', 'Username', 'trim|required');
   $this->form_validation->set_rules('password', 'Password', 'trim|required|callback_check_database');

     if($this->form_validation->run() == FALSE)
   {
     //Field validation failed.  User redirected to login page (home page)
     
     $this->load->view('home');
    
   }
   else
   {
     $session_data = $this->session->userdata('logged_in');
      $session_authority = $session_data['UserType'];
      $status = $session_data['Status'];
      $username = $session_data['UserName'];
      $data = array(
      'username' => $username
      );
    
      if($session_authority == 'admin')
      {
       
         // $this->load->view('addteacher', $data);
        redirect('teacher_controller');
      }

   
      else
      {
        echo "You are Inactive";
      }

  }
 

 }
 
 function check_database($password)
 {
   //Field validation succeeded.  Validate against database
   $username = $this->input->post('username');
 
   //query the database
   $result = $this->users->login($username, $password);

   if($result)
   {
     $sess_array = array();
     foreach($result as $row)
     {
       $sess_array = array(
          'UserID'  => $row->userid,
          'UserName' => $row->username,
          'UserType' => $row->usertype,
          
          'Status' => $row->status
       );
       $this->session->set_userdata('logged_in', $sess_array);
     }
     return TRUE;
   }
   else
   {
     $this->form_validation->set_message('check_database', 'Invalid username or password');
     return false;
   }
 }
}
?>