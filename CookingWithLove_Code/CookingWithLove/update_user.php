
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

if($_SESSION['accountID']){
  $user = User::getByAccountID($conn, $_SESSION['accountID']);




}

if($_SERVER['REQUEST_METHOD'] == "POST"){





  



$user->first_name = trim(filter_input(INPUT_POST, 'first_name'));
$user->last_name = trim(filter_input(INPUT_POST, 'last_name'));
$user->dob = trim(filter_input(INPUT_POST, 'dob'));
$user->zip = trim(filter_input(INPUT_POST, 'zip'));

$user->email = trim(filter_input(INPUT_POST,'email_address'));
$user->phone = trim(filter_input(INPUT_POST, 'phone_number'));
$user->street = trim(filter_input(INPUT_POST, 'address'));

$erroremail = $validate->validateemail($user->email ) ;
$errorphone = $validate->validatephone($user->phone) ;
$erroraddress = $validate->validateaddress($user->street);
$errorzip = $validate->validatezip($user->zip);
$errordob = $validate->validatedob($user->dob);

$errorfname = $validate->validatefname($user->first_name);
$errorlname = $validate->validatelname($user->last_name);


if(isset($_POST)){
if(($erroremail == '' && $errorphone == '' && $errorfname == '' && $errorlname == ''  && $errorzip == '' && $errordob == '' && $erroraddress == '' )){
 
  if($user->updateuser($conn)){
    
   
     
       if( $_SESSION['accountType'] == "Seller"){
  
        echo '<script>';
        echo  'setTimeout(function(){ window.location.href = "/cookingwithlove/seller/SellerMainPage.php"; }, 1000);'; // 1000 milliseconds (1 second) delay
           echo 'alert("Account has been updated successfully.");window.close();';
    
           echo '</script>';
       

       }
       else {
      
        echo '<script>';
        echo  'setTimeout(function(){ window.location.href = "/cookingwithlove/buyer/BuyerMainPage.php"; }, 1000);'; // 1000 milliseconds (1 second) delay
           echo 'alert("Account has been updated successfully.");window.close();';
    
        
           echo '</script>';
       


       } 
}

 
}
}

}









?>
<?php require 'view/header.php'; ?>








<?php require 'view/user-update-form.php'; ?>

<?php require 'view/footer.php'; ?>