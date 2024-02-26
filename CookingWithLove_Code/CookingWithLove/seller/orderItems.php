<?php
require '../model/init.php';
$conn = require '../model/db.php';



$buyer= $_GET['buyer'];
$orderNum = $_GET['orderNum'];
$date = urldecode($_GET['date']); // Decode the date parameter
$totalPrice = $_GET['totalPrice'];
$status = $_GET['status'];

 $sellerOrderItems = Order::getBuyerByOrderNum($conn, $orderNum);


 if ($_SERVER['REQUEST_METHOD'] == "POST") {
  $orderNum = $_GET['orderNum'];

  if (isset($_POST['MarkasComplete'])) {
      $orderComplete = 'Y';

      if (Order::sellerupdateOrders($conn, $orderNum, $orderComplete)) {
          echo '<script>alert("Order has been Marked as Completed successfully."); window.history.go(-2);</script>';
      } else {
          echo '<script>alert("Order has not been Marked as Completed.");</script>';
      }
  } elseif (isset($_POST['MarkasPickedUp'])) {
      $deliveryComplete = 'Y';

      if (Order::sellerupdatedeliveryCompletedOrders($conn, $orderNum, $deliveryComplete)) {
          echo '<script>alert("Order has been Marked as Picked Up."); window.history.go(-2);</script>';
      } else {
          echo '<script>alert("Order has not been Marked as Picked Up.");</script>';
      }
  }
}
 




?>




<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>

            





            <?php require '../view/sellerorderItems.php'; ?>

<?php require '../view/footer.php'; ?>