<?php
require '../model/init.php';
$conn = require '../model/db.php';
$accountID = $_SESSION['accountID'];
$title = $_GET['title'];
$cancelled = $_GET['cancelled']; 
$deliveryComplete = $_GET['deliveryComplete'];
$orderComplete = $_GET['orderComplete'];
$status = $_GET['status'];

$sellerOrders = Order::sellerDisplayOrders($conn, $accountID, $cancelled, $deliveryComplete, $orderComplete);

?>




<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>


<?php require '../view/sellerOrders.php'; ?>

<?php require '../view/footer.php'; ?>