
<?php
 require 'model/init.php';

 $conn = require 'model/db.php';
 $userModel = new User();
 $validate = new Validate();

 $errorusername = '';
 $errorpassword = '';
 $error = ''; 
 $enteredUsername = isset($_POST['username']) ? $_POST['username'] : '';
 $enteredpassword = isset($_POST['password']) ? $_POST['password'] : '';
if($_SERVER['REQUEST_METHOD'] == "POST"){
 

  $username = trim(filter_input(INPUT_POST, 'username'));
  $password = trim(filter_input(INPUT_POST, 'password'));
    $errorusername = $validate->validateusernamelogin($username);
    $errorpassword = $validate->validatepasswordlogin( $password );

    if(isset($_POST)){
      if(($errorusername == ''  && $errorpassword == '' )){
       
    $user =    $userModel ->authenticate($conn, $_POST['username'] , $_POST['password']);
if ($user instanceof User){
    
   Auth::login();
   $_SESSION['accountID'] = $user->accountID;
   $_SESSION['accountType'] = $user->accountType;
   $_SESSION['userName'] = $user->userName;
   $_SESSION['is_active'] =  $user->is_active ;

   if ($user->accountType === 'Admin') {

   Url::redirect('/cookingwithlove/admin/adminmainpage.php');
    exit();
} elseif ($user->accountType === 'Buyer') {
if(isset($_SESSION['is_active']) && $_SESSION['is_active'] === 'Y'){
    Url::redirect('/cookingwithlove/buyer/BuyerMainPage.php');

    exit();}
    else{
      Url::redirect('/cookingwithlove/');
      exit();
    }
} elseif ($user->accountType === 'Seller') {
   
    if(isset($_SESSION['is_active']) && $_SESSION['is_active'] === 'Y'){
      Url::redirect('/cookingwithlove/seller/SellerMainPage.php');
  
      exit();}
      else{
        Url::redirect('/cookingwithlove/');
        exit();
      }
} else {
  Url::redirect('/cookingwithlove/');

  
   
    exit();
   
}


}else {
   
    $error = $user;
 }}}}

?>
<?php require 'view/header.php'; ?>


    <div class="Login_form">
<form method ="post">
<div class="row">
      <div class="col-25">
        <label for = "username">Username </label>
        </div>
      <div class="row-75">
        <input type="text" name="username" id="username" value="<?= htmlspecialchars($enteredUsername); ?>">
        <span class = "error-message"> <?php echo   $errorusername; ?></span>   
</div>
</div>
<div class="row">
      <div class="col-25">
        <label for = "password">Password </label>
        </div>
      <div class="row-75">
        <input type = "password" name="password" id="password" value="<?= htmlspecialchars($enteredpassword); ?>">
        <span class = "error-message"> <?php echo   $errorpassword; ?></span>
</div>
</div>



<div class="row">
    <input type="submit" value="Login" >
  </div>

  
  <div class="login_links">
    
  <a href="Reset_Password.php" class="ResetCredential"><span style="font-size: 1em; font-style: oblique;font-family: Arial, Helvetica, sans-serif;">Forgot Password ?</span></a><br>
        <a href="new_user.php" class="UserSignUp"><span style="font-size: 1em; font-style: oblique;font-family: Arial, Helvetica, sans-serif;">Create Account</span></a><br>
     </div>
</form>
<div>
<?php if(! empty($error)): ?>
    <p class="styled-text-error"><?= $error?></p>
    <?php endif; ?>
</div>
</div>

<?php require 'view/footer.php'; ?>