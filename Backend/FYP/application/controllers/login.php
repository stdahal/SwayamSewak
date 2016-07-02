<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends CI_Controller {

	
	public function index()
	{
		
	
		$this->load->view('home');//, $data);
	}
	
	

	public function logout()
		{
		   $this->session->unset_userdata('logged_in');
		   $this->session->sess_destroy();
		   redirect('login', 'refresh');
		}
}
