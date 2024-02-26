
<?php
 require 'model/init.php';

$userInfo = new User();
$conn = require 'model/db.php';
$validate = new Validate();

$erroremail = '';
$errorphone = '';
$errorfname = '';
$errorlname = '';
$erroraddress = '';
$errorzip = '';
$errordob = '';
$errorpassword = '';
$errorconfirmpassword = '';
$errorusername = '';
$erroracounttype = '';

if($_SERVER['REQUEST_METHOD'] == "POST"){





  

$userInfo->username = trim(filter_input(INPUT_POST, 'username'));
$userInfo->password = trim(filter_input(INPUT_POST, 'password'));
$userInfo->accountType = $_POST['accountType'];
$userInfo->confirm_password = trim(filter_input(INPUT_POST, 'confirm_password'));
$userInfo->first_name = trim(filter_input(INPUT_POST, 'first_name'));
$userInfo->last_name = trim(filter_input(INPUT_POST, 'last_name'));
$userInfo->dob = trim(filter_input(INPUT_POST, 'dob'));
$userInfo->zip = trim(filter_input(INPUT_POST, 'zip'));

$userInfo->email = trim(filter_input(INPUT_POST,'email_address'));
$userInfo->phone = trim(filter_input(INPUT_POST, 'phone_number'));
$userInfo->street = trim(filter_input(INPUT_POST, 'address'));

$erroremail = $validate->validateemail($userInfo->email ) ;
$errorphone = $validate->validatephone($userInfo->phone) ;
$erroracounttype = $validate->validateaccounttype($userInfo->accountType) ;

$errorfname = $validate->validatefname($userInfo->first_name);
$errorlname = $validate->validatelname($userInfo->last_name);
$erroraddress = $validate->validateaddress($userInfo->street);
$errorzip = $validate->validatezip($userInfo->zip);
$errordob = $validate->validatedob($userInfo->dob);
$errorusername = $validate->validateusername($userInfo->username);
$errorpassword = $validate->validatepassword($userInfo->password);
$errorconfirmpassword = $validate->validateconfirmpassword( $userInfo->confirm_password ,$userInfo->password);
if(isset($_POST)){
if(($errorusername == '' && $erroracounttype == '' && $errorzip == '' && $errorconfirmpassword == '' && $errorpassword == '' && $errordob == '' && $erroremail == '' && $erroraddress == '' && $errorphone == '' && $errorfname == '' && $errorlname == '' )){
 
  if($userInfo->create($conn)){
  

  echo '<script>';
      echo 'alert("Congratulations! Your account has been created successfully."); window.location.href = "/cookingwithlove/login.php";';
      echo '</script>';


  }
 
 
}
}

}









?>
<?php require 'view/header.php'; ?>







<?php require 'view/user-form.php'; ?>

<?php require 'view/footer.php'; ?>