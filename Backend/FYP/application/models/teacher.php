<?php
Class Teacher extends CI_Model
{
  function __construct() 
  {
    parent::__construct();
  }


//function to check userid provided and the available userid in the table users database FYP while creating a group i.e. first teacher must be available
function getteacherid($UserID)
 {
   $this -> db -> select('userid');
   $this -> db -> from('users');
   $this -> db -> where('userid', $UserID);
   $this -> db -> limit(1);

    $q = $this -> db -> get();
    $result = $q->num_rows();
    return $result;
  }
  
  function deleteTeacher($teacher_id)
  {
    $this->db->where('userid', $teacher_id);
	  $this->db->delete('users'); 
  }

   function getusers()
 {
  $data = "SELECT * FROM volunteer_detail";
  $query = $this->db-> query($data);
  return $query->result();
 }

 //function to get status of a user to activate or deactivate the user
 function getstatus($Id)
 {
   $this -> db -> select('status');
   $this -> db -> from('volunteer_detail');
   $this -> db -> where('Id', $Id);
   $this -> db -> limit(1);

    // $q = $this -> db -> get();
    // $result = $q->result();
    //return $result;
   return $this->db->get()->row_array();
 }

 function changestatus($Statu, $Id)
  {
    $this->db->set('Status', $Statu);
    $this->db->where('Id', $Id);
    $this->db->update('volunteer_detail');
 }

//function to deliver the usertype of the user
 function getusertype($UserID)
 {
   $this -> db -> select('usertype');
   $this -> db -> from('users');
   $this -> db -> where('userid', $UserID);
   $this -> db -> limit(1);

    // $q = $this -> db -> get();
    // $result = $q->result();
    //return $result;
   return $this->db->get()->row_array();
 }

  

  

 
  
}
?>