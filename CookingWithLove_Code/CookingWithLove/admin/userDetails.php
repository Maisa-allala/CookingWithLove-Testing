<?php
require '../model/init.php';
$conn = require '../model/db.php';




$accountID = $_GET['accountID'];
$accountRequestedDate = urldecode($_GET['accountRequestedDate']);
$userName = $_GET['userName'];
$status= $_GET['status'];

 $userInfo = User::getUserByAccountID($conn, $accountID);


 if($_SERVER['REQUEST_METHOD'] == "POST"){
    if(isset($_POST['MarkasActive'])){
    
    $is_active = 'Y';



   if( User::ActiveUserAccount($conn, $accountID,  $is_active)){
    

    echo '<script>alert("Account has been activated successfully."); window.history.go(-2);</script>';
  }
  else {
    echo '<script type="text/javascript">alert("Account has been not activated .") ;</script>';
  }
    

 }
 }
 




?>




<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>

            





            <?php require '../view/userDetails.php'; ?>

<?php require '../view/footer.php'; ?>