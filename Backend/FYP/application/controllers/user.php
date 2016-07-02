<?php

class User extends CI_Controller {

function __construct() {
parent::__construct();
$this->load->model('users');
}
function index() 
{
//Including validation library
/* $this->load->library('form_validation');

$this->form_validation->set_error_delimiters('<div class="error">', '</div>');*/

// //Validating Name Field
// $this->form_validation->set_rules('username', 'username', 'required|min_length[5]|max_length[15]');

// //Validating address Field
// $this->form_validation->set_rules('address', 'address', 'required');

// //Validating contact no. Field
// $this->form_validation->set_rules('contact', 'contact', 'required|regex_match[/^[0-9]{10}$/]');

// //Validating email Field
// $this->form_validation->set_rules('email', 'email', 'required|min_length[10]|max_length[50]');

// //Validating password Field
// $this->form_validation->set_rules('password', 'password', 'required|min_length[5]|max_length[50]');

// //Validating usertype Field
// $this->form_validation->set_rules('usertype', 'usertype', 'required|min_length[5]|max_length[50]');

/*	if ($this->form_validation->run() == FALSE) 
	{
		$this->load->view('home');
	} 
	else 
	{*/
		//Setting values for tabel columns
		$data = array(
		'username' => $this->input->post('username'),
		'address' => $this->input->post('address'),
		'contact' => $this->input->post('contact'),
		'email' => $this->input->post('email'),
		'password' => $this->input->post('password'),
		'usertype' => $this->input->post('usertype'),
		'status' => $this->input->post('status')
		);
		//Transfering data to Model
		$this->users->adduser($data);
		$data['message'] = 'Data Inserted Successfully';
		//Loading View
		$this->load->view('home', $data);
	 }

	 // function delete($username)
	 // {
		// $username = $this->input->post('username');
		// $this->users->deleteuser($username);

	 // }

	 function addteacher()
	 {
	 	$data = array(
		'username' => $this->input->post('username'),
		'address' => $this->input->post('address'),
		'contact' => $this->input->post('contact'),
		'email' => $this->input->post('email'),
		'password' => $this->input->post('password'),
		'usertype' => $this->input->post('usertype'),
		'status' => $this->input->post('status')
		);

		//Transfering data to Model
		$this->users->adduser($data);
		$id = array(
		'collegeid' => $this->input->post('collegeid'),
		'userid' => $this->db->insert_id()
		);
		$this->users->teacher_college($id);
		redirect('teacher_controller/addteacher');
	 }


	 
	}


?>