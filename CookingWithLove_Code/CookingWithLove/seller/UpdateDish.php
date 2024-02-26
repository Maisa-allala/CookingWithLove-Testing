

<?php
 require '../model/init.php';


$conn = require '../model/db.php';
$validate = new Validate();

$errordishname = '';
$errordescription = '';
$errordishType = '';
$errorprice = '';

$errorstatus = '';
// $errorpublishDate = '';



if($_SESSION['accountID']){

  $dishid = $_GET['dishID'];
  
  $dish = Dish::getByDishIDAccountID($conn, $_SESSION['accountID'], $dishid);
  
  if ( !  $dish) {
    die("Dish not found");
}
  
  }
  else {
    die("id not supplied, dish not found");
}
if($_SERVER['REQUEST_METHOD'] == "POST"){




  
  

$dish->dishName = trim(filter_input(INPUT_POST, 'dis_name'));
$dish->description = trim(filter_input(INPUT_POST, 'dish_detailes'));
$dish->dishType = trim(filter_input(INPUT_POST, 'dis_catagory'));

$dish->price = trim(filter_input(INPUT_POST, 'food_price'));
$dish->accountID =  $_SESSION['accountID'];
$dish->status = trim(filter_input(INPUT_POST, 'food_status'));


$dish->dishID = $dishid;

$errordishname  = $validate->validatedishname($dish->dishName);

$errordescription = $validate->validatedescription($dish->description);
$errorprice = $validate->validateprice($dish->price);
$errorstatus = $validate->validateselect($dish->status, 'Status');
$errordishType = $validate->validateselect($dish->dishType, 'Dish Type');




if(isset($_POST['modify'])){
  if(($errordishname == '' && $errordescription == '' && $errordishType == '' && $errorprice == '' && $errorstatus == '' )){

    
      if($dish->updatedish($conn)){
        


      echo '<script>';
      echo 'alert("Dish has been Updated."); window.location.href = "/cookingwithlove/seller/CurrentDishes.php";';
      echo '</script>';

 
    }
    
     
 }
    }

 

}








?>





<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>

<?php require '../view/dish-update-form.php'; ?>
<?php require '../view/footer.php'; ?>