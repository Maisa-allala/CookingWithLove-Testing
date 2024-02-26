<?php
require '../model/init.php';
$conn = require '../model/db.php';




$dishID = $_GET['dishID'];
$dishRequestedDate = urldecode($_GET['dishRequestedDate']);
$dishName = $_GET['dishName'];
$status= $_GET['status'];

 $dishInfo = Dish::getDishBydishID($conn, $dishID);


 if($_SERVER['REQUEST_METHOD'] == "POST"){
    if(isset($_POST['ApproveforPublishing'])){
    
      $is_published = 'Y';



   if( Dish::publishDish($conn, $dishID,  $is_published)){
    

    echo '<script>alert("Dish has been published successfully."); window.history.go(-2);</script>';
  }
  else {
    echo '<script type="text/javascript">alert("Dish has been not published.") ;</script>';
  }
    

 }
 }
 




?>




<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>

            





            <?php require '../view/dishDetails.php'; ?>

<?php require '../view/footer.php'; ?>