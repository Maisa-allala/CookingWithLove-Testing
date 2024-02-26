

<?php
 require '../model/init.php';

$dishInfo = new Dish();
$conn = require '../model/db.php';
$validate = new Validate();


$errordishname = '';
$errordescription = '';
$errordishType = '';
$errorprice = '';
$dishInfo->accountID =  $_SESSION['accountID'];
$errorstatus = '';
$errorpublishDate = '';


if($_SERVER['REQUEST_METHOD'] == "POST"){
$dishInfo->dishName = trim(filter_input(INPUT_POST, 'dis_name'));
$dishInfo->description = trim(filter_input(INPUT_POST, 'dish_detailes'));
$dishInfo->dishType = $_POST['dis_catagory'];
$dishInfo->price = trim(filter_input(INPUT_POST, 'food_price'));
$dishInfo->accountID =  $_SESSION['accountID'];
$dishInfo->status = $_POST['food_status'];
$dishInfo->publishDate = trim(filter_input(INPUT_POST, 'publish_date'));

$errordishname  = $validate->validatedishname($dishInfo->dishName);
$errorpublishDate = $validate->validatepublishDate($dishInfo->publishDate);
$errordescription = $validate->validatedescription($dishInfo->description);
$errorprice = $validate->validateprice($dishInfo->price);
$errorstatus = $validate->validateselect($dishInfo->status, 'Status');
$errordishType = $validate->validateselect($dishInfo->dishType, 'Dish Type');


if(isset($_POST)){
    if(($errordishname == '' && $errordescription == '' && $errordishType == '' && $errorprice == '' && $errorstatus == '' )){
     
      if($dishInfo->createDish($conn)){
    

        echo '<script>alert("Dish has been added successfully."); window.location.href = "/cookingwithlove/seller/CurrentDishes.php";</script>';
      }
      else {
        echo '<script type="text/javascript">alert("Dish has been not added .") ;</script>';
      }
     
    }
    }



}








?>





<?php require '../view/header.php'; ?>


<?php require '../view/dish-form.php'; ?>
<?php require '../view/footer.php'; ?>