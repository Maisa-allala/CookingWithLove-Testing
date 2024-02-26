
<?php
 require 'model/init.php';

 $conn = require 'model/db.php';
 $userModel = new User();
 $validate = new Validate();

 $errorusername = '';
 $errorpassword = '';
 $errorconfirmpassword = '';
 $error = ''; 
 $enteredUsername = isset($_POST['username']) ? $_POST['username'] : '';
 $enteredpassword = isset($_POST['new_Password']) ? $_POST['new_Password'] : '';
 $enteredConfpassword = isset($_POST['con_pass']) ? $_POST['con_pass'] : '';
if($_SERVER['REQUEST_METHOD'] == "POST"){
 

  $username = trim(filter_input(INPUT_POST, 'username'));
  $password = trim(filter_input(INPUT_POST, 'new_Password'));
  $confirmPassword = trim(filter_input(INPUT_POST, 'con_pass'));
    $errorusername = $validate->validateusernamelogin($username);
    $errorpassword = $validate->validatepassword( $password );
    $errorconfirmpassword = $validate->validateconfirmpassword( $confirmPassword , $password);

    if(isset($_POST)){
      if(($errorusername == ''  && $errorpassword == '' && $errorconfirmpassword == '')){
       
    $user =    $userModel ->authenticate($conn,  $username , $password, $password);
if ($user === 'Password updated successfully'){
    echo '<script>';
      echo 'alert("Password has been updated successfully."); window.location.href = "/cookingwithlove/login.php";';
      echo '</script>';
   

   



}else {
  
    $error = $user;
 }}}}

?>
<?php require 'view/header.php'; ?>





<?php require 'view/resetpassword.php'; ?>



<?php require 'view/footer.php'; ?>