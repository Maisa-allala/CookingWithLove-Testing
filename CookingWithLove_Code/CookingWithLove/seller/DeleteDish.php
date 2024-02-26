

<?php
 require '../model/init.php';


$conn = require '../model/db.php';

if($_SESSION['accountID']){

  $dishid = $_POST['dishID'];

  var_dump( $_SESSION['accountID']);
  
  $dish = Dish::getByDishIDAccountID($conn, $_SESSION['accountID'], $dishid);
  
  if ( !  $dish) {
    die("Dish not found");
}
  
  }
  else {
    die("id not supplied, dish not found");
}
if($_SERVER['REQUEST_METHOD'] == "POST"){

if(isset($_POST['delete'])){

  
    
      if($dish->deletedish($conn)){
        
        echo '<script>';
        echo 'alert("Dish has been Deleted."); window.location.href = "/cookingwithlove/seller/CurrentDishes.php";';
        echo '</script>';
  
    }
    
     

    }

 

}








?>





<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>

<?php require '../view/dish-update-form.php'; ?>
<?php require '../view/footer.php'; ?>