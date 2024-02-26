<?php
require '../model/init.php';
$conn = require '../model/db.php';
$accountID = $_SESSION['accountID'];
$title = $_GET['title'];
$cancelled = $_GET['cancelled']; 
$deliveryComplete = $_GET['deliveryComplete'];

$currentOrders = Order::buyerDisplayOrders($conn, $accountID, $cancelled, $deliveryComplete);

?>




<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>


<?php require '../view/orders.php'; ?>

<?php require '../view/footer.php'; ?>