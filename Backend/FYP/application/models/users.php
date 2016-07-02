<?php
Class Users extends CI_Model
{
  function __construct() 
  {
    parent::__construct();
  }

 function login($username, $password)
 {
   $this -> db -> select('userid, username, usertype, status');
   $this -> db -> from('users');
   $this -> db -> where('username', $username);
   $this -> db -> where('password', $password);
   $this -> db -> limit(1);

   $query = $this -> db -> get();

   if($query -> num_rows() == 1)
   {
     return $query->result();
   }
   else
   {
     return false;
   }
 }

 function getpost($username)
 {
   $this -> db -> select('usertype');
   $this -> db -> from('users');
   $this -> db -> where('username', $username);
   $this -> db -> limit(1);

    $query = $this -> db -> get();
    $ret = $query->row();
    return $ret->usertype;
  }

function getvalue($username)
{
  $t = "SELECT usertype FROM users WHERE username = '$username'";
   $query = $this->db->query($t);  
     return $query; 
}

  function adduser($data)
  {
    // Inserting in Table(users) of Database(fyp)
    $this->db->insert('users', $data);


    $this->db->insert_id();
  }




//function to get userid from the provided username
  function getuserid($username)
   {
     $this -> db -> select('userid');
     $this -> db -> from('users');
     $this -> db -> where('username', $username);
     $this -> db -> limit(1);

      $query = $this -> db -> get();
      $req = $query->row();
      return $req->userid;
    }

    function teacher_college($id)
    {
      $this->db->insert('college_teacher', $id);
    }

    function getusername($userid)
    {
      $this -> db -> select('username');
    $this -> db -> from('users');
    $this -> db -> where('userid', $userid);
    // $this -> db -> limit(1);
    $query = $this->db-> get();
    return $query->result();
    }
    

  }
  



?>