<?php
require '../model/init.php';
$conn = require '../model/db.php';




$orderNum = $_GET['orderNum'];
$date = urldecode($_GET['date']); // Decode the date parameter
$totalPrice = $_GET['totalPrice'];
$deliveryType = $_GET['deliveryType'];
$status = $_GET['status'];

 $currentOrderItems = Order::getByOrderNum($conn, $orderNum);


 if($_SERVER['REQUEST_METHOD'] == "POST"){
    if(isset($_POST['CancelOrder'])){
    
    $orderNum= $_GET['orderNum'];
    $cancelled = 'Y';



   if( Order::updateOrders($conn, $orderNum, $cancelled)){
    

    echo '<script>alert("Order has been cancelled successfully."); window.history.go(-2);</script>';
  }
  else {
    echo '<script type="text/javascript">alert("Order has been not cancelled .") ;</script>';
  }
    

 }
 }
 




?>




<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>

            





            <?php require '../view/orderItems.php'; ?>

<?php require '../view/footer.php'; ?>